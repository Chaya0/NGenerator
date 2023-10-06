package com.generator.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class EnumModel {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlElementWrapper(name = "values")
	@XmlElement(name = "value", required = true)
	private List<String> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "EnumModel [name=" + name + ", values=" + values + "]";
	}
}
