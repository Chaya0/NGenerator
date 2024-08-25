package com.generator.writer.java.specification;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSearchFilterWriter {
	public void create() throws Exception {
        try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getSpecificationPackagePath(), "GenericSpecification.java", false)) {
            if (file.hasAlreadyExisted()) {
                return;
            }
            file.writeln(0, "package " + WriterUtils.getSpecificationPackageImportPath() + ";");
            file.writeln(0, "");
            file.writeln(0, "import java.util.List;");
            file.writeln(0, "");
            file.writeln(0, "import lombok.Data;");
            file.writeln(0, "");
            file.writeln(0, "@Data");
            file.writeln(0, "public class SearchFilter {");
            file.writeln(1, "private String key;");
            file.writeln(1, "private SearchOperator searchOperator;");
            file.writeln(1, "private Object value;");
            file.writeln(1, "private LogicalOperator logicalOperator;");
            file.writeln(1, "private List<SearchFilter> filters;");
            file.writeln(0, "}");

        }
 }
}
