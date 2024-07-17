package com.generator.model;

import com.generator.model.enums.FetchType;
import com.generator.model.enums.RelationType;
import com.generator.reader.adapter.FetchTypeXmlAdapter;
import com.generator.reader.adapter.RelationTypeXmlAdapter;

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
public class Relation {
	@XmlAttribute(name = "entityName", required = true)
	private String entityName;
	@XmlAttribute(name = "tableName", required = true)
	private String tableName;
	@XmlAttribute(name = "relationName", required = true)
	private String relationName;
	@XmlAttribute(name = "owningSide", required = true)
	private boolean owningSide;
	@XmlAttribute(name = "relationType", required = true)
	@XmlJavaTypeAdapter(RelationTypeXmlAdapter.class)
	private RelationType relationType;
	@XmlAttribute(name = "fetchType")
	@XmlJavaTypeAdapter(FetchTypeXmlAdapter.class)
	private FetchType fetchType = FetchType.NULL;
}
