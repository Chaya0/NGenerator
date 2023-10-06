package com.generator.writer;

import com.generator.model.AppModel;

public interface Writer {
	void create(AppModel model) throws Exception;
}
