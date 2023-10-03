package com.generator.reader.adapter;

import com.generator.model.enums.CascadeType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CascadeTypeXmlAdapter extends XmlAdapter<String, CascadeType> {

	@Override
	public CascadeType unmarshal(String v) throws Exception {
		return CascadeType.fromCode(v);
	}

	@Override
	public String marshal(CascadeType v) throws Exception {
		return v.getCode();
	}

}