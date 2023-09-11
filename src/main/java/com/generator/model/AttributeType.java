package com.generator.model;

public enum AttributeType {
	STRING("String"),
	INTEGER("Integer"),
	DOUBLE("Double"),
	LONG("Long"),
	DATE("Date"),
	BOOLEAN("Boolean");

	private String description;

	private AttributeType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
