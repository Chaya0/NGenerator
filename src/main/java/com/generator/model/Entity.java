package com.generator.model;

import java.util.List;
import java.util.Map;

import com.generator.model.enums.GenerationType;
import com.generator.model.enums.InheritanceType;
import com.generator.model.enums.RelationType;
import com.generator.reader.adapter.GenerationTypeXmlAdapter;
import com.generator.reader.adapter.InheritanceTypeXmlAdapter;

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
	@XmlAttribute(name = "inherits", required = false)
	private String inherits;
	@XmlAttribute(name = "inheritance-type", required = false)
	@XmlJavaTypeAdapter(InheritanceTypeXmlAdapter.class)
	private InheritanceType inheritanceType;
	@XmlAttribute(name = "generationType", required = false)
	@XmlJavaTypeAdapter(GenerationTypeXmlAdapter.class)
	private GenerationType generationType = GenerationType.IDENTITY;

	@XmlElementWrapper(name = "relations", required = false)
	@XmlElement(name = "relation")
	private List<Relation> relations;
	@XmlElementWrapper(name = "attributes", required = false)
	@XmlElement(name = "attribute")
	private List<Attribute> attributes;
	/*
	 * Key - entityName
	 * Value - relationType
	 */
	private Map<String, RelationType> owningSideRelationEntites;

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

	public Map<String, RelationType> getOwningSideRelationEntites() {
		return owningSideRelationEntites;
	}

	public void setOwningSideRelationEntites(Map<String, RelationType> owningSideRelationEntites) {
		this.owningSideRelationEntites = owningSideRelationEntites;
	}
	
	public boolean relationWithEntityNameExsists(String entityName) {
		for(Relation relation : getRelations()) {
			if(relation.getEntityName().equals(entityName)) return true;
		}
		return false;
	}
}
