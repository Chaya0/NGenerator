package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

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
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-create-page", StringUtils.camelToKebabCase(entity.getName()) + "-create-page.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { " + upperCaseName + "InsertFormComponent } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-insert-form/" +  kebabCase + "-insert-form.component';");
			file.writeln(0, "import { " + upperCaseName + "Structure } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-structure';");
			file.writeln(0, "import { BreadcrumbComponent } from \"../../../shared/components/breadcrumb/breadcrumb.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-create-page',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [" + upperCaseName + "InsertFormComponent],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-create-page.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-create-page.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "CreatePageComponent {");
			file.writeln(1, "structure: " + upperCaseName + "Structure = " + upperCaseName + "Structure.instance;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-create-page", StringUtils.camelToKebabCase(entity.getName()) + "-create-page.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-create-page", StringUtils.camelToKebabCase(entity.getName()) + "-create-page.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<div class=\"grid\">");
			file.writeln(1, "<div class=\"col-12\">");
			file.writeln(2, "<div class=\"card px-2 py-0 mb-4 flex flex-row justify-content-between align-items-center\">");
			file.writeln(3, "<app-breadcrumb></app-breadcrumb>");
			file.writeln(2, "</div>");
			file.writeln(2, "<div class=\"card px-6 py-6\"");
			file.writeln(3, "<app-" + kebabCase + "-insert-form [structure]=\"structure\"></app-" + kebabCase + "-insert-form>");
			file.writeln(2, "</div>");
			file.writeln(1, "</div>");
			file.writeln(0, "</div>");
		}
	}
	
}
