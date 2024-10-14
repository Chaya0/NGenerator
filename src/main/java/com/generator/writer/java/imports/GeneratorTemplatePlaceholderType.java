package com.generator.writer.java.imports;

public enum GeneratorTemplatePlaceholderType {
	/**
	 * Generator package.
	 */
	GP("#GP"),
	/**
	 * Generator import.
	 */
	GI("#GI");

	private String code;

	private GeneratorTemplatePlaceholderType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
