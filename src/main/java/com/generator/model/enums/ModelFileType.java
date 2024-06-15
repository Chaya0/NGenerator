package com.generator.model.enums;

public enum ModelFileType {
	XML("xml"),
	JSON("json"),
	JAVA_LIBRARY("javaLibrary");

	private String code;

	private ModelFileType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
