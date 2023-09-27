package com.generator.writer;

import com.generator.model.AppModel;
import com.generator.model.Entity;

public interface Writer {
	   void create(Entity entity) throws Exception;
	   void create(AppModel model) throws Exception;
}
