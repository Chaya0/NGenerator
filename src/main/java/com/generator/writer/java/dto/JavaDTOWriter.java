package com.generator.writer.java.dto;

import java.io.File;
import java.util.List;

import com.generator.util.FileUtils;
import com.generator.writer.TemplateWriter;
import com.generator.writer.java.JavaTemplateWriterUtil;
import com.generator.writer.utils.WriterUtils;

public class JavaDTOWriter implements TemplateWriter {
	@Override
	public void create() throws Exception {
		for (File file : FileUtils.getSubFiles(JavaDTOWriter.class)) {
			JavaTemplateWriterUtil.processFile(file, WriterUtils.getDTOPackagePath());
		}
	}

	@Override
	public void create(List<String> templeteFilesPath) throws Exception {
	}

	@Override
	public void create(String templetaFilePath) throws Exception {
	}
}
