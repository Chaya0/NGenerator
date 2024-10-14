package com.generator.writer.java.imports;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.generator.writer.utils.WriterUtils;

public enum ImportType {
	JAVA_TIME("java.time"),
	JAVA_TIME_FORMAT("java.time.format"),
	JAVA_IO("java.io"),
	JAVA_UTIL("java.util"),
	JAVA_LANG_REFLECT("java.lang.reflect"),
	JAVA_TEXT("java.text"),

	FASTERXML_JACKSON_CORE("com.fasterxml.jackson.core"),
	FASTERXML_JACKSON_DATABIND("com.fasterxml.jackson.databind"),
	FASTERXML_JACKSON_DATABIND_NODE("com.fasterxml.jackson.databind.node"),
	FASTERXML_JACKSON_DATATYPE("com.fasterxml.jackson.datatype"),
	FASTERXML_JACKSON_ANNOTATION("com.fasterxml.jackson.annotation"),
	FASTERXML_JACKSON_DATABIND_ANNOTATION("com.fasterxml.jackson.databind.annotation"),

	SPRING_PERSISTENCE("jakarta.persistence"),
	SPRING_PERSISTENCE_CRITERIA("jakarta.persistence.criteria"),
	SPRING_PERSISTENCE_METAMODEL("jakarta.persistence.metamodel"),
	SPRING_VALIDATION("jakarta.validation"),
	SPRING_SERVLET("jakarta.servlet"),
	SPRING_DATA_DOMAIN("org.springframework.data.domain"),
	SPRING_DATA_JPA_DOMAIN("org.springframework.data.jpa.domain"),
	SPRING_LANG("org.springframework.lang"),
	SPRING_STEREOTYPE("org.springframework.stereotype"),
	SPRING_DAO("org.springframework.dao"),
	SPRING_HTTP("org.springframework.http"),
	SPRING_WEB("org.springframework.web.bind.annotation"),
	SPRING_SECURITY("org.springframework.security.core"),
	SPRING_CONTEXT("org.springframework.context"),
	SPRING_SCHEDULING("org.springframework.scheduling.quartz"),
	SPRING_BEANS("org.springframework.beans.factory.annotation"),
	SPRING_CORE("org.springframework.core"),
	SPRING_IO("org.springframework.core.io"),
	SPRING_QUARTZ("org.quartz"),

	// SecurityImports
	SPRING_SECURITY_ACCESS("org.springframework.security.access"),
	SPRING_SECURITY_CORE("org.springframework.security.core"),
	SPRING_SECURITY_CORE_CONTECT("org.springframework.security.core.context"),
	SPRING_SECURITY_CORE_USERDETAILS("org.springframework.security.core.userdetails"),
	SPRING_SECURITY_CRYPTO_BCRYPT("org.springframework.security.crypto.bcrypt"),
	SPRING_SECURITY_CRYPTO_PASSWORD("org.springframework.security.crypto.password"),

	APP_SERVICE("service"),
	APP_REPOSITORY("repository"),
	APP_MODEL("model"),
	APP_EXCEPTION("exceptions"),
	APP_UTILS("utils"),
	APP_CONFIG("config"),
	APP_ENUM("model.enums"),
	APP_CONTROLLER("controller"),
	APP_SPECIFICATION("specification"),
	APP_SECURITY("config.security"),

	LOMBOK("lombok"),
	APACHE_COMMONS("org.apache.commons.lang3"),
	SWAGGER("io.swagger.v3.oas.annotations.security"),
	LOGGER("org.apache.logging.log4j");

	private String code;

	private ImportType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImportCode() {
		return "import " + code + ".";
	}

	public String getImportCode(String specificClass) {
		return "import " + code + "." + specificClass + ";";
	}

	public String getFullImportCode() {
		return "import " + code + ".*;";
	}

	public static String getSpecificImportCodeFor(String packageName, String className) {
		return "import " + packageName + "." + className + ";";
	}

	private static List<ImportType> getAppRelatedImportTypes() {
		return Arrays.stream(values()).filter(item -> item.name().startsWith("APP_")).collect(Collectors.toList());
	}
	
	public static void loadProjectImports() {
		getAppRelatedImportTypes().forEach(appImport ->{
			appImport.setCode(WriterUtils.getImportDefaultPackage() + "." + appImport.getCode());
		});
	}
}
