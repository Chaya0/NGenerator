package com.generator.model.enums;

public enum AttributeType {
	STRING("string", "String", "string"),
	INTEGER("integer", "Integer", "number"),
	DOUBLE("double", "Double", "number"),
	LONG("long", "Long", "number"),
	DATE("date", "Date", "Date"),
	LOCAL_DATE("localDate", "LocalDate", "Date"),
	LOCAL_DATE_IME("localDateTime", "LocalDateTime", "Date"),
	BOOLEAN("boolean", "Boolean", "boolean"),
	ENUM("enum", "", ""),
	NULL("", "", "any");

	private String code;
	private String generatorCode;
	private String angularTypeCode;

	private AttributeType(String code, String generatorCode, String angularTypeCode) {
		this.code = code;
		this.generatorCode = generatorCode;
		this.angularTypeCode = angularTypeCode;
	}

	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	public String getAngularTypeCode() {
		return angularTypeCode;
	}

	public static AttributeType fromCode(String code) {
		for (AttributeType attributeType : AttributeType.values()) {
			if (attributeType.getCode().equals(code)) return attributeType;
		}
		return NULL;
	}
}
