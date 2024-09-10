package com.generator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.properties.GeneratorProperties;
import com.generator.model.properties.SpringProperties;

public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class);
	private static SpringProperties springProperties = new SpringProperties(loadProperties());
	private static GeneratorProperties generatorProperties = loadGeneratorProperties();

	public static void main(String[] args) {
		try {
			logger.info("Generating application...");
			ApplicationGenerator.generateApp();
		} catch (Exception e) {
			logger.error(e);
			ErrorLogHandler.writeErrorLog(e);
			e.printStackTrace();
		}
		logger.info("Application generated successfuly!");

	}

	static Properties loadProperties() {
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

	static GeneratorProperties loadGeneratorProperties() {
		logger.info("Loading application.properties...");
		try (InputStream input = new FileInputStream("./application.properties")) {
			GeneratorProperties generatorProperties = PropertiesLoader.loadProperties(input, GeneratorProperties.class);
			logger.info("Application properties loaded successfuly!");
			return generatorProperties;
		} catch (Exception e) {
			logger.error("An error has occured: " + e);
			e.printStackTrace();
			return null;
		}
	}

	public static SpringProperties getSpringProperties() {
		return springProperties;
	}

	public static void setSpringProperties(SpringProperties springProperties) {
		Application.springProperties = springProperties;
	}

	public static GeneratorProperties getGeneratorProperties() {
		return generatorProperties;
	}

}
