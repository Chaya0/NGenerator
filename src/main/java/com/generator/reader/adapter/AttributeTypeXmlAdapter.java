package com.generator.reader.adapter;

import com.generator.model.enums.AttributeType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AttributeTypeXmlAdapter extends XmlAdapter<String, AttributeType> {

	@Override
	public AttributeType unmarshal(String v) throws Exception {
		return AttributeType.fromCode(v);
	}

	@Override
	public String marshal(AttributeType v) throws Exception {
		return v.getCode();
	}

}
