package com.generator.writer.java.specification;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSearchOperatorWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "GenericSpecification.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
			file.writeln(0, "");
			file.writeln(0, "import lombok.Getter;");
			file.writeln(0, "");
			file.writeln(0, "@Getter");
			file.writeln(0, "public enum SearchOperator {");
			file.writeln(1, "GT(\"GREATER_THAN\"),");
			file.writeln(1, "GTE(\"GREATER_THAN_OR_EQUAL\"),");
			file.writeln(1, "LT(\"LESS_THAN\"),");
			file.writeln(1, "LTE(\"LESS_THAN_OR_EQUAL\"),");
			file.writeln(1, "EQUAL(\"EQUAL\"),");
			file.writeln(1, "NOT_EQUAL(\"NOT_EQUAL\"),");
			file.writeln(1, "STARTS_WITH(\"STARTS_WITH\"),");
			file.writeln(1, "ENDS_WITH(\"ENDS_WITH\"),");
			file.writeln(1, "LIKE(\"LIKE\"),");
			file.writeln(1, "EMPTY_FILTER(\"EMPTY_FILTER\"),");
			file.writeln(1, "IN(\"IN\"),");
			file.writeln(1, "BETWEEN(\"BETWEEN\");");
			file.writeln(1, "");
			file.writeln(1, "SearchOperator(String operation) {");
			file.writeln(2, "this.operation = operation;");
			file.writeln(1, "}");
			file.writeln(1, "");
			file.writeln(1, "private final String operation;");
			file.writeln(1, "");
			file.writeln(0, "}");

		}
	}
}
