package com.generator.writer.frontend.angular.components.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularGenericSearchFormComponentWriter implements ComponentWriter {
	
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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-form", StringUtils.camelToKebabCase(entity.getName()) + "-search-form.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { GenericSearchFormComponent } from \"../../../../shared/components/generic-search-form/generic-search-form.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-search-form',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [ GenericSearchFormComponent ],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-search-form.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-search-form.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "SearchFormComponent {");
			file.writeln(1, "@Input() structure!: " + upperCaseName + "Structure;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-form", StringUtils.camelToKebabCase(entity.getName()) + "-search-form.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-form", StringUtils.camelToKebabCase(entity.getName()) + "-search-form.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<app-generic-search-form [structure]=\"structure\"></app-generic-search-form>");
		}
	}
}