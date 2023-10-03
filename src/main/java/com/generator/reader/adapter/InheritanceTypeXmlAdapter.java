package com.generator.reader.adapter;

import com.generator.model.enums.InheritanceType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class InheritanceTypeXmlAdapter extends XmlAdapter<String, InheritanceType> {

	@Override
	public InheritanceType unmarshal(String v) throws Exception {
		return InheritanceType.fromCode(v);
	}

	@Override
	public String marshal(InheritanceType v) throws Exception {
		return v.getCode();
	}

}