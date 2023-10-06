package com.generator.model.enums;

public enum AttributeType {
	STRING("string", "String"),
	INTEGER("integer", "Integer"),
	DOUBLE("double", "Double"),
	LONG("long", "Long"),
	DATE("date", "Date"),
	LOCAL_DATE("localDate", "LocalDate"),
	BOOLEAN("boolean", "Boolean"),
	ENUM("enum", ""),
	NULL("","");

	private String code;
	private String generatorCode;

	private AttributeType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	public static AttributeType fromCode(String code) {
		for (AttributeType attributeType : AttributeType.values()) {
			if (attributeType.getCode().equals(code)) return attributeType;
		}
		return NULL;
	}
}
