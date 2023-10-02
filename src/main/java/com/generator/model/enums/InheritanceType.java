package com.generator.model.enums;

public enum InheritanceType {
	
	JOINED("joined", "@Inheritance(strategy = InheritanceType.JOINED)"),
	TABLE_PER_CLASS("tablePerClass", "@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)"),
	SINGLE_TABLE("singleTable", "@Inheritance(strategy = InheritanceType.SINGLE_TABLE)");

	private String code;
	private String annotationCode;

	InheritanceType(String code, String annotationCode) {
		this.code = code;
		this.annotationCode = annotationCode;
	}

	public String getCode() {
		return code;
	}

	public String getAnnotationCode() {
		return annotationCode;
	}
	
	

}
