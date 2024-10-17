package com.generator.writer.java.config.security;

import java.io.File;
import java.util.List;

import com.generator.util.FileUtils;
import com.generator.writer.TemplateWriter;
import com.generator.writer.java.JavaTemplateWriterUtil;
import com.generator.writer.utils.WriterUtils;

public class JavaSecurityWriter implements TemplateWriter{

	@Override
	public void create() throws Exception {
		for (File file : FileUtils.getSubFiles(JavaSecurityWriter.class)) {
			JavaTemplateWriterUtil.processFile(file, WriterUtils.getSecurityPackagePath());
		}
	}

	@Override
	public void create(List<String> templeteFilesPath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(String templetaFilePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
