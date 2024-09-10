package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.FetchType;

public class FetchTypeXmlAdapterTest {

	private FetchTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new FetchTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		String code = "eager";
		FetchType result = adapter.unmarshal(code);
		assertEquals(FetchType.EAGER, result);
	}

	@Test
	public void testMarshal() throws Exception {
		FetchType fetchType = FetchType.EAGER;
		String result = adapter.marshal(fetchType);
		assertEquals("eager", result);
	}

	@Test
	public void testUnmarshalInvalidValue() throws Exception {
		String code = "invalidCode";
		FetchType result = adapter.unmarshal(code);
		assertEquals(FetchType.NULL, result);
	}

	@Test
	public void testMarshalNull() throws Exception {
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}