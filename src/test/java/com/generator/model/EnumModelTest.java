package com.generator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnumModelTest {

	private EnumModel enumModel;

	@BeforeEach
	public void setUp() {
		List<String> values = Arrays.asList("VALUE1", "VALUE2", "VALUE3");
		enumModel = new EnumModel("TestEnum", values);
	}

	@Test
	public void testGetName() {
		assertEquals("TestEnum", enumModel.getName());
	}

	@Test
	public void testGetValues() {
		List<String> expectedValues = Arrays.asList("VALUE1", "VALUE2", "VALUE3");
		assertEquals(expectedValues, enumModel.getValues());
	}

	@Test
	public void testToString() {
		String expectedString = "EnumModel(name=TestEnum, values=[VALUE1, VALUE2, VALUE3])";
		assertEquals(expectedString, enumModel.toString());
	}
}