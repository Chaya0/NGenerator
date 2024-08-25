package com.generator.writer.java.components.generic;

import com.generator.annotations.GenericComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.EnumModel;
import com.generator.model.enums.ComponentType;
import com.generator.util.StringUtils;
import com.generator.writer.EnumWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@GenericComponent(ComponentType.MODEL)
@WriterVersion("1.0")
public class JavaEnumWriter implements EnumWriter {

	@Override
	public void create(AppModel model) throws Exception {
		if (model.getEnums() != null) {
			for (EnumModel enumModel : model.getEnums()) {
				create(enumModel);
			}
		}
	}

	@Override
	public void create(EnumModel enumModel) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(enumModel.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getModelEnumsPackagePath(), upperCaseName + ".java", true)) {
			file.writeln(0, "package " + WriterUtils.getImportModelPackageName() + ".enums;");
			file.writeln(0, "");
			file.writeln(0, "public enum " + upperCaseName + " {");
			file.writeln(0, "");
			for (String enumType : enumModel.getValues()) {
				file.writeln(1, enumType.toUpperCase() + ",");
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}
	}
}
