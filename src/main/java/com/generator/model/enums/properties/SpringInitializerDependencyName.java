package com.generator.model.enums.properties;

public enum SpringInitializerDependencyName {
	QUARTZ("quartz"),
	JWT("jjwt");

	private String code;

	private SpringInitializerDependencyName(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
