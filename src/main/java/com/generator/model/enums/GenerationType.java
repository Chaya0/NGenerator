package com.generator.model.enums;

public enum GenerationType {
	TABLE("table", "@GeneratedValue(strategy = GenerationType.TABLE)"),
	SEQUENCE("sequence", "@GeneratedValue(strategy = GenerationType.SEQUENCE)"),
	IDENTITY("identity", "@GeneratedValue(strategy = GenerationType.IDENTITY)"),
	AUTO("auto", "@GeneratedValue(strategy = GenerationType.AUTO)");

	private String code;
	private String generatorCode;

	GenerationType(String code, String generatorCode) {
		this.code = code;
		this.generatorCode = generatorCode;
	}

	public String getCode() {
		return code;
	}

	public String getGeneratorCode() {
		return generatorCode;
	}

	public static GenerationType fromCode(String code) {
		for (GenerationType generationType : GenerationType.values()) {
			if (generationType.getCode().equals(code)) return generationType;
		}
		return null;
	}
}
