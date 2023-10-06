package com.generator.model;

import java.util.ArrayList;
import java.util.List;

import com.generator.model.enums.AttributeType;
import com.generator.model.enums.GenerationType;
import com.generator.model.enums.InheritanceType;
import com.generator.reader.adapter.GenerationTypeXmlAdapter;
import com.generator.reader.adapter.InheritanceTypeXmlAdapter;
import com.generator.util.StringUtils;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "inherits")
	private String inherits;
	@XmlAttribute(name = "inheritance-type")
	@XmlJavaTypeAdapter(InheritanceTypeXmlAdapter.class)
	private InheritanceType inheritanceType = InheritanceType.NULL;
	@XmlAttribute(name = "generationType")
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private GenerationType generationType = GenerationType.IDENTITY;
	/*
	 * XmlElements
	 */
	@XmlElementWrapper(name = "relations")
	@XmlElement(name = "relation")
	private List<Relation> relations = new ArrayList<>();
	@XmlElementWrapper(name = "attributes")
	@XmlElement(name = "attribute")
	private List<Attribute> attributes = new ArrayList<>();
	/*
	 * Key - entityName
	 * Value - relationType
	 */

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

	public GenerationType getGenerationType() {
		return generationType;
	}

	public void setGenerationType(GenerationType generationType) {
		this.generationType = generationType;
	}

	public boolean relationWithEntityNameExsists(String entityName) {
		for (Relation relation : getRelations()) {
			if (relation.getEntityName().equals(entityName)) return true;
		}
		return false;
	}

	public List<String> getEnumsForImport() {
		List<String> enumsForImport = new ArrayList<>();
		for (Attribute attribute : getAttributes()) {
			if (attribute.getType().equals(AttributeType.ENUM)) {
				enumsForImport.add(StringUtils.uppercaseFirst(attribute.getEnumName()));
			}
		}
		return enumsForImport;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", inherits=" + inherits + ", inheritanceType=" + inheritanceType + ", generationType=" + generationType + ", relations=" + relations + ", attributes="
				+ attributes + "]";
	}
}
