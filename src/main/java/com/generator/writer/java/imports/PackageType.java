package com.generator.writer.java.imports;

import java.util.Arrays;

import com.generator.writer.utils.WriterUtils;

public enum PackageType {
	SPECIFICATION("specification"),
	UTILS("utils"),
	EXCEPTION("exceptions");

	private String code;

	private PackageType(String code) {
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
