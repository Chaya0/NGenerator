package com.generator.writer.java.components.generic;

import com.generator.model.AppModel;
import com.generator.writer.Writer;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaEntityAttributeWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
//		if (WriterUtils.fileExists(WriterUtils.getServicePackagePath(false), "/" + "EntityAttribute.java")) {
//			return;
//		}
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false), "EntityAttribute.java", true)) {
			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "public interface EntityAttribute {");
			file.writeln(1, "String getName();");
			file.writeln(0, "}");
		}
	}
}
