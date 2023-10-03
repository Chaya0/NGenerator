package com.generator.model.enums;

public enum FetchType {
	LAZY("lazy", "fetch =  FetchType.LAZY"),
	EAGER("eager", "fetch =  FetchType.EAGER");

	private String code;
	private String generatorCode;

	FetchType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	public String getCode() {
		return code.toLowerCase();
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	public static FetchType fromCode(String code) {
		for (FetchType fetchType : FetchType.values()) {
			if (fetchType.getCode().equals(code)) return fetchType;
		}
		return null;
	}
}