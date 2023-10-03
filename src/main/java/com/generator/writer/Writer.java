package com.generator.writer;

import com.generator.model.AppModel;
import com.generator.model.Entity;

public interface Writer {
	void create(AppModel model) throws Exception;
	   void create(Entity entity) throws Exception;
}
