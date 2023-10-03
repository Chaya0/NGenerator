package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.EnumModel;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaEnumWriter {

	public void create(AppModel model) throws Exception {
		for(EnumModel enumModel : model.getEnums()) {
			create(enumModel);
		}

	}

	private void create(EnumModel enumModel) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(enumModel.getName());

		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getModelEnumsPackagePath(), upperCaseName + ".java", false)) {
			file.writeln(0, "package " + Utils.getImportModelPackageName() + ";");
			file.writeln(0, "");
			file.writeln(0, "public enum " + upperCaseName + " {");
			file.writeln(0, "");
			for(String enumType : enumModel.getValues()) {
				file.writeln(1, enumType.toUpperCase());
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}
	}
}
