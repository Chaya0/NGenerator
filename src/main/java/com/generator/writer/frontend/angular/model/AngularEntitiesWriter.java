package com.generator.writer.frontend.angular.model;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularEntitiesWriter {
	public void create(AppModel appModel) throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath(), "entities.ts", true)) {
			for (Entity entity : appModel.getEntities()) {
				String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
				String kebabCaseName = StringUtils.camelToKebabCase(entity.getName());
				file.writeln(0, "import { " + upperCaseName + "Structure } from \"./" + kebabCaseName + "/" + kebabCaseName + "-structure\";");
				file.writeln(0, "import { " + upperCaseName + " } from \"./" + kebabCaseName + "/" + kebabCaseName + "\";");
			}
			file.writeln(0, "");
			file.writeln(0, "export const entities = [ ");
			for (Entity entity : appModel.getEntities()) {
				String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
				file.writeln(1, "{");
				file.writeln(2, "name: \"" + upperCaseName + "\",");
				file.writeln(2, "structure: " + upperCaseName + "Structure.instance,");
				file.writeln(2, "type: " + upperCaseName);
				file.writeln(1, "},");
			}
			file.writeln(0, "]");
		}
	}
}
