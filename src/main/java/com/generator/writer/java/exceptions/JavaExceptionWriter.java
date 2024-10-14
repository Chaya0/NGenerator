package com.generator.writer.java.exceptions;

import java.io.File;
import java.util.List;

import com.generator.util.FileUtils;
import com.generator.writer.TemplateWriter;
import com.generator.writer.java.JavaTemplateWriterUtil;
import com.generator.writer.utils.WriterUtils;

public class JavaExceptionWriter implements TemplateWriter {
	@Override
	public void create() throws Exception {
		for (File file : FileUtils.getSubFiles(JavaExceptionWriter.class)) {
			JavaTemplateWriterUtil.processFile(file, WriterUtils.getExceptionsPackagePath());
		}
	}

	@Override
	public void create(List<String> templeteFilesPath) throws Exception {
	}

	@Override
	public void create(String templetaFilePath) throws Exception {
	}
}
