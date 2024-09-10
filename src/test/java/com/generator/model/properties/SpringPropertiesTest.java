package com.generator.model.properties;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpringPropertiesTest {

	private SpringProperties springProperties;

	@BeforeEach
	public void setUp() {
		Properties properties = new Properties();
		properties.setProperty("bootVersion", "3.2.0");
		properties.setProperty("baseDir", "testApp");
		properties.setProperty("groupId", "com.testApp");
		properties.setProperty("artifactId", "testArtifact");
		properties.setProperty("name", "TestApp");
		properties.setProperty("description", "A test application");
		properties.setProperty("packageName", "com.testApp.testApp");
		properties.setProperty("javaVersion", "17");
		properties.setProperty("projectPath", "/path/to/project");
		properties.setProperty("datasourceUrl", "jdbc:mysql://localhost:3306/testdb");
		properties.setProperty("datasourceUsername", "testuser");
		properties.setProperty("datasourcePassword", "testpass");

		springProperties = new SpringProperties(properties);
	}

	@Test
	public void testDefaultValues() {
		// Ensure default values are set correctly
		assertEquals("maven-project", springProperties.getType());
		assertEquals("java", springProperties.getLanguage());
		assertEquals("jar", springProperties.getPackaging());
		assertEquals(Arrays.asList("lombok", "data-jpa", "validation", "web", "cloud-feign", "jdbc", "mysql", "security"), springProperties.getDependencies());
		assertEquals("3.2.0", springProperties.getBootVersion());
		assertEquals("testApp", springProperties.getBaseDir());
		assertEquals("com.testApp", springProperties.getGroupId());
		assertEquals("testArtifact", springProperties.getArtifactId());
		assertEquals("TestApp", springProperties.getName());
		assertEquals("A test application", springProperties.getDescription());
		assertEquals("com.testApp.testApp", springProperties.getPackageName());
		assertEquals("17", springProperties.getJavaVersion());
		assertEquals("/path/to/project", springProperties.getProjectPath());
		assertEquals("jdbc:mysql://localhost:3306/testdb", springProperties.getDatasourceUrl());
		assertEquals("testuser", springProperties.getDatasourceUsername());
		assertEquals("testpass", springProperties.getDatasourcePassword());
	}

	@Test
	public void testSpringAppDownloadUrl() {
		String expectedUrl = "https://start.spring.io/starter.zip?" + "type=maven-project&" + "language=java&" + "bootVersion=3.2.0&" + "baseDir=testApp&" + "groupId=com.testApp&"
				+ "artifactId=testArtifact&" + "name=TestApp&" + "description=A test application&" + "packageName=com.testApp.testApp&" + "packaging=jar&" + "javaVersion=17&"
				+ "dependencies=lombok,data-jpa,validation,web,cloud-feign,jdbc,mysql,security";

		assertEquals(expectedUrl, springProperties.getSpringAppDownloadUrl());
	}

	@Test
	public void testSpringAppDownloadUrlWithEmptyDependencies() {
		springProperties.setDependencies(Arrays.asList());
		String expectedUrl = "https://start.spring.io/starter.zip?" + "type=maven-project&" + "language=java&" + "bootVersion=3.2.0&" + "baseDir=testApp&" + "groupId=com.testApp&"
				+ "artifactId=testArtifact&" + "name=TestApp&" + "description=A test application&" + "packageName=com.testApp.testApp&" + "packaging=jar&" + "javaVersion=17&" + "dependencies=";

		assertNotEquals(expectedUrl, springProperties.getSpringAppDownloadUrl());
	}
}