package com.generator.model;

import com.generator.model.enums.AttributeType;
import com.generator.reader.adapter.AttributeTypeXmlAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Attribute {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "type", required = true)
	@XmlJavaTypeAdapter(AttributeTypeXmlAdapter.class)
	private AttributeType type;
	@XmlAttribute(name = "enumName")
	private String enumName;
	@XmlAttribute(name = "nullable")
	private boolean nullable = true;
	@XmlAttribute(name = "unique")
	private boolean unique = false;

	public Attribute() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", type=" + type + ", enumName=" + enumName + ", nullable=" + nullable + ", unique=" + unique + "]";
	}
}
