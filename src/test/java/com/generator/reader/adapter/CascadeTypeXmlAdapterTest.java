package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.CascadeType;

public class CascadeTypeXmlAdapterTest {

	private CascadeTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new CascadeTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		String code = "ALL";
		CascadeType result = adapter.unmarshal(code);
		assertEquals(CascadeType.ALL, result);
	}

	@Test
	public void testMarshal() throws Exception {
		CascadeType cascadeType = CascadeType.ALL;
		String result = adapter.marshal(cascadeType);
		assertEquals("ALL", result);
	}

	@Test
	public void testUnmarshalInvalidValue() {
		String invalidCode = "INVALID";
		assertThrows(Exception.class, () -> adapter.unmarshal(invalidCode));
	}

	@Test
	public void testMarshalNull() throws Exception {
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}