package com.generator.model.enums;

public enum InheritanceType implements CodeEnum<InheritanceType> {
	JOINED("joined", "@Inheritance(strategy = InheritanceType.JOINED)"),
	TABLE_PER_CLASS("tablePerClass", "@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)"),
	SINGLE_TABLE("singleTable", "@Inheritance(strategy = InheritanceType.SINGLE_TABLE)"),
	NULL("", "");

	private String code;
	private String generatorCode;

	InheritanceType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	@Override
	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	@Override
	public InheritanceType fromCode(String code) {
		for (InheritanceType inheritanceType : InheritanceType.values()) {
			if (inheritanceType.getCode().equals(code)) return inheritanceType;
		}
		return NULL;
	}

}
