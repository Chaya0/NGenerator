package com.generator.writer.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BuilderOutputLineTest {

	@Test
	public void testConstructorAndGetters() {
		BuilderOutputLine line = new BuilderOutputLine(4, "Hello");
		assertEquals(4, line.getIndent());
		assertEquals("Hello", line.getText());
	}

	@Test
	public void testSetIndent() {
		BuilderOutputLine line = new BuilderOutputLine(5, "Text");
		assertEquals(5, line.getIndent());
		BuilderOutputLine line2 = new BuilderOutputLine(-1, "Text");
		assertEquals(0, line2.getIndent());
	}

	@Test
	public void testSetText() {
		BuilderOutputLine line = new BuilderOutputLine(0, "New Text");
		assertEquals("New Text", line.getText());
		BuilderOutputLine line2 = new BuilderOutputLine(0, null);
		assertEquals("", line2.getText());
	}
}