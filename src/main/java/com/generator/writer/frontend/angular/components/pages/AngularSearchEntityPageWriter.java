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
			file.writeln(0, "import { Component, inject } from '@angular/core';");
			file.writeln(0, "import {RouterLink, RouterOutlet} from \"@angular/router\";");
			file.writeln(0, "import { NgIf } from \"@angular/common\";");
			file.writeln(0, "import {MenuItem} from \"primeng/api\";");
			file.writeln(0, "import { PrimeModule } from \"../../../shared/prime/prime.module\";");
			file.writeln(0, "import { SearchService } from \"../../../core/services/search.service\";");
			file.writeln(0, "import { TranslationService } from \"../../../core/services/translation.service\";");
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
			file.writeln(2, "BreadcrumbComponent,");
			file.writeln(2, "PrimeModule,");
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
			file.writeln(1, "translationService = inject(TranslationService);");
			file.writeln(1, "items: MenuItem[] = [");
			file.writeln(2, "{");
			file.writeln(3, "label: this.translationService.translate('new'),");
			file.writeln(3, "icon: 'pi pi-plus',");
			file.writeln(3, "routerLink: 'create',");
			file.writeln(3, "command: () => {}");
			file.writeln(2, "}");
			file.writeln(1, "];");
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
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendPagesPath() + kebabCase + "/" + kebabCase + "-search-page", StringUtils.camelToKebabCase(entity.getName()) + "-search-page.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<div class=\"grid\">");
			file.writeln(1, "<div class=\"col-12\">");
			file.writeln(2, "<div class=\"card px-2 py-0 mb-4 flex flex-row justify-content-between align-items-center\">");
			file.writeln(3, "<app-breadcrumb></app-breadcrumb>");
			file.writeln(3, "<div>");
			file.writeln(4, "<p-menu #menu [model]=\"items\" [popup]=\"true\"/>");
			file.writeln(4, "<p-button (onClick)=\"menu.toggle($event)\" [rounded]=\"true\" [text]=\"true\" icon=\"pi pi-ellipsis-v\"/>");
			file.writeln(3, "</div>");
			file.writeln(2, "</div>");
			file.writeln(2, "<div class=\"card px-6 py-6\"");
			file.writeln(2, "<div class=\"mb-4\">");
			file.writeln(0, "<app-" + kebabCase + "-search-form [structure]=\"structure\"></app-" + kebabCase + "-search-form>");
			file.writeln(2, "</div>");
			file.writeln(0, "<app-" + kebabCase + "-table-view [structure]=\"structure\"></app-" + kebabCase + "-table-view>");
			file.writeln(2, "</div>");
			file.writeln(1, "</div>");
			file.writeln(0, "</div>");
		}
	}

}
