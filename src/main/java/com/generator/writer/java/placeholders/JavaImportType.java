package com.generator.writer.java.placeholders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.generator.writer.utils.WriterUtils;

public enum JavaImportType {
	JAVA_TIME("java.time"),
	JAVA_TIME_FORMAT("java.time.format"),
	JAVA_IO("java.io"),
	JAVA_UTIL("java.util"),
	JAVA_LANG_REFLECT("java.lang.reflect"),
	JAVA_TEXT("java.text"),

	FASTERXML_JACKSON_CORE("com.fasterxml.jackson.core"),
	FASTERXML_JACKSON_DATABIND("com.fasterxml.jackson.databind"),
	FASTERXML_JACKSON_DATABIND_MODULE("com.fasterxml.jackson.databind.module"),
	FASTERXML_JACKSON_DATABIND_NODE("com.fasterxml.jackson.databind.node"),
	FASTERXML_JACKSON_DATATYPE("com.fasterxml.jackson.datatype"),
	FASTERXML_JACKSON_ANNOTATION("com.fasterxml.jackson.annotation"),
	FASTERXML_JACKSON_DATABIND_ANNOTATION("com.fasterxml.jackson.databind.annotation"),

	SPRING_PERSISTENCE("jakarta.persistence"),
	SPRING_PERSISTENCE_CRITERIA("jakarta.persistence.criteria"),
	SPRING_PERSISTENCE_METAMODEL("jakarta.persistence.metamodel"),
	SPRING_VALIDATION("jakarta.validation"),
	SPRING_VALIDATION_CONSTRAINTS("jakarta.validation.constraints"),
	SPRING_SERVLET("jakarta.servlet"),
	SPRING_SERVLET_HTTP("jakarta.servlet.http"),
	SPRING_DATA_DOMAIN("org.springframework.data.domain"),
	SPRING_DATA_JPA_DOMAIN("org.springframework.data.jpa.domain"),
	SPRING_DATA_JPA("org.springframework.data.jpa"),
	SPRING_DATA_JPA_REPOSITORY("org.springframework.data.jpa.repository"),
	SPRING_DATA_JPA_REPOSITORY_CONFIG("org.springframework.data.jpa.repository.config"),
	SPRING_LANG("org.springframework.lang"),
	SPRING_STEREOTYPE("org.springframework.stereotype"),
	SPRING_DAO("org.springframework.dao"),
	SPRING_HTTP("org.springframework.http"),
	SPRING_WEB("org.springframework.web"),
	SPRING_WEB_BIND_ANNOTATION("org.springframework.web.bind.annotation"),
	SPRING_WEB_FILTER("org.springframework.web.filter"),
	SPRING_CONTEXT("org.springframework.context"),
	SPRING_CONTEXT_ANNOTATION("org.springframework.context.annotation"),
	SPRING_SCHEDULING("org.springframework.scheduling.quartz"),
	SPRING_BEANS("org.springframework.beans.factory.annotation"),
	SPRING_CORE("org.springframework.core"),
	SPRING_CORE_ANNOTATION("org.springframework.core.annotation"),
	SPRING_IO("org.springframework.core.io"),
	SPRING_QUARTZ("org.quartz"),

	// SecurityImports
	SPRING_SECURITY("org.springframework.security.core"),
	SPRING_SECURITY_AUTHENTICATION("org.springframework.security.authentication"),
	SPRING_SECURITY_ACCESS("org.springframework.security.access"),
	SPRING_SECURITY_PROVISIONING("org.springframework.security.provisioning"),
	SPRING_SECURITY_WEB("org.springframework.security.web"),
	SPRING_SECURITY_WEB_AUTHENTICATION("org.springframework.security.web.authentication"),
	SPRING_SECURITY_CONFIG("org.springframework.security.config"),
	SPRING_SECURITY_CONFIG_HTTP("org.springframework.security.config.http"),
	SPRING_SECURITY_CONFIG_ANNOTATION("org.springframework.security.config.annotation"),
	SPRING_SECURITY_CONFIG_ANNOTATION_AUTHENTICATION("org.springframework.security.config.annotation.authentication"),
	SPRING_SECURITY_CONFIG_ANNOTATION_AUTHENTICATION_CONFIGURATION("org.springframework.security.config.annotation.authentication.configuration"),
	SPRING_SECURITY_CONFIG_ANNOTATION_WEB("org.springframework.security.config.annotation.web"),
	SPRING_SECURITY_CONFIG_ANNOTATION_WEB_BUILDERS("org.springframework.security.config.annotation.web.builders"),
	SPRING_SECURITY_CONFIG_ANNOTATION_WEB_CONFIGURATION("org.springframework.security.config.annotation.web.configuration"),
	SPRING_SECURITY_CONFIG_ANNOTATION_WEB_CONFIGURERS("org.springframework.security.config.annotation.web.configurers"),
	SPRING_SECURITY_CORE("org.springframework.security.core"),
	SPRING_SECURITY_CORE_CONTEXT("org.springframework.security.core.context"),
	SPRING_SECURITY_CORE_USERDETAILS("org.springframework.security.core.userdetails"),
	SPRING_SECURITY_CRYPTO_BCRYPT("org.springframework.security.crypto.bcrypt"),
	SPRING_SECURITY_CRYPTO_PASSWORD("org.springframework.security.crypto.password"),
	
	SPRING_WEB_SERVLET_CONFIG_ANNOTATION("org.springframework.web.servlet.config.annotation"),

	JWT("io.jsonwebtoken"),
	
	APP_SERVICE("service"),
	APP_DTO("dto"),
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
	SWAGGER("io.swagger.v3.oas.annotations"),
	SWAGGER_SECURITY("io.swagger.v3.oas.annotations.security"),
	SWAGGER_INFO("io.swagger.v3.oas.annotations.info"),
	LOGGER("org.apache.logging.log4j");

	private String code;

	private JavaImportType(String code) {
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

	private static List<JavaImportType> getAppRelatedImportTypes() {
		return Arrays.stream(values()).filter(item -> item.name().startsWith("APP_")).collect(Collectors.toList());
	}
	
	public static void loadProjectImports() {
		getAppRelatedImportTypes().forEach(appImport ->{
			appImport.setCode(WriterUtils.getImportDefaultPackage() + "." + appImport.getCode());
		});
	}
}
