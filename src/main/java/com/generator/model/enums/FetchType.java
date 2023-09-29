package com.generator.model.enums;

public enum FetchType {
	LAZY("lazy", "fetch =  FetchType.LAZY"),
	EAGER("eager", "fetch =  FetchType.EAGER");
	
	private String code;
	private String annotationCode;
	
	FetchType(String code, String annotationCode) {
		this.code = code;
		this.annotationCode = annotationCode;
	}

	public String getCode() {
		return code.toLowerCase();
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAnnotationCode() {
		return annotationCode;
	}

	public void setAnnotationCode(String annotationCode) {
		this.annotationCode = annotationCode;
	}
}