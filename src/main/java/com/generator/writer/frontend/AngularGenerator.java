package com.generator.writer.frontend;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.Application;
import com.generator.writer.frontend.angular.AngularBashUtil;
import com.generator.writer.utils.WriterUtils;

public class AngularGenerator {
	private static final Logger logger = LogManager.getLogger(AngularGenerator.class);
	
	private static void createFrontendAppIfItDoesntExists() {

		String path = WriterUtils.getFrontendApplicationPath();
		File file = new File(path);
		File rootAppFile = new File(WriterUtils.getFrontendRootPackagePath());
		if (!rootAppFile.exists()) {
			file.mkdirs();
			logger.info("Application folder is not detected. New insatance of application is being created on path : " + path);
			System.out.println("created file");
			try {
				AngularBashUtil.createNewAngularApp(path, Application.getSpringProperties().getName(), "18");
				System.out.println("New Application genereted with neccessary dependencies!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			File frontApp = new File(WriterUtils.getFrontendRootPackagePath());
			System.out.println(frontApp.exists());
			System.out.println(frontApp.getAbsolutePath());
			System.out.println("Application already exsists!");
		}
	}
	
	public static void main(String[] args) {
		createFrontendAppIfItDoesntExists();
	}
}
