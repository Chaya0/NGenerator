package com.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class PropertiesLoaderTest {

	@Test
	public void testLoadProperties() throws Exception {
		// Given
		String propertiesContent = "name=John Doe\nage=30\nactive=true";
		InputStream inputStream = new ByteArrayInputStream(propertiesContent.getBytes());

		// When
//        TestClass instance = PropertiesLoader.loadProperties(inputStream, TestClass.class);

		// Then
//        assertEquals("John Doe", instance.getName());
//        assertEquals(30, instance.getAge());
//        assertTrue(instance.isActive());
	}
}