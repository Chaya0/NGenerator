package com.generator.writer;

import com.generator.model.Entity;

public interface DefaultWriter extends Writer{
	   void create(Entity entity) throws Exception;
}
