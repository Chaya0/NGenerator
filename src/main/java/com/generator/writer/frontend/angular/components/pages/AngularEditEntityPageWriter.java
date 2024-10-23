package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularEditEntityPageWriter implements ComponentWriter {

	@Override
	public void create(AppModel model) throws Exception {
		boolean overwrite = true;
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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-edit-page", StringUtils.camelToKebabCase(entity.getName()) + "-edit-page.component.ts", overwrite)) {
			file.writeln(0, "import { Component, inject, OnInit } from '@angular/core';");
			file.writeln(0, "import {ActivatedRoute} from \"@angular/router\";");
			file.writeln(0, "import { " + upperCaseName + "UpdateFormComponent } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-update-form/" +  kebabCase + "-update-form.component';");
			file.writeln(0, "import { " + upperCaseName + "Structure } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-structure';");
			file.writeln(0, "import { BreadcrumbComponent } from \"../../../shared/components/breadcrumb/breadcrumb.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-edit-page',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [" + upperCaseName + "UpdateFormComponent, BreadcrumbComponent],");
			file.writeln(1, "templateUrl: './" + kebabCase + "-edit-page.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-edit-page.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "EditPageComponent implements OnInit {");
			file.writeln(1, "structure: " + upperCaseName + "Structure = " + upperCaseName + "Structure.instance;");
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
	public void writeStyles(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-edit-page", StringUtils.camelToKebabCase(entity.getName()) + "-edit-page.component.css", overwrite)) {
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity, boolean overwrite) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-edit-page", StringUtils.camelToKebabCase(entity.getName()) + "-edit-page.component.html", overwrite)) {
			file.writeln(0, "<div class=\"grid\">");
			file.writeln(1, "<div class=\"col-12\">");
			file.writeln(2, "<div class=\"card px-2 py-0 mb-4 flex flex-row justify-content-between align-items-center\">");
			file.writeln(3, "<app-breadcrumb></app-breadcrumb>");
			file.writeln(2, "</div>");
			file.writeln(2, "<div class=\"card px-6 py-6\">");
			file.writeln(0, "<app-" + kebabCase + "-update-form [structure]=\"structure\" [id]=\"id\" ></app-" + kebabCase + "-update-form>");
			file.writeln(2, "</div>");
			file.writeln(1, "</div>");
			file.writeln(0, "</div>");
		}
	}

}
