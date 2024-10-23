package com.generator.writer.frontend.angular.model;

import com.generator.model.AppModel;
import com.generator.model.EnumModel;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularEnumWriter {
	public void create(AppModel appModel) throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath(), "enums.model.ts", true)) {
			for (EnumModel enumModel : appModel.getEnums()) {
				file.writeln(0, "export enum " + StringUtils.uppercaseFirst(enumModel.getName()) + " {");
				for (String value : enumModel.getValues()) {
					file.writeln(1, value.toUpperCase() + " = '" + value + "',");
				}
				file.writeln(0, "}");
				file.breakLine();
			}
		}
	}

}
