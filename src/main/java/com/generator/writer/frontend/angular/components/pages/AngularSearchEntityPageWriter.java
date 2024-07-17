package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.frontend.ComponentWriter;

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
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendPagesPath() + snakeCaseName, StringUtils.camelToKebabCase(entity.getName()) + ".component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component, inject, OnInit } from '@angular/core';");
			file.writeln(0, "import {RouterLink, RouterOutlet} from \"@angular/router\";");
			file.writeln(0, "import {NgIf} from \"@angular/common\";");
			file.writeln(0, "import {MaterialModule} from \"../../../shared/material/material.module\";");
			file.writeln(0, "import { " + upperCaseName + " } from \"../../../features/entities/" + snakeCaseName + "/" + entity.getName() + "\";");
			file.writeln(0, "import {SearchService} from \"../../../core/services/search.service\";");
			file.writeln(0, "import {Page} from \"../../../core/entity-utils/page\";");
			file.writeln(0, "import { " + upperCaseName + "Structure } from ../../../features/entities/" + snakeCaseName + "/" + entity.getName() + ".structure");
			file.writeln(0, "import { " + upperCaseName + "TableViewComponent } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-table-view/" + "/" + snakeCaseName
					+ "/-table.view.component");
			file.writeln(0, "import { " + upperCaseName + "SearchFormComponent } from ../../../features/entities/" + snakeCaseName + "/" + snakeCaseName + "/-search-form/" + snakeCaseName + "-search-form.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + snakeCaseName + "-create-page',");
			file.writeln(1, "standalnoe: true,");
			file.writeln(1, "imports: [" + upperCaseName + "InsertFormComponent],");
			file.writeln(1, "templateUrl: './" + snakeCaseName + "-create-page.component.html,'");
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
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendPagesPath() + snakeCaseName, StringUtils.camelToKebabCase(entity.getName()) + ".component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<mat-card class=\"card\" appearance=\"outlined\" style=\"margin-bottom: 30px\">");
			file.writeln(1, "<div class=\"title\">");
			file.writeln(2, "<button mat-icon-button [matMenuTriggerFor]=\"menu\" aria-label=\"Example icon-button with a menu\">");
			file.writeln(3, "<mat-icon>more_vert</mat-icon>");
			file.writeln(2, "</button>");
			file.writeln(2, "<mat-card-title>{{ structure.title | translate }}</mat-card-title>");
			file.writeln(1, "</div>");
			file.writeln(1, "<mat-menu #menu=\"matMenu\">");
			file.writeln(2, "<a routerLink=\"create\" mat-menu-item>");
			file.writeln(3, "<mat-icon color=\"primary\">add</mat-icon>");
			file.writeln(3, "<span class=\"color-primary\">New</span>");
			file.writeln(2, "</a>");
			file.writeln(1, "</mat-menu>");
			file.writeln(1, "<mat-card-content>");
			file.writeln(2, "<div class=\"search-form\">");
			file.writeln(3, "<app-" + snakeCaseName + "-search-form class=\"search-form-container\" [structure]=\"structure\"></app-" + snakeCaseName + "-search-form>");
			file.writeln(2, "</div>");
			file.writeln(2, "<mat-divider></mat-divider>");
			file.writeln(2, "<app-" + snakeCaseName + "-table-view [structure]=\"structure\" [page]=\"page\"></app-" + snakeCaseName + "-table-view>");
			file.writeln(1, "</mat-card-content>");
			file.writeln(0, "</mat-card>");
		}
	}

}
