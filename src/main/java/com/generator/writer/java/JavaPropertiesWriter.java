package com.generator.writer.java;

import com.generator.Application;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaPropertiesWriter {
	
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getResourcesPath(), "application.properties", true)) {
			file.writeln(0, "spring.datasource.url="  + Application.getSpringProperties().getDatasourceUrl());
			file.writeln(0, "spring.datasource.username=" + Application.getSpringProperties().getDatasourceUsername());
			file.writeln(0, "spring.datasource.password="+ Application.getSpringProperties().getDatasourcePassword());
			file.writeln(0, "spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect");
			file.writeln(0, "spring.jpa.generate-ddl=true");
			file.writeln(0, "spring.datasource.driver-class-name=com.mysql.jdbc.Driver");
			file.writeln(0, "spring.jpa.hibernate.ddl-auto=create-drop");
		}
	}
}
