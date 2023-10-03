package com.generator.model;

import com.generator.model.enums.AttributeType;
import com.generator.reader.adapter.AttributeTypeXmlAdapter;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Attribute {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "type", required = true)
	@XmlJavaTypeAdapter(AttributeTypeXmlAdapter.class)
	private AttributeType attributeType;
	@XmlAttribute(name = "enumName", required = false)
	private String enumName;
	@XmlAttribute(name = "nullable", required = false)
	private boolean nullable = true;
	@XmlAttribute(name = "unique", required = false)
	private boolean unique = false;

	public Attribute() {
	}

	public Attribute(String name, AttributeType attributeType, boolean nullable, boolean unique) {
		super();
		this.name = name;
		this.attributeType = attributeType;
		this.nullable = nullable;
		this.unique = unique;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
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

}
