package com.generator.model;

import com.generator.model.enums.FetchType;
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
	private String entityName;
	@XmlAttribute
	private FetchType fetchType;

	public Relation() {
	}

	public Relation(RelationType relationType, String joinColumn, String entityName, FetchType fetchType) {
		super();
		this.relationType = relationType;
		this.joinColumn = joinColumn;
		this.entityName = entityName;
		this.fetchType = fetchType;
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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public FetchType getFetchType() {
		return fetchType;
	}

	public void setFetchType(FetchType fetchType) {
		this.fetchType = fetchType;
	}

}
