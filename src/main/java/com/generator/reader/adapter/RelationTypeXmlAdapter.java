package com.generator.reader.adapter;

import com.generator.model.enums.RelationType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class RelationTypeXmlAdapter extends XmlAdapter<String, RelationType> {

	@Override
	public RelationType unmarshal(String v) throws Exception {
		return RelationType.fromCode(v);
	}

	@Override
	public String marshal(RelationType v) throws Exception {
		return v.getCode();
	}

}