package com.generator.model.enums;

public enum FrontendType implements CodeEnum<FrontendType> {
	ANGULAR("angular"),
	NULL("");

	private String code;

	private FrontendType(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public FrontendType fromCode(String code) {
		for (FrontendType frontendType : FrontendType.values()) {
			if (frontendType.getCode().equals(code)) return frontendType;
		}
		return NULL;
	}

}
