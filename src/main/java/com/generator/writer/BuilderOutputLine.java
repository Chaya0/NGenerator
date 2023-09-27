package com.generator.writer;

class BuilderOutputLine {
	private int indent;
	private String text;

	public BuilderOutputLine(int indent, String text) {
		setIndent(indent);
		setText(text);
	}

	public int getindent() {
		return indent;
	}

	public String getText() {
		return text;
	}

	private void setIndent(int indent) {
		if (indent < 0) indent = 0;
		this.indent = indent;
	}

	private void setText(String text) {
		if (text == null) text = "";
		this.text = text;
	}
}
