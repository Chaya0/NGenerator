package com.generator.writer.java.specification;

import java.io.File;
import java.util.List;

import com.generator.util.FileUtils;
import com.generator.writer.TemplateWriter;
import com.generator.writer.java.JavaTemplateWriterUtil;
import com.generator.writer.utils.WriterUtils;

public class JavaSpecificationWriter implements TemplateWriter {
	@Override
	public void create() throws Exception {
		for (File file : FileUtils.getSubFiles(JavaSpecificationWriter.class)) {
			JavaTemplateWriterUtil.processFile(file, WriterUtils.getSpecificationPackagePath());
		}
	}

	@Override
	public void create(List<String> templeteFilesPath) throws Exception {
	}

	@Override
	public void create(String templetaFilePath) throws Exception {
	}
}
