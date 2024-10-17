package com.generator.writer.java.placeholders;

import com.generator.Application;
import com.generator.util.StringUtils;

public enum ComponentAffix {
	APP_NAME("");

	private String code;

	private ComponentAffix(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPackageImport() {
		return "package " + code + ";";
	}

	public String getPackageImport(String packagePath) {
		return "package " + packagePath + ";";
	}
	
	public static void loadComponentAffixes() {
		for(ComponentAffix componentAffix : ComponentAffix.values()) {
			switch(componentAffix) {
			case APP_NAME:
				componentAffix.setCode(StringUtils.uppercaseFirst(Application.getSpringProperties().getName()));
				break;
			default:
                throw new IllegalStateException("Unexpected value: " + componentAffix);
			}
		}
	}
}
