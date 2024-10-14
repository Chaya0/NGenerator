package com.generator.writer.java.config.security;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSecurityUtilWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath() + "/security", "JwtUtil.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}
}
