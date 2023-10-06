package com.generator.model.enums;

public enum RelationType {
	ONE_TO_MANY("oneToMany", "@OneToMany"),
	MANY_TO_MANY("manyToMany", "@ManyToMany"),
	MANY_TO_ONE("manyToOne", "@ManyToOne"),
	ONE_TO_ONE("oneToOne", "@OneToOne"),
	NULL("","");

	private String code;
	private String generatorCode;

	RelationType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	public static RelationType fromCode(String code) {
		for (RelationType relationType : RelationType.values()) {
			if (relationType.getCode().equals(code)) return relationType;
		}
		return NULL;
	}
}
