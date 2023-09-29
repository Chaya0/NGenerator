package com.generator;

import java.util.Arrays;
import java.util.List;

import com.generator.model.AppModel;
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
	private AppModel appModel;

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

	public AppModel getAppModel() {
		return appModel;
	}
}
