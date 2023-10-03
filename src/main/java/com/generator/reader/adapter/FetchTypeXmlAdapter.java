package com.generator.reader.adapter;

import com.generator.model.enums.FetchType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class FetchTypeXmlAdapter extends XmlAdapter<String, FetchType> {

	@Override
	public FetchType unmarshal(String v) throws Exception {
		return FetchType.fromCode(v);
	}

	@Override
	public String marshal(FetchType v) throws Exception {
		return v.getCode();
	}

}