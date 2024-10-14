package com.generator.writer;

import java.util.List;

public interface TemplateWriter {
	void create() throws Exception;
	
	void create(List<String> templeteFilesPath) throws Exception;

	void create(String templetaFilePath) throws Exception;
}
