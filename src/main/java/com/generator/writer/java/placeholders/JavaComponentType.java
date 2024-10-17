package com.generator.writer.java.placeholders;

import java.util.Arrays;

public enum JavaComponentType {

	APP_EXCEPTION(ComponentAffix.APP_NAME,"Exception");

	private String code;
	private ComponentAffix componentPrefix;
	private ComponentAffix componentSuffix;

	private JavaComponentType(String code) {
		this.code = code;
	}

	private JavaComponentType(ComponentAffix componentPrefix, String code, ComponentAffix componentSuffix) {
		this.componentPrefix = componentPrefix;
		this.code = code;
		this.componentSuffix = componentSuffix;
	}

	private JavaComponentType(ComponentAffix componentPrefix, String code) {
		this.componentPrefix = componentPrefix;
		this.code = code;
	}

	private JavaComponentType(String code, ComponentAffix componentSuffix) {
		this.code = code;
		this.componentSuffix = componentSuffix;
	}

	public String getCode() {
		return code;
	}

	public String getComponentPrefix() {
		return componentPrefix == null ? "" : componentPrefix.getCode();
	}

	public String getComponentSuffix() {
		return componentSuffix == null ? "" : componentSuffix.getCode();
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComponentName(String packagePath) {
		return "package " + packagePath + ";";
	}

	public static void loadProjectComponents() {
		Arrays.asList(values()).forEach(projectComponent -> {
			projectComponent.setCode(projectComponent.getComponentPrefix() + projectComponent.getCode() + projectComponent.getComponentSuffix());
		});
	}
}
