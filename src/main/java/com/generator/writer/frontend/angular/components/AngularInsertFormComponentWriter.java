package com.generator.writer.frontend.angular.components;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularInsertFormComponentWriter implements ComponentWriter {

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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-insert-form", StringUtils.camelToKebabCase(entity.getName()) + "-insert-form.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { GenericInsertFormComponent } from \"../../../../shared/components/generic-insert-form/generic-insert-form.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-insert-form',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [GenericInsertFormComponent],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-insert-form.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-insert-form.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "InsertFormComponent extends GenericInsertFormComponent {");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-insert-form", StringUtils.camelToKebabCase(entity.getName()) + "-insert-form.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-insert-form", StringUtils.camelToKebabCase(entity.getName()) + "-insert-form.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<app-generic-insert-form [structure]=\"structure\"></app-generic-insert-form>");
		}
	}
}
