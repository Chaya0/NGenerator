package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularSearchEntityPageWriter implements ComponentWriter {

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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-page", StringUtils.camelToKebabCase(entity.getName()) + "-search-page.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component, inject, OnInit } from '@angular/core';");
			file.writeln(0, "import {RouterLink, RouterOutlet} from \"@angular/router\";");
			file.writeln(0, "import {NgIf} from \"@angular/common\";");
			file.writeln(0, "import {MaterialModule} from \"../../../shared/material/material.module\";");
			file.writeln(0, "import {Page} from \"../../../core/entity-utils/page\";");
			file.writeln(0, "import {SearchService} from \"../../../core/services/search.service\";");
			file.writeln(0, "import { AppUtils } from '../../../shared/utils/app-utils';");
			file.writeln(0, "import { " + upperCaseName + " } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "';");
			file.writeln(0, "import { " + upperCaseName + "Structure } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-structure';");
			file.writeln(0, "import { " + upperCaseName + "TableViewComponent } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-table-view/" + kebabCase + "-table-view.component';");
			file.writeln(0, "import { " + upperCaseName + "SearchFormComponent } from '../../../features/entities/" + kebabCase + "/" + kebabCase + "-search-form/" + kebabCase + "-search-form.component';");
			
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-search-page',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [");
			file.writeln(2, upperCaseName + "TableViewComponent,");
			file.writeln(2, upperCaseName + "SearchFormComponent,");
			file.writeln(2, "MaterialModule,");
			file.writeln(2, "RouterLink,");
			file.writeln(2, "RouterOutlet,");
			file.writeln(2, "NgIf");
			file.writeln(1, "],");
			file.writeln(1, "templateUrl: './" + kebabCase + "-search-page.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-search-page.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "SearchPageComponent {");
			file.writeln(1, "structure: " + upperCaseName + "Structure = " + upperCaseName + "Structure.instance;");
			file.writeln(1, "searchService: SearchService = inject(SearchService);");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-page", StringUtils.camelToKebabCase(entity.getName()) + "-search-page.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, ".title {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "align-items: center;");
			file.writeln(1, "padding: 16px 16px 24px;");
			file.writeln(1, "font-weight: 500;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".search-form {");
			file.writeln(0, "margin-bottom: 16px;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-page", StringUtils.camelToKebabCase(entity.getName()) + "-search-page.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<div class=\"title fw-bold\">");
			file.writeln(1, "{{ structure.title | translate }}");
			file.writeln(1, "<button mat-icon-button [matMenuTriggerFor]=\"menu\" aria-label=\"Example icon-button with a menu\">");
			file.writeln(2, "<mat-icon>arrow_drop_down</mat-icon>");
			file.writeln(1, "</button>");
			file.writeln(0, "</div>");
			file.writeln(0, "<mat-card class=\"card\" style=\"margin-bottom: 30px\">");
			file.writeln(1, "<mat-menu #menu=\"matMenu\">");
			file.writeln(2, "<a routerLink=\"create\" mat-menu-item>");
			file.writeln(3, "<mat-icon color=\"primary\">add</mat-icon>");
			file.writeln(3, "<span class=\"color-primary\">New</span>");
			file.writeln(2, "</a>");
			file.writeln(1, "</mat-menu>");
			file.writeln(1, "<mat-card-content>");
			file.writeln(2, "<div class=\"search-form\">");
			file.writeln(3, "<app-" + kebabCase + "-search-form class=\"search-form-container\"");
			file.writeln(4, "[structure]=\"structure\"></app-" + kebabCase + "-search-form>");
			file.writeln(2, "</div>");
			file.writeln(2, "<app-" + kebabCase + "-table-view [attributes]=\"structure.attributes\"");
			file.writeln(3, "[entity]=\"structure.entityName\"></app-" + kebabCase + "-table-view>");
			file.writeln(1, "</mat-card-content>");
			file.writeln(0, "</mat-card>");
		}
	}

}
