package com.generator;

import com.generator.model.AppModel;
import com.generator.reader.XMLModelReader;
import com.generator.writer.java.components.basic.JavaBasicControllerWriter;
import com.generator.writer.java.components.basic.JavaBasicRepositoryWriter;
import com.generator.writer.java.components.basic.JavaBasicServiceWriter;

public class CustomAppWriter {
	private static JavaBasicRepositoryWriter basicRepositoryWriter = new JavaBasicRepositoryWriter();
	private static JavaBasicServiceWriter basicServiceWriter = new JavaBasicServiceWriter();
	private static JavaBasicControllerWriter basicControllerWriter = new JavaBasicControllerWriter();
	private static AppModel appModel = XMLModelReader.readModel();
	
	public static void main(String[] args) {
		try {
			System.out.println("Start");
//			basicServiceWriter.create(appModel);
//			basicRepositoryWriter.create(appModel);
			basicControllerWriter.create(appModel);
			System.out.println("End");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
