package com.generator.writer.frontend;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.Application;
import com.generator.model.AppModel;
import com.generator.reader.XMLModelReader;
import com.generator.writer.frontend.angular.AngularBashUtil;
import com.generator.writer.frontend.angular.components.AngularFetureWriter;
import com.generator.writer.frontend.angular.components.pages.AngularPagesWriter;
import com.generator.writer.frontend.angular.core.AngularCoreComponentsWriter;
import com.generator.writer.frontend.angular.initialization.AngularAppComponentWriter;
import com.generator.writer.frontend.angular.initialization.AngularInitializationWriter;
import com.generator.writer.frontend.angular.model.AngularEntityModelWriter;
import com.generator.writer.frontend.angular.pages.AngularTemplatePagesWriter;
import com.generator.writer.frontend.angular.shared.AngularSharedComponentsWriter;
import com.generator.writer.frontend.angular.utils.AngularEnvironmentWriter;
import com.generator.writer.frontend.angular.utils.AngularRoutesWriter;
import com.generator.writer.frontend.angular.utils.LocalizationWriter;
import com.generator.writer.utils.WriterUtils;

public class AngularGenerator {
	private static final Logger logger = LogManager.getLogger(AngularGenerator.class);
	
	private static void createFrontendAppIfItDoesntExists() {
		AppModel model =  XMLModelReader.readModel();
		String path = WriterUtils.getFrontendApplicationNewAppPath();
		File file = new File(path);
		File rootAppFile = new File(WriterUtils.getFrontendRootPackagePath());
		if (!rootAppFile.exists()) {
			file.mkdirs();
			logger.info("Application folder is not detected. New insatance of application is being created on path : " + path);
			System.out.println("Creating New Application on path: " + file.getAbsolutePath());
			try {
				AngularBashUtil.createNewAngularApp(path, Application.getSpringProperties().getName(), "18");
				AngularBashUtil.createEnvironments(WriterUtils.getFrontendApplicationPath());
				System.out.println("New Application genereted with neccessary dependencies!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			File frontApp = new File(WriterUtils.getFrontendRootPackagePath());
//			AngularBashUtil.createEnvironments(WriterUtils.getFrontendApplicationPath());
			System.out.println(frontApp.exists());
			System.out.println(frontApp.getAbsolutePath());
			System.out.println("Application already exsists!");
			
			System.out.println(model);
		}
		AngularPagesWriter angularPagesWriter = new AngularPagesWriter();
		AngularFetureWriter angularFeatureWriter = new AngularFetureWriter();
		LocalizationWriter writer = new LocalizationWriter();
		AngularEntityModelWriter angularEntityModelWriter = new AngularEntityModelWriter();
		AngularEnvironmentWriter angularEnvironmentWriter = new AngularEnvironmentWriter();
		AngularRoutesWriter angularRoutesWriter = new AngularRoutesWriter();
		AngularTemplatePagesWriter angularTemplatePagesWriter = new AngularTemplatePagesWriter();
		AngularCoreComponentsWriter angularCoreComponentsWriter = new AngularCoreComponentsWriter();
		AngularSharedComponentsWriter angularSharedComponentsWriter = new AngularSharedComponentsWriter();
		AngularInitializationWriter angularInitializationWriter = new AngularInitializationWriter();
		try {
			angularPagesWriter.create(model);
			angularFeatureWriter.create(model);
			writer.create(model);
			angularRoutesWriter.createRoutes(model);
			angularEntityModelWriter.create(model);
			angularCoreComponentsWriter.create();
			angularSharedComponentsWriter.create();
			angularEnvironmentWriter.create();
			angularTemplatePagesWriter.create();
			angularInitializationWriter.create();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		createFrontendAppIfItDoesntExists();
	}
}
