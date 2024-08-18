package com.generator;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesLoader {

	public static <T> T loadProperties(InputStream inputStream, Class<T> clazz) throws Exception {
		// Create an instance of the provided class
		T instance = clazz.getDeclaredConstructor().newInstance();

		// Load the properties from the InputStream
		Properties properties = new Properties();
		properties.load(inputStream);

		// Iterate over the fields of the class
		for (Field field : clazz.getDeclaredFields()) {
			String propertyName = field.getName();
			String propertyValue = properties.getProperty(propertyName);

			if (propertyValue != null) {
				// Make the field accessible if it's private
				field.setAccessible(true);

				// Convert the property value to the correct type and set it on the instance
				Object convertedValue = convertToFieldType(field, propertyValue);
				field.set(instance, convertedValue);
			}
		}

		return instance;
	}

	private static Object convertToFieldType(Field field, String propertyValue) {
		Class<?> fieldType = field.getType();
		if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
			return Boolean.parseBoolean(propertyValue);
		} else if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
			return Integer.parseInt(propertyValue);
		} else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
			return Long.parseLong(propertyValue);
		} else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
			return Double.parseDouble(propertyValue);
		} else if (fieldType.equals(String.class)) {
			return propertyValue;
		}
		throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
	}
}