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
import com.generator.writer.java.JavaApiUtilWriter;
import com.generator.writer.java.JavaControllerWriter;
import com.generator.writer.java.JavaRepositoryWriter;
import com.generator.writer.java.JavaServiceWriter;
import com.generator.writer.java.generic.JavaEntityWriter;
import com.generator.writer.java.generic.JavaEnumWriter;
import com.generator.writer.java.generic.JavaGenericControllerWriter;
import com.generator.writer.java.generic.JavaGenericRepositoryWriter;
import com.generator.writer.java.generic.JavaGenericServiceWriter;

public class ApplicationGenerator {
	private static final Logger logger = LogManager.getLogger(ApplicationGenerator.class);
	private static List<Writer> writers = initJavaWriters();
	private static AppModel appModel = XMLModelReader.readModel();

	public static void generateApp() {
		extractAppIfItDoesntExists();
		JavaApiUtilWriter utilWriter = new JavaApiUtilWriter();;
		for (Writer writer : writers) {
			try {
				writer.create(appModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			utilWriter.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");

	}

	private static List<Writer> initJavaWriters() {
		Writer entityWriter = new JavaEntityWriter();
		Writer genControllerWriter = new JavaGenericControllerWriter();
		Writer genRepositoryWriter = new JavaGenericRepositoryWriter();
		Writer genServiceWriter = new JavaGenericServiceWriter();
		Writer controllerWriter = new JavaControllerWriter();
		Writer repositoryWriter = new JavaRepositoryWriter();
		Writer serviceWriter = new JavaServiceWriter();
		Writer enumWriter = new JavaEnumWriter();
		return Arrays.asList(entityWriter, genControllerWriter, genRepositoryWriter, genServiceWriter, controllerWriter, repositoryWriter, serviceWriter, enumWriter);
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
