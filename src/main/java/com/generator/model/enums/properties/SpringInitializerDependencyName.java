package com.generator.model.enums.properties;

public enum SpringInitializerDependencyName {
	QUARTZ("quartz"),
	;

	private String code;

	private SpringInitializerDependencyName(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
