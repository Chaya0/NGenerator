package com.generator.model.enums;

public enum AuthorisationType {
	JWT("jwt"),
	JWT_COOKIE("jwt_cookie"),
	NULL("");

	private String code;

	private AuthorisationType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static AuthorisationType fromCode(String code) {
		for (AuthorisationType authorisationType : AuthorisationType.values()) {
			if (authorisationType.getCode().equals(code)) return authorisationType;
		}
		return NULL;
	}

}
