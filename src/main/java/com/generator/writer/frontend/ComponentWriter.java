package com.generator.writer.frontend;

import com.generator.model.Entity;
import com.generator.writer.Writer;

public interface ComponentWriter extends Writer {
	void writeScript(Entity entity) throws Exception;

	void writeStyles(Entity entity) throws Exception;

	void writeHTML(Entity entity) throws Exception;
}
