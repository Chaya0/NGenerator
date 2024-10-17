package com.generator.reader.adapter;

import com.generator.model.enums.CodeEnum;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class EnumXmlAdapter<T extends Enum<T> & CodeEnum<T>> extends XmlAdapter<String, T> {
    private final Class<T> enumType;

    public EnumXmlAdapter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T unmarshal(String v) throws Exception {
        for (T constant : enumType.getEnumConstants()) {
            if (constant.getCode().equals(v)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + v);
    }

    @Override
    public String marshal(T v) throws Exception {
        return v.getCode();
    }
}
