package com.generator.model.enums;

public enum AttributeType {
	STRING("String"),
	INTEGER("Integer"),
	DOUBLE("Double"),
	LONG("Long"),
	DATE("Date"),
	BOOLEAN("Boolean"),
	ENUM("");

	private String code;

	private AttributeType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
