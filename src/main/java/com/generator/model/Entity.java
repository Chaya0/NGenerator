package com.generator.model;

import java.util.List;

import com.generator.model.enums.InheritanceType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "inherits", required = false)
	private String inherits;
	@XmlElementWrapper(name = "relations", required = false)
    @XmlElement(name = "relations")
	private List<Relation> relations;
	@XmlElementWrapper(name = "attributes", required = false)
    @XmlElement(name = "attributes")
	private List<Attribute> attributes;
	@XmlAttribute(name = "inheritance-type", required = false)
	private InheritanceType inheritanceType;

	public Entity() {
	}

	public Entity(List<Relation> relations, List<Attribute> attributes, String name) {
		super();
		this.relations = relations;
		this.attributes = attributes;
		this.name = name;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InheritanceType getInheritanceType() {
		return inheritanceType;
	}

	public void setInheritanceType(InheritanceType inheritanceType) {
		this.inheritanceType = inheritanceType;
	}

	public String getInherits() {
		return inherits;
	}

	public void setInherits(String inherits) {
		this.inherits = inherits;
	}

}
