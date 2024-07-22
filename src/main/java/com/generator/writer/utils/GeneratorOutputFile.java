package com.generator.writer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class GeneratorOutputFile implements AutoCloseable {
	private static final String indent = "\t";
	private final File file;
	private boolean alreadyExisted = true;
	private BufferedWriter writer = null;
	private String previousContentEnd = "";
	private String previousContentStart = "";

	public GeneratorOutputFile(String filename, String charset, boolean overwrite) throws IOException {
		file = new File(filename);
		alreadyExisted = true;
		if (!file.exists()) {
			alreadyExisted = false;
			file.createNewFile();
		} else {
			if (!overwrite) {
				return;
			}
		}
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), charset));
		conditionalWrite(previousContentStart);
	}

	public GeneratorOutputFile(String filename, String charset) throws IOException {
		this(filename, "UTF-8", true);
	}

	public GeneratorOutputFile(String filename) throws IOException {
		this(filename, "UTF-8");
	}

	public boolean hasAlreadyExisted() {
		return alreadyExisted;
	}

	boolean writePermitted() {
		return !alreadyExisted;
	}

	private void conditionalWrite(String s) throws IOException {
		if (writer == null) return;
		if (s == null) return;
		if (s.length() == 0) return;

		__write(0, s, "");
	}

	private void __write(int indentLevel, String s, String suffix) throws IOException {
		String blank = "";
		for (int i = 0; i < indentLevel; i++)
			blank += indent;

		writer.write(blank + s + suffix);
	}

	private void _write(int indentLevel, String line, String suffix) throws IOException {
		if (writer == null) return;
		if (line == null) return;
		__write(indentLevel, line, suffix);
	}

	public void writeln(int indentLevel, String line) throws IOException {
		_write(indentLevel, line, "\r\n");
	}

	void write(int indentLevel, String lines) throws IOException {
		_write(indentLevel, lines, "");
	}

	@Override
	public void close() throws IOException {
		if (writer == null) return;
		conditionalWrite(previousContentEnd);
		writer.close();
		writer = null;
	}

	@Override
	public String toString() {
		return file.getAbsolutePath();
	}
}
