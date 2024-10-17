package com.generator.model.enums.properties;

public enum DatabaseType {
	MYSQL("MySQL8Dialect"), 
	POSTGRES("PostgreSQLDialect");
	
	private String code;

	private DatabaseType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
