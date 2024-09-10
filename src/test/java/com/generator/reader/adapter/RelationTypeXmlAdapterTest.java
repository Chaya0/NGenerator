package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.RelationType;

public class RelationTypeXmlAdapterTest {

	private RelationTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new RelationTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		String code = "oneToMany";
		RelationType result = adapter.unmarshal(code);
		assertEquals(RelationType.ONE_TO_MANY, result);
	}

	@Test
	public void testMarshal() throws Exception {
		RelationType relationType = RelationType.ONE_TO_MANY;
		String result = adapter.marshal(relationType);
		assertEquals("oneToMany", result);
	}

	@Test
	public void testUnmarshalInvalidValue() throws Exception {
		String code = "invalidCode";
		RelationType result = adapter.unmarshal(code);
		assertEquals(RelationType.NULL, result);
	}

	@Test
	public void testMarshalNull() throws Exception {
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}