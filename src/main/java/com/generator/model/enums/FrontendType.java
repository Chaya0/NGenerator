package com.generator.model.enums;

public enum FrontendType {
	ANGULAR("angular"),
	NULL("");

	private String code;

	private FrontendType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static FrontendType fromCode(String code) {
		for (FrontendType frontendType : FrontendType.values()) {
			if (frontendType.getCode().equals(code)) return frontendType;
		}
		return NULL;
	}

}
