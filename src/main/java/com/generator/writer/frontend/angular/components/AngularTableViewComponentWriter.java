package com.generator.writer.frontend.angular.components;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularTableViewComponentWriter implements ComponentWriter {

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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import { Component } from '@angular/core';");
			file.writeln(0, "import { GenericTableViewComponent } from \"../../../../shared/components/generic-table-view/generic-table-view.component\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-" + kebabCase + "-table-view',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [GenericTableViewComponent],");
			file.writeln(1, "templateUrl: './"+ kebabCase + "-table-view.component.html',");
			file.writeln(1, "styleUrl: './" + kebabCase + "-table-view.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "TableViewComponent extends GenericTableViewComponent {");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.css", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML(Entity entity) throws Exception {
		String kebabCase = StringUtils.camelToKebabCase(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + kebabCase + "/" + kebabCase + "-table-view", StringUtils.camelToKebabCase(entity.getName()) + "-table-view.component.html", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "<app-generic-table-view [attributes]=\"attributes\" [entity]=\"entity\" [page]=\"page\"></app-generic-table-view>");
		}
	}
}