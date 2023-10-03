package com.generator.model.enums;

public enum CascadeType {
	ALL("all", "cascade = CascadeType.ALL"),
	PERSIST("persist", "cascade = CascadeType.PERSIST"),
	MERGE("merge", "cascade = CascadeType.MERGE"),
	REMOVE("remove", "cascade = CascadeType.REMOVE"),
	REFRESH("refresh", "cascade = CascadeType.REFRESH"),
	DETACH("detach", "cascade = CascadeType.DETACH");

	private String code;
	private String generatorCode;

	CascadeType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}
	public static CascadeType fromCode(String code) {
		for (CascadeType cascadeType : CascadeType.values()) {
			if (cascadeType.getCode().equals(code)) return cascadeType;
		}
		return null;
	}
}
