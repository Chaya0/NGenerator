package com.generator.model;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
 * Might be advanced into loading properties from XML.
 */
public class SpringProperties {
	private String type;
	private String language;
	private String packaging;
	private List<String> dependencies;
	private String bootVersion;
	private String baseDir;
	private String groupId;
	private String artifactId;
	private String name;
	private String description;
	private String packageName;
	private String javaVersion;
	private String projectPath;

	public SpringProperties(Properties properties) {
		type = "maven-project";
		language = "java";
		packaging = "jar";
		dependencies = Arrays.asList("lombok", "data-jpa", "validation", "web", "cloud-feign", "jdbc", "mysql", "security");
		bootVersion = properties.getProperty("bootVersion", "3.1.4");
		baseDir = properties.getProperty("baseDir", "myApp");
		groupId = properties.getProperty("groupId", "com.myApp");
		artifactId = properties.getProperty("artifactId", "myApp");
		name = properties.getProperty("name", "myApp");
		description = properties.getProperty("description", "");
		packageName = properties.getProperty("packageName", "com.myApp.myApp");
		javaVersion = properties.getProperty("javaVersion", "11");
		projectPath = properties.getProperty("projectPath");
	}

	public String getType() {
		return type;
	}

	public String getLanguage() {
		return language;
	}

	public String getPackaging() {
		return packaging;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public String getBootVersion() {
		return bootVersion;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public String getSpringAppDownloadUrl() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("https://start.spring.io/starter.zip?type=" + getType());
		stringBuilder.append("&language=" + getLanguage());
		stringBuilder.append("&bootVersion=" + getBootVersion());
		stringBuilder.append("&baseDir=" + getBaseDir());
		stringBuilder.append("&groupId=" + getGroupId());
		stringBuilder.append("&artifactId=" + getArtifactId());
		stringBuilder.append("&name=" + getName());
		stringBuilder.append("&description=" + getDescription());
		stringBuilder.append("&packageName=" + getPackageName());
		stringBuilder.append("&packaging=" + getPackaging());
		stringBuilder.append("&javaVersion=" + getJavaVersion());
		stringBuilder.append("&dependencies=");
		if (!getDependencies().isEmpty()) {
			for (String dependency : getDependencies()) {
				stringBuilder.append(dependency + ",");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		return stringBuilder.toString();
	}

}
