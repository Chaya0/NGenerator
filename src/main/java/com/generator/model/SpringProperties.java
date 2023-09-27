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
//	@XmlAttribute(required =  true)
	private String bootVersion;
//	@XmlAttribute(required =  true)
	private String baseDir;
//	@XmlAttribute(required =  true)
	private String groupId;
//	@XmlAttribute(required =  true)
	private String artifactId;
//	@XmlAttribute(required =  true)
	private String name;
//	@XmlAttribute(required =  true)
	private String description;
//	@XmlAttribute(required =  true)
	private String packageName;
//	@XmlAttribute(required =  true)
	private String javaVersion;
//	@XmlAttribute(required =  true)
	private String projectPath;

	public SpringProperties(Properties properties) {
		type = "maven-project";
		language = "java";
		packaging = "jar";
		dependencies = Arrays.asList("lombok");
		bootVersion = properties.getProperty("bootVersion");
		baseDir = properties.getProperty("baseDir");
		groupId = properties.getProperty("groupId");
		artifactId = properties.getProperty("artifactId");
		name = properties.getProperty("name");
		description = properties.getProperty("description");
		packageName = properties.getProperty("packageName");
		javaVersion = properties.getProperty("javaVersion");
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

}
