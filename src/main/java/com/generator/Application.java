package com.generator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.SpringProperties;

public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class);
	private static SpringProperties springProperties = new SpringProperties(loadProperties());

	public static void main(String[] args) throws Exception {
		try {
			logger.info("Generating application...");
			ApplicationGenerator.generateApp();			
		}catch (Exception e) {
			logger.error(e);
			ErrorLogHandler.writeErrorLog(e);
		}
		logger.info("Application generated successfuly!");

	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		logger.info("Loading application.properties...");
		try (InputStream input = new FileInputStream("./application.properties")) {
			properties.load(input);
		} catch (Exception e) {
			logger.error("An error has occured: " + e);
			e.printStackTrace();
		}
		logger.info("Application properties loaded successfuly!");
		return properties;
	}

	public static SpringProperties getSpringProperties() {
		return springProperties;
	}

}
