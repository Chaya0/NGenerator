package com.generator.writer.frontend.angular.components;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularUpdateFormComponentWriter implements ComponentWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			writeScript(entity);
			writeStyles(entity);
			writeHTML(entity);
		}
	}

	@Override
	public void writeScript(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-update-form", StringUtils.camelToKebabCase(entity.getName()) + "-update-form.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { GenericUpdateFormComponent } from \"../../../../shared/components/generic-update-form/generic-update-form.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-update-form',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [GenericUpdateFormComponent],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-update-form.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-update-form.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "UpdateFormComponent extends GenericUpdateFormComponent {");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-update-form", StringUtils.camelToKebabCase(entity.getName()) + "-update-form.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-update-form", StringUtils.camelToKebabCase(entity.getName()) + "-update-form.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<app-generic-update-form [structure]=\"structure\" [id]=\"id\"></app-generic-update-form>");
		}
	}
}