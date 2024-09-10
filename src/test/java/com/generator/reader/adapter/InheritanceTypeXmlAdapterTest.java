package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.InheritanceType;

public class InheritanceTypeXmlAdapterTest {

	private InheritanceTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new InheritanceTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		String code = "singleTable";
		InheritanceType result = adapter.unmarshal(code);
		assertEquals(InheritanceType.SINGLE_TABLE, result);
	}

	@Test
	public void testMarshal() throws Exception {
		InheritanceType inheritanceType = InheritanceType.SINGLE_TABLE;
		String result = adapter.marshal(inheritanceType);
		assertEquals("singleTable", result);
	}

	@Test
	public void testUnmarshalInvalidValue() throws Exception {
		String code = "invalid";
		InheritanceType result = adapter.unmarshal(code);
		assertEquals(InheritanceType.NULL, result);
	}

	@Test
	public void testMarshalNull() throws Exception {
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}