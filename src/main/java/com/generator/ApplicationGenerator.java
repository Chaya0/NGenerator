package com.generator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.AppModel;
import com.generator.reader.XMLModelReader;
import com.generator.writer.SpringStartExtractor;
import com.generator.writer.Writer;
import com.generator.writer.java.components.JavaControllerWriter;
import com.generator.writer.java.components.JavaRepositoryWriter;
import com.generator.writer.java.components.JavaServiceWriter;
import com.generator.writer.java.components.basic.JavaBasicControllerWriter;
import com.generator.writer.java.components.basic.JavaBasicRepositoryWriter;
import com.generator.writer.java.components.basic.JavaBasicServiceWriter;
import com.generator.writer.java.components.generic.JavaEntityWriter;
import com.generator.writer.java.components.generic.JavaEnumWriter;
import com.generator.writer.java.components.generic.JavaGenericControllerWriter;
import com.generator.writer.java.components.generic.JavaGenericRepositoryWriter;
import com.generator.writer.java.components.generic.JavaGenericServiceWriter;
import com.generator.writer.java.config.JavaSwaggerWriter;
import com.generator.writer.java.config.quartz.JavaAutowiringSpringBeanJobFactoryWriter;
import com.generator.writer.java.config.quartz.JavaQuartzConfigurationWriter;
import com.generator.writer.java.config.security.JavaJwtFilterWriter;
import com.generator.writer.java.config.security.JavaJwtUtilWriter;
import com.generator.writer.java.config.security.JavaSpringSecurityConfigWriter;
import com.generator.writer.java.dto.JavaErrorResponseDTOWriter;
import com.generator.writer.java.dto.JavaGenericResponseWriter;
import com.generator.writer.java.exceptions.JavaApplicationExceptionWriter;
import com.generator.writer.java.exceptions.JavaExceptionHandlerWriter;
import com.generator.writer.java.exceptions.JavaOperationNotSupprotedExceptionWriter;
import com.generator.writer.java.utils.JavaApiUtilWriter;

public class ApplicationGenerator {
	private static final Logger logger = LogManager.getLogger(ApplicationGenerator.class);
	private static List<Writer> writers = initJavaWriters();
	private static AppModel appModel = XMLModelReader.readModel();

	public static void generateApp() {
		extractAppIfItDoesntExists();
		JavaApiUtilWriter utilWriter = new JavaApiUtilWriter();;
		JavaAutowiringSpringBeanJobFactoryWriter autowiringSpringBeanJobFactoryWriter = new JavaAutowiringSpringBeanJobFactoryWriter();
		JavaQuartzConfigurationWriter quartzWriter = new JavaQuartzConfigurationWriter();
		JavaJwtFilterWriter jwtFilterWriter = new JavaJwtFilterWriter();
		JavaJwtUtilWriter jwtUtilWriter = new JavaJwtUtilWriter();
		JavaSpringSecurityConfigWriter javaSpringSecurityConfigWriter = new JavaSpringSecurityConfigWriter();
		JavaSwaggerWriter swaggerWriter = new JavaSwaggerWriter();
		JavaErrorResponseDTOWriter javaErrorResponseDTOWriter = new JavaErrorResponseDTOWriter();
		JavaGenericResponseWriter javaGenericResponseWriter = new JavaGenericResponseWriter();
		JavaExceptionHandlerWriter javaExceptionHandlerWriter = new JavaExceptionHandlerWriter();
		JavaApplicationExceptionWriter javaApplicationExceptionWriter = new JavaApplicationExceptionWriter();
		JavaOperationNotSupprotedExceptionWriter javaOperationNotSupprotedExceptionWriter = new JavaOperationNotSupprotedExceptionWriter();
		
		for (Writer writer : writers) {
			try {
				writer.create(appModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			utilWriter.create();
			autowiringSpringBeanJobFactoryWriter.create();
			quartzWriter.create();
			jwtFilterWriter.create();
			jwtUtilWriter.create();
			javaSpringSecurityConfigWriter.create();
			swaggerWriter.create();
			javaErrorResponseDTOWriter.create();
			javaGenericResponseWriter.create();
			javaExceptionHandlerWriter.create();
			javaOperationNotSupprotedExceptionWriter.create();
			javaApplicationExceptionWriter.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Splendid.");

	}

	private static List<Writer> initJavaWriters() {
		Writer entityWriter = new JavaEntityWriter();
		Writer enumWriter = new JavaEnumWriter();
		Writer genControllerWriter = new JavaGenericControllerWriter();
		Writer basicControllerWriter = new JavaBasicControllerWriter();
		Writer controllerWriter = new JavaControllerWriter();
		Writer genRepositoryWriter = new JavaGenericRepositoryWriter();
		Writer basicRepositoryWriter = new JavaBasicRepositoryWriter();
		Writer repositoryWriter = new JavaRepositoryWriter();
		Writer genServiceWriter = new JavaGenericServiceWriter();
		Writer basicServiceWriter = new JavaBasicServiceWriter();
		Writer serviceWriter = new JavaServiceWriter();
		return Arrays.asList(entityWriter, genControllerWriter, genRepositoryWriter, genServiceWriter, controllerWriter, repositoryWriter, serviceWriter, enumWriter,basicControllerWriter, basicRepositoryWriter, basicServiceWriter);
	}

	private static void extractAppIfItDoesntExists() {
		String path = Application.getSpringProperties().getProjectPath() + Application.getSpringProperties().getBaseDir();
		File file = new File(path);
		if (!file.exists()) {
			logger.info("Application folder is not detected. New insatance of application is being created on path : " + path);
			SpringStartExtractor.extractApp();
		}else {
			logger.info("Application already exsists on path : " + path + ". Generic files will be overwritten!");
		}
	}

}
