package com.generator.model.enums;

public interface CodeEnum<T> {
	T fromCode(String code);

	String getCode();
}