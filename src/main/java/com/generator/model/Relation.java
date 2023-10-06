package com.generator.model;

import com.generator.model.enums.FetchType;
import com.generator.model.enums.RelationType;
import com.generator.reader.adapter.FetchTypeXmlAdapter;
import com.generator.reader.adapter.RelationTypeXmlAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Relation {
	@XmlAttribute(name = "joinColumn")
	private String joinColumn;
	@XmlAttribute(name = "entityName", required = true)
	private String entityName;
	@XmlAttribute(name = "relationType", required = true)
	@XmlJavaTypeAdapter(RelationTypeXmlAdapter.class)
	private RelationType relationType;
	@XmlAttribute(name = "fetchType")
	@XmlJavaTypeAdapter(FetchTypeXmlAdapter.class)
	private FetchType fetchType = FetchType.NULL;
	@XmlAttribute(name = "owningSide", required = true)
	private boolean owningSide;

	public Relation() {
	}

	public Relation(RelationType relationType, String joinColumn, String entityName, FetchType fetchType, boolean owningSide) {
		super();
		this.relationType = relationType;
		this.joinColumn = joinColumn;
		this.entityName = entityName;
		this.fetchType = fetchType;
		this.owningSide = owningSide;
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

	public boolean isOwningSide() {
		return owningSide;
	}

	public void setOwningSide(boolean owningSide) {
		this.owningSide = owningSide;
	}

	@Override
	public String toString() {
		return "Relation [joinColumn=" + joinColumn + ", entityName=" + entityName + ", relationType=" + relationType + ", fetchType=" + fetchType + ", owningSide=" + owningSide + "]";
	}
}
