package com.generator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.generator.model.AppModel;
import com.generator.reader.ModelReader;
import com.generator.writer.SpringStartExtractor;
import com.generator.writer.Writer;
import com.generator.writer.java.JavaControllerWriter;
import com.generator.writer.java.JavaRepositoryWriter;
import com.generator.writer.java.JavaServiceWriter;
import com.generator.writer.java.generic.JavaEntityWriter;
import com.generator.writer.java.generic.JavaGenericControllerWriter;
import com.generator.writer.java.generic.JavaGenericRepositoryWriter;
import com.generator.writer.java.generic.JavaGenericServiceWriter;

public class ApplicationGenerator {
	private static List<Writer> writers = initWriters();
	private static AppModel appModel = ModelReader.readModel();

	public static void generateApp() {
		extractAppIfItDoesntExists();
		for (Writer writer : writers) {
			try {
				writer.create(appModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static List<Writer> initWriters() {
		Writer entityWriter = new JavaEntityWriter();
		Writer genControllerWriter = new JavaGenericControllerWriter();
		Writer genRepositoryWriter = new JavaGenericRepositoryWriter();
		Writer genServiceWriter = new JavaGenericServiceWriter();
		Writer controllerWriter = new JavaControllerWriter();
		Writer repositoryWriter = new JavaRepositoryWriter();
		Writer serviceWriter = new JavaServiceWriter();

		return Arrays.asList(entityWriter, genControllerWriter, genRepositoryWriter, genServiceWriter, controllerWriter, repositoryWriter, serviceWriter);
	}

	private static void extractAppIfItDoesntExists() {
		File file = new File(Application.getSpringProperties().getProjectPath() + Application.getSpringProperties().getArtifactId());
		if (!file.exists()) {
			SpringStartExtractor.extractApp();
		}
	}

}
