package com.generator.model.enums;

public enum CascadeType implements CodeEnum<CascadeType> {
	ALL("all", "cascade = CascadeType.ALL"),
	PERSIST("persist", "cascade = CascadeType.PERSIST"),
	MERGE("merge", "cascade = CascadeType.MERGE"),
	REMOVE("remove", "cascade = CascadeType.REMOVE"),
	REFRESH("refresh", "cascade = CascadeType.REFRESH"),
	DETACH("detach", "cascade = CascadeType.DETACH"),
	NULL("", "");

	private String code;
	private String generatorCode;

	CascadeType(String code, String generatorCode) {
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
	public CascadeType fromCode(String code) {
		for (CascadeType cascadeType : CascadeType.values()) {
			if (cascadeType.getCode().equals(code)) return cascadeType;
		}
		return NULL;
	}
}
