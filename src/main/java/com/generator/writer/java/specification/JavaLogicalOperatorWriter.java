package com.generator.writer.java.specification;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaLogicalOperatorWriter {

	public void create() throws Exception {
        try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "LogicalOperator.java", false)) {
            if (file.hasAlreadyExisted()) {
                return;
            }
            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
			file.writeln(0, "");
			file.writeln(0, "public enum LogicalOperator {");
			file.writeln(1, "AND, OR");
			file.writeln(0, "}");
        }
	}
 }
