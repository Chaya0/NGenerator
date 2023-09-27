package com.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.generator.model.AppModel;
import com.generator.model.SpringProperties;
import com.generator.writer.Writer;
import com.generator.writer.java.JavaControllerWriter;
import com.generator.writer.java.JavaRepositoryWriter;
import com.generator.writer.java.JavaServiceWriter;
import com.generator.writer.java.generic.JavaEntityWriter;
import com.generator.writer.java.generic.JavaGenericControllerWriter;
import com.generator.writer.java.generic.JavaGenericRepositoryWriter;
import com.generator.writer.java.generic.JavaGenericServiceWriter;

public class Application {
//	private SpringProperties springProperties = new SpringProperties();

	public static void main(String[] args) {
		Properties properties = new Properties();
		File file = new File("./");
		for(File f : file.listFiles()) {
			System.out.println(f);
		}
		System.out.println(file.getPath());
			try (InputStream input = new FileInputStream("./application.properties")) {
				properties.load(input);
				System.out.println(properties.get("bootVersion"));
			}
			catch (Exception e) {
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
	
	private static boolean projectExsists() {
		
		return false;
	}
	
	private static void downloadAndUnpackSpringClothProject() {
		BaseProjectCreator projectCreator = new BaseProjectCreator();
		projectCreator.downloadProjcet();
//		projectCreator.unpackProject();
	}
	
	private static void generateApp() {
		AppModel appModel =  new AppModel(null, null);
		List<Writer> writers = initWriters();
		for(Writer writer : writers) {
			try {
				writer.create(appModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
