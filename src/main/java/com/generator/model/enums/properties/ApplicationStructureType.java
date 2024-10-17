package com.generator.model.enums.properties;

public enum ApplicationStructureType {
	MONOLITHIC("monolithic"),
	MICROSERVICES("microservices"),
	NULL("");

	private String code;

	private ApplicationStructureType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static ApplicationStructureType fromCode(String code) {
		for (ApplicationStructureType applicationStructureType : ApplicationStructureType.values()) {
			if (applicationStructureType.getCode().equals(code)) return applicationStructureType;
		}
		return NULL;
	}
}
