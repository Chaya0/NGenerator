package com.generator.writer.java;

import com.generator.Application;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaPropertiesWriter {
	
	public void create() throws Exception {
		if(Application.getGeneratorProperties().isGenerateQuartzComponents()) {
			generateQuartzProperties();
		}
		generateApplicationProperties();
	}
	
	private void generateApplicationProperties() throws Exception{
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "application.properties", false)) {
			file.writeln(0, "spring.datasource.url="  + Application.getSpringProperties().getDatasourceUrl());
			file.writeln(0, "spring.datasource.username=" + Application.getSpringProperties().getDatasourceUsername());
			file.writeln(0, "spring.datasource.password="+ Application.getSpringProperties().getDatasourcePassword());
			file.writeln(0, "spring.application.name=" + Application.getSpringProperties().getName());
			file.writeln(0, "spring.jpa.database-platform=org.hibernate.dialect." + Application.getGeneratorProperties().getDatabaseType().getCode());
			file.writeln(0, "spring.jpa.hibernate.ddl-auto=validate");
			file.writeln(0, "spring.sql.init.mode=always");
			file.writeln(0, "spring.jpa.generate-ddl=true");
			file.writeln(0, "spring.sql.init.continue-on-error=false");
			
			if(Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "jwt.secret=tTuEYaKUFumwKdiq0kzn");
				file.writeln(0, "#	15 Minutes");
				file.writeln(0, "jwt.expiration=900000");
				file.writeln(0, "#	30 Days");
				file.writeln(0, "jwt.refreshToken=2592000000");
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
