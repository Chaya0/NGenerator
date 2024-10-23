package com.generator.writer.frontend;

import com.generator.model.Entity;
import com.generator.writer.Writer;

public interface ComponentWriter extends Writer {
	void writeScript(Entity entity, boolean overwrite) throws Exception;

	void writeStyles(Entity entity, boolean overwrite) throws Exception;

	void writeHTML(Entity entity, boolean overwrite) throws Exception;
}
