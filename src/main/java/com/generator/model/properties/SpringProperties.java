package com.generator.model.properties;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.generator.Application;

import lombok.Data;

/*
 * Might be advanced into loading properties from XML.
 */
@Data
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
	private String datasourceUrl;
	private String datasourceUsername;
	private String datasourcePassword;
	
	private static List<String> defaultDependecies = Arrays.asList("lombok", "data-jpa", "validation", "web", "jdbc", "mysql", "security");

	public SpringProperties(Properties properties) {
		type = "maven-project";
		language = "java";
		packaging = "jar";
		dependencies = defaultDependecies;
		bootVersion = properties.getProperty("bootVersion", "3.1.4");
		baseDir = properties.getProperty("baseDir", "myApp");
		groupId = properties.getProperty("groupId", "com.myApp");
		artifactId = properties.getProperty("artifactId", "myApp");
		name = properties.getProperty("name", "myApp");
		description = properties.getProperty("description", "");
		packageName = properties.getProperty("packageName", "com.myApp.myApp");
		javaVersion = properties.getProperty("javaVersion", "11");
		projectPath = properties.getProperty("projectPath");
		datasourceUrl = properties.getProperty("datasourceUrl", "");
		datasourceUsername = properties.getProperty("datasourceUsername", "");
		datasourcePassword = properties.getProperty("datasourcePassword", "");
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
			if(Application.getGeneratorProperties().isGenerateSwaggerComponent()) {
				
			}
			if(Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				
			}
			if(Application.getGeneratorProperties().isGenerateQuartzComponents()) {
				
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		return stringBuilder.toString();
	}

}
