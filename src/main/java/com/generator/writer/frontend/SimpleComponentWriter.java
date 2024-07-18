package com.generator.writer.frontend;

public interface SimpleComponentWriter extends FrontendWriter {
	void writeScript() throws Exception;

	void writeStyles() throws Exception;

	void writeHTML() throws Exception;
}
