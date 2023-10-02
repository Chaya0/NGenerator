package com.generator.model.enums;

public enum RelationType {
	ONE_TO_MANY("oneToMany", "@OneToMny"), 
	MANY_TO_MANY("manyToMany", "@ManyToMany"), 
	MANY_TO_ONE("manyToOne", "@ManyToOne"), 
	ONE_TO_ONE("oneToOne", "@OneToOne");
	
	private String code;
	private String annotationCode;
	
	RelationType(String code, String annotationCode) {
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
