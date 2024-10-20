package com.generator.writer.java.placeholders;

import java.util.Arrays;

import com.generator.writer.utils.WriterUtils;

public enum JavaPackageType {
	SPECIFICATION("specification"),
	UTILS("utils"),
	DTO("dto"),
	CONTROLLER("controller"),
	CONFIG("config"),
	SECURITY("config.security"),
	EXCEPTION("exceptions");
	private String code;

	private JavaPackageType(String code) {
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
	
	public static void loadProjectPackages() {
		Arrays.asList(values()).forEach(appPackage ->{
			appPackage.setCode(WriterUtils.getImportDefaultPackage() + "." + appPackage.getCode());
		});
	}
}
