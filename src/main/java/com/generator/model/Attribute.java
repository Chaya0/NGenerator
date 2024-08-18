package com.generator.model;

import com.generator.model.enums.AttributeType;
import com.generator.reader.adapter.AttributeTypeXmlAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	/**
	 * System variable represents attribute that should not be included in DTO's or
	 * request sent to the frontend...
	 * It's attribute that's important only to the bussines logic of the application
	 */
	@XmlAttribute(name = "systemVariable")
	private boolean systemVariable = false;

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", type=" + type + ", enumName=" + enumName + ", nullable=" + nullable + ", unique=" + unique + "]";
	}
}
