package com.generator.model.enums;

public enum FetchType implements CodeEnum<FetchType> {
	LAZY("lazy", "fetch =  FetchType.LAZY"),
	EAGER("eager", "fetch =  FetchType.EAGER"),
	NULL("", "");

	private String code;
	private String generatorCode;

	FetchType(String code, String generatorCode) {
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
	public FetchType fromCode(String code) {
		for (FetchType fetchType : FetchType.values()) {
			if (fetchType.getCode().equals(code)) return fetchType;
		}
		return NULL;
	}
}