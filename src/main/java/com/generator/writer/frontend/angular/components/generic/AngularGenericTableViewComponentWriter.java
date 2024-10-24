package com.generator.writer.frontend.angular.components.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularGenericTableViewComponentWriter implements ComponentWriter {

	@Override
	public void create(AppModel model) throws Exception {
		boolean overwrite = false;
		for (Entity entity : model.getEntities()) {
			writeScript(entity, overwrite);
			writeStyles(entity, overwrite);
			writeHTML(entity, overwrite);
		}
	}

	@Override
	public void writeScript(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { GenericTableViewComponent } from \"../../../../shared/components/generic-table-view/generic-table-view.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-table-view',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [GenericTableViewComponent],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-table-view.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-table-view.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "TableViewComponent {");
			file.writeln(1, "@Input() attributes!: Attribute[];");
			file.writeln(1, "@Input() entity!: string;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<app-generic-table-view [attributes]=\"attributes\" [entity]=\"entity\"></app-generic-table-view>\r\n"
					+ "");
		}
	}
}