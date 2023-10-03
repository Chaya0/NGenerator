package com.generator.reader.adapter;

import com.generator.model.enums.GenerationType;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class GenerationTypeXmlAdapter extends XmlAdapter<String, GenerationType> {

	@Override
	public GenerationType unmarshal(String v) throws Exception {
		return GenerationType.fromCode(v);
	}

	@Override
	public String marshal(GenerationType v) throws Exception {
		return v.getCode();
	}

}