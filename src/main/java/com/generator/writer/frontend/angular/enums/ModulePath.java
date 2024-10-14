package com.generator.writer.frontend.angular.enums;

public enum ModulePath {
	ANGULAR_CORE("@angular/core"),
    ANGULAR_COMMON("@angular/common"),
    PRIME_NG("primeng/primeng"),
    CUSTOM_PATH("path/to/custom/module"),
    APP_COMPONENTS("src/app/components"),
    APP_SERVICES("src/app/services");
	
	private final String code;

    ModulePath(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
