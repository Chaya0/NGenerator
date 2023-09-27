package com.generator.model.enums;

public enum InheritanceType {
	
	MAPPED_SUPERCLASS("MappedSuperclass");

	private String code;

	InheritanceType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
