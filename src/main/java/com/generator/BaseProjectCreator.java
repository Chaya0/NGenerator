package com.generator;

import java.util.Properties;
import java.util.zip.ZipFile;

import com.generator.model.SpringProperties;

public class BaseProjectCreator {

	public void generateProjectBase() {
		
	}

	public void unpackProject(ZipFile zipFile) {
		// TODO Auto-generated method stub

	}

	public void downloadProjcet() {
		// TODO Auto-generated method stub

	}

	private String createStartSpringApi() {
		SpringProperties springProperties = new SpringProperties(new Properties());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("https://start.spring.io/starter.zip?type=" + springProperties.getType());
		stringBuilder.append("&language=" + springProperties.getLanguage());
		stringBuilder.append("&bootVersion=" + springProperties.getBootVersion());
		stringBuilder.append("&baseDir=" + springProperties.getBaseDir());
		stringBuilder.append("&groupId=" + springProperties.getGroupId());
		stringBuilder.append("&artifactId=" + springProperties.getArtifactId());
		stringBuilder.append("&name=" + springProperties.getName());
		stringBuilder.append("&description=" + springProperties.getDescription());
		stringBuilder.append("&packageName=" + springProperties.getPackageName());
		stringBuilder.append("&packaging=" + springProperties.getPackaging());
		stringBuilder.append("&javaVersion=" + springProperties.getJavaVersion());
		stringBuilder.append("&dependencies=");
		if (!springProperties.getDependencies().isEmpty()) {
			for (String dependency : springProperties.getDependencies()) {
				stringBuilder.append(dependency + ",");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		return stringBuilder.toString();
	}

}
