package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.frontend.ComponentWriter;

public class AngularCreateEntityPageWriter implements ComponentWriter {

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
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendPagesPath() + snakeCaseName, StringUtils.camelToKebabCase(entity.getName()) + ".component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { " + upperCaseName + "InsertFormComponent } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-insert-form/" + "/" + snakeCaseName + "/-insert-form.component");
			file.writeln(0, "import { " + upperCaseName + "Structure } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-insert-form/" );
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + snakeCaseName + "-create-page',");
			file.writeln(1, "standalnoe: true,");
			file.writeln(1, "imports: [" + upperCaseName + "InsertFormComponent],");
			file.writeln(1, "templateUrl: './"+ snakeCaseName + "-create-page.component.html,'");
			file.writeln(1, "styleUrl: './" + snakeCaseName + "-create-page.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "CreatePageComponent {");
			file.writeln(1, "structure: " + upperCaseName + " Structure = " + upperCaseName + "Structure.instance;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendPagesPath() + snakeCaseName, StringUtils.camelToKebabCase(entity.getName()) + ".component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendPagesPath() + snakeCaseName, StringUtils.camelToKebabCase(entity.getName()) + ".component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<div class=\"page-container\">");
			file.writeln(0, "<app-" + snakeCaseName + "-insert-form [structure]=\"structure\"></app-" + snakeCaseName + "-insert-form>");
			file.writeln(0, "</div>");
		}
	}
	
}
