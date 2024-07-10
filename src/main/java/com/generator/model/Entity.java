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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {
	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "tableName", required = false)
	private String tableName;
	@XmlAttribute(name = "inherits")
	private String inherits;
	@XmlAttribute(name = "inheritanceType")
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
