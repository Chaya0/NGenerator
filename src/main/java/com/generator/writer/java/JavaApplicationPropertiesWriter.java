package com.generator.writer.java;

import com.generator.Application;
import com.generator.model.enums.properties.AuthenticationType;
import com.generator.util.RandomUtil;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaApplicationPropertiesWriter {
	
	public void create() throws Exception {
		if(Application.getGeneratorProperties().isGenerateQuartzComponents()) {
			generateQuartzProperties();
		}
		generateApplicationProperties();
	}
	
	private void generateApplicationProperties() throws Exception{
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "application.properties", true)) {
			file.writeln(0, "spring.datasource.url="  + Application.getSpringProperties().getDatasourceUrl());
			file.writeln(0, "spring.datasource.username=" + Application.getSpringProperties().getDatasourceUsername());
			file.writeln(0, "spring.datasource.password="+ Application.getSpringProperties().getDatasourcePassword());
			file.writeln(0, "spring.application.name=" + Application.getSpringProperties().getName());
			file.writeln(0, "spring.jpa.database-platform=org.hibernate.dialect." + Application.getGeneratorProperties().getDatabaseType().getCode());
			file.writeln(0, "spring.jpa.hibernate.ddl-auto=create-drop");
			file.writeln(0, "spring.sql.init.mode=always");
			file.writeln(0, "spring.jpa.generate-ddl=true");
			file.writeln(0, "spring.sql.init.continue-on-error=false");
			file.breakLine();

			
			if(Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "jwt.secret=tTuEYaKUFumwKdiq0kzn");
				file.writeln(0, "#	15 Minutes");
				file.writeln(0, "jwt.expiration=900000");
				file.writeln(0, "#	30 Days");
				file.writeln(0, "jwt.refreshToken=2592000000");
				file.breakLine();
				if(Application.getGeneratorProperties().getAuthenticationType().equals(AuthenticationType.JWT_COOKIE_SECURE)) {
					file.writeln(0, "# Enable SSL");
					file.writeln(0, "server.ssl.enabled=true");
					file.breakLine();
					file.writeln(0, "# Path to the keystore file");
					file.writeln(0, "server.ssl.key-store=classpath:keystore.p12");
					file.breakLine();
					file.writeln(0, "# Keystore password");
					file.writeln(0, "server.ssl.key-store-password=" + RandomUtil.generateRandomString(23));
					file.breakLine();
					file.writeln(0, "# Keystore type (PKCS12 is common)");
					file.writeln(0, "");
					file.breakLine();
					file.writeln(0, "# Alias of the certificate (should match the alias used during creation)");
					file.writeln(0, "");
					file.breakLine();
//					file.writeln(0, "# HTTP Port (optional - redirect HTTP to HTTPS)");
//					file.writeln(0, "8085");
//					file.breakLine();
				}
			}
		}
	}
	
	private void generateQuartzProperties() throws Exception{
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "quartz.properties", false)) {
			file.writeln(0, "org.quartz.scheduler.instanceName=" + Application.getSpringProperties().getName());
			file.writeln(0, "org.quartz.threadPool.threadCount=3");
			file.writeln(0, "org.quartz.jobStore.class=org.quartz.simpl.RAMJobStore");
		}
	}
	
}
