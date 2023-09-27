package com.generator.model.enums;

public enum RelationType {
	ONE_TO_MANY("one_to_many", "@OneToMny"), 
	MANY_TO_MANY("many_to_many", "@ManyToMany"), 
	MANY_TO_ONE("many_to_one", "@ManyToOne"), 
	ONE_TO_ONE("one_to_one", "@OneToOne");
	
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
