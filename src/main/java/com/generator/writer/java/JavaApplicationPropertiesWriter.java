package com.generator.writer.java;

import com.generator.Application;
import com.generator.model.enums.properties.AuthenticationType;
import com.generator.util.RandomUtil;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaApplicationPropertiesWriter {

	public void create() throws Exception {
		if (Application.getGeneratorProperties().isGenerateQuartzComponents()) {
			generateQuartzProperties();
		}
		generateApplicationProperties();
		writeTestProperties();
	}

	private void generateApplicationProperties() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "application.properties", true)) {
			file.writeln(0, "spring.datasource.url=" + Application.getSpringProperties().getDatasourceUrl());
			file.writeln(0, "spring.datasource.username=" + Application.getSpringProperties().getDatasourceUsername());
			file.writeln(0, "spring.datasource.password=" + Application.getSpringProperties().getDatasourcePassword());
			file.writeln(0, "spring.application.name=" + Application.getSpringProperties().getName());
			file.writeln(0, "spring.jpa.database-platform=org.hibernate.dialect." + Application.getGeneratorProperties().getDatabaseType().getCode());
			file.writeln(0, "spring.jpa.hibernate.ddl-auto=create-drop");
			file.writeln(0, "spring.sql.init.mode=always");
			file.writeln(0, "spring.jpa.generate-ddl=true");
			file.writeln(0, "spring.sql.init.continue-on-error=false");
			file.breakLine();

			if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "jwt.secret=tTuEYaKUFumwKdiq0kzn");
				file.writeln(0, "#	15 Minutes");
				file.writeln(0, "jwt.expiration=900000");
				file.writeln(0, "#	30 Days");
				file.writeln(0, "jwt.refreshToken=2592000000");
				file.breakLine();
				if (Application.getGeneratorProperties().getAuthenticationType().equals(AuthenticationType.JWT_COOKIE_SECURE)) {
					file.writeln(0, "# Enable SSL");
					file.writeln(0, "server.ssl.enabled=true");
					file.breakLine();
					file.writeln(0, "# Path to the keystore file");
					file.writeln(0, "server.ssl.key-store=classpath:keystore.p12");
					file.breakLine();
					file.writeln(0, "# Keystore password");
					file.writeln(0, "server.ssl.key-store-password=" + Application.getSpringProperties().getName() + "-keystore-password");
					file.breakLine();
					file.writeln(0, "# Keystore type (PKCS12 is common)");
					file.writeln(0, "server.ssl.keyStoreType=PKCS12");
					file.breakLine();
					file.writeln(0, "# Alias of the certificate (should match the alias used during creation)");
					file.writeln(0, "server.ssl.key-alias=" + Application.getSpringProperties().getName() + "-alias");
					file.breakLine();
					file.writeln(0, "# Password of the certificate");
					file.writeln(0, "server.ssl.key-alias=" + Application.getSpringProperties().getName() + "-password");
					file.breakLine();
//					file.writeln(0, "# HTTP Port (optional - redirect HTTP to HTTPS)");
//					file.writeln(0, "8085");
//					file.breakLine();
				}
			}
		}
	}

	private void generateQuartzProperties() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "quartz.properties", false)) {
			file.writeln(0, "org.quartz.scheduler.instanceName=" + Application.getSpringProperties().getName());
			file.writeln(0, "org.quartz.threadPool.threadCount=3");
			file.writeln(0, "org.quartz.jobStore.class=org.quartz.simpl.RAMJobStore");
		}
	}

	private void writeTestProperties() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getTestResourcesPath(), "application.properties", true)) {

			file.writeln(0, "# Configure your datasource for tests");
			file.writeln(0, "spring.datasource.url=" + Application.getSpringProperties().getTestDatasourceUrl());
			file.writeln(0, "spring.datasource.username=" + Application.getSpringProperties().getDatasourceUsername());
			file.writeln(0, "spring.datasource.password=" + Application.getSpringProperties().getDatasourcePassword());
			file.breakLine();
			file.writeln(0, "# Hibernate properties");
			file.writeln(0, "spring.jpa.hibernate.ddl-auto=update");
			file.writeln(0, "spring.jpa.show-sql=true");
			file.writeln(0, "spring.jpa.database-platform=org.hibernate.dialect." + Application.getGeneratorProperties().getDatabaseType().getCode());
			file.breakLine();
			file.writeln(0, "# Disable caching");
			file.writeln(0, "spring.cache.type=none");
			file.breakLine();
			if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "jwt.secret=tTuEYaKUFumwKdiq0kzn");
				file.writeln(0, "#	15 Minutes");
				file.writeln(0, "jwt.expiration=900000");
				file.writeln(0, "#	30 Days");
				file.writeln(0, "jwt.refreshToken=2592000000");
				file.breakLine();
				if (Application.getGeneratorProperties().getAuthenticationType().equals(AuthenticationType.JWT_COOKIE_SECURE)) {
					file.writeln(0, "# Enable SSL");
					file.writeln(0, "server.ssl.enabled=true");
					file.breakLine();
					file.writeln(0, "# Path to the keystore file");
					file.writeln(0, "server.ssl.key-store=classpath:keystore.p12");
					file.breakLine();
					file.writeln(0, "# Keystore password");
					file.writeln(0, "server.ssl.key-store-password=" + Application.getSpringProperties().getName() + "-keystore-password");
					file.breakLine();
					file.writeln(0, "# Keystore type (PKCS12 is common)");
					file.writeln(0, "server.ssl.keyStoreType=PKCS12");
					file.breakLine();
					file.writeln(0, "# Alias of the certificate (should match the alias used during creation)");
					file.writeln(0, "server.ssl.key-alias=" + Application.getSpringProperties().getName() + "-alias");
					file.breakLine();
					file.writeln(0, "# Password of the certificate");
					file.writeln(0, "server.ssl.key-alias=" + Application.getSpringProperties().getName() + "-password");
					file.breakLine();
//					file.writeln(0, "# HTTP Port (optional - redirect HTTP to HTTPS)");
//					file.writeln(0, "8085");
//					file.breakLine();
				}
			}
		}
	}

}
