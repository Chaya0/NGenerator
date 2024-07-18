package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.frontend.ComponentWriter;

public class AngularEditEntityPageWriter implements ComponentWriter {

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
			file.writeln(0, "import { Component, inject, OnInit } from '@angular/core';");
			file.writeln(0, "import {ActivatedRoute} from \"@angular/router\";");
			file.writeln(0, "import { " + upperCaseName + "UpdateFormComponent } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-update-form/" + "/" + snakeCaseName
					+ "/-insert-form.component");
			file.writeln(0, "import { " + upperCaseName + "Structure } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-insert-form/");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + snakeCaseName + "-edit-page',");
			file.writeln(1, "standalnoe: true,");
			file.writeln(1, "imports: [" + upperCaseName + "UpdateFormComponent],");
			file.writeln(1, "templateUrl: './" + snakeCaseName + "-edit-page.component.html,'");
			file.writeln(1, "styleUrl: './" + snakeCaseName + "-edit-page.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "EditPageComponent implements OnInit {");
			file.writeln(1, "structure: " + upperCaseName + " Structure = " + upperCaseName + "Structure.instance;");
			file.writeln(1, "id: any;");
			file.writeln(1, "route = inject(ActivatedRoute);");
			file.writeln(0, "");
			file.writeln(1, "ngOnInit() {");
			file.writeln(2, "this.route.paramMap.subscribe(params => {");
			file.writeln(3, "this.id = params.get('id');");
			file.writeln(2, "});");
			file.writeln(1, "}");
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
			file.writeln(0, "<app-" + snakeCaseName + "-update-form [structure]=\"structure\" [id]=\"id\" ></app-" + snakeCaseName + "-update-form>");
		}
	}

}
