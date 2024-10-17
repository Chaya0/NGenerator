package com.generator.writer.java.placeholders;

public enum GeneratorTemplatePlaceholderType {
	/**
	 * Generator package.
	 */
	GP("#GP"),
	/**
	 * Generator import.
	 */
	GI("#GI"),
	/**
	 * Generator component.
	 */
	GC("#GC");

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
