package com.generator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.generator.model.SpringProperties;

public class Application {
	private static SpringProperties springProperties = new SpringProperties(loadProperties());

	public static void main(String[] args) {
		ApplicationGenerator.generateApp();
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream input = new FileInputStream("./application.properties")) {
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static SpringProperties getSpringProperties() {
		return springProperties;
	}

}
