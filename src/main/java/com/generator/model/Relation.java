package com.generator.model;

import com.generator.model.enums.RelationType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Relation {
	@XmlAttribute
	private RelationType relationType;
	@XmlAttribute
	private String joinColumn;
	@XmlAttribute
	private String entity;

	public Relation() {
	}

	public Relation(RelationType relationType, String joinColumn, String entity) {
		super();
		this.relationType = relationType;
		this.joinColumn = joinColumn;
		this.entity = entity;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

}
