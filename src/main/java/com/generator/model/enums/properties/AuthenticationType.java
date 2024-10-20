package com.generator.model.enums.properties;

public enum AuthenticationType {
	JWT("jwt"),
	JWT_COOKIE("jwtCookie"),
	JWT_COOKIE_SECURE("jwtCookieSecure"),
	NULL(""), 
	;

	private String code;

	private AuthenticationType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static AuthenticationType fromCode(String code) {
		for (AuthenticationType authenticationType : AuthenticationType.values()) {
			if (authenticationType.getCode().equals(code)) return authenticationType;
		}
		return NULL;
	}

}
