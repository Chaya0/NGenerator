package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.frontend.SimpleComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularGenericSearchFormComponentWriter implements SimpleComponentWriter {
	
	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();		
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-search-form.component.ts")) {
			file.writeln(0, "import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';");
			file.writeln(0, "import { NgFor, NgIf, NgTemplateOutlet } from \"@angular/common\";");
			file.writeln(0, "import { MaterialModule } from \"../../material/material.module\";");
			file.writeln(0, "import { RootComponent } from \"../../root/root.component\";");
			file.writeln(0, "import { Attribute } from \"../../../core/entity-utils/attribute\";");
			file.writeln(0, "import { FormBuilder, FormGroup } from \"@angular/forms\";");
			file.writeln(0, "import { ApiService } from \"../../../core/services/api.service\";");
			file.writeln(0, "import { SearchFilter } from \"../../../core/entity-utils/search-filter\";");
			file.writeln(0, "import { FilterGroup } from \"../../../core/entity-utils/filter-group\";");
			file.writeln(0, "import { LogicalOperator } from \"../../../core/entity-utils/logical-operator\";");
			file.writeln(0, "import { SearchDTO } from \"../../../core/entity-utils/search-dto\";");
			file.writeln(0, "import { Page } from \"../../../core/entity-utils/page\";");
			file.writeln(0, "import { AppUtils } from \"../../utils/app-utils\";");
			file.writeln(0, "import { SearchOperator } from \"../../../core/entity-utils/search-operator\";");
			file.writeln(0, "import { Structure } from \"../../../features/entities/department-unit/structure\";");
			file.writeln(0, "import { SearchService } from \"../../../core/services/search.service\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-generic-search-form',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [MaterialModule, NgIf, NgFor, NgTemplateOutlet],");
			file.writeln(1, "templateUrl: './generic-search-form.component.html',");
			file.writeln(1, "styleUrls: ['./generic-search-form.component.css']");
			file.writeln(0, "})");
			file.writeln(0, "export class GenericSearchFormComponent extends RootComponent implements OnInit {");
			file.writeln(1, "@Input() structure?: Structure;");
			file.writeln(1, "attributes: Attribute[] = [];");
			file.writeln(1, "entity: string = \"\";");
			file.writeln(1, "searchForm: FormGroup;");
			file.writeln(0, "");
			file.writeln(1, "override service: ApiService = inject(ApiService);");
			file.writeln(1, "private fb: FormBuilder = inject(FormBuilder);");
			file.writeln(1, "private searchService: SearchService = inject(SearchService);");
			file.writeln(0, "");
			file.writeln(1, "constructor() {");
			file.writeln(2, "super();");
			file.writeln(2, "this.searchForm = this.fb.group({});");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "ngOnInit() {");
			file.writeln(2, "this.attributes = this.structure?.attributes || [];");
			file.writeln(2, "this.entity = this.structure?.entityName || \"\";");
			file.writeln(2, "this.initForm();");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "initForm() {");
			file.writeln(2, "this.attributes.forEach(attribute => {");
			file.writeln(3, "if (attribute.type !== 'primaryKey')");
			file.writeln(4, "this.searchForm.addControl(attribute.attr_name, this.fb.control('', []));");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "emitSearch(): void {");
			file.writeln(2, "const filters: SearchFilter[] = this.collectSearchFilters();");
			file.writeln(2, "const filterGroup: FilterGroup = {");
			file.writeln(3, "logicalOperator: LogicalOperator.AND,");
			file.writeln(3, "filters: filters.filter(f => f.value !== null && f.value !== undefined && f.value !== '')");
			file.writeln(2, "};");
			file.writeln(2, "const searchDTO: SearchDTO = {");
			file.writeln(3, "pageNumber: 0,");
			file.writeln(3, "pageSize: this.service.pageSize,");
			file.writeln(3, "sort: [],");
			file.writeln(3, "filterGroup: filterGroup");
			file.writeln(2, "};");
			file.writeln(0, "");
			file.writeln(2, "// We are saving searchDTO inside the service, so we can reuse it in the table component for page changes etc.");
			file.writeln(2, "this.service.searchDTO = searchDTO;");
			file.writeln(0, "");
			file.writeln(2, "this.search(searchDTO, this.entity, (data: Page<any>) => {");
			file.writeln(3, "this.searchService.emitEvent(data);");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "private collectSearchFilters() {");
			file.writeln(2, "return this.attributes.map(attribute => {");
			file.writeln(3, "if (AppUtils.isForeignKey(attribute.type)) {");
			file.writeln(4, "let fkSearchField = AppUtils.getFkSearchAttribute(attribute.type);");
			file.writeln(4, "let fkSearchValue = this.searchForm.get(attribute.attr_name)?.value;");
			file.writeln(4, "if (fkSearchValue && fkSearchValue.length > 0)");
			file.writeln(5, "return ({");
			file.writeln(6, "key: attribute.attr_name,");
			file.writeln(6, "searchOperator: SearchOperator.LIKE,");
			file.writeln(6, "value: {");
			file.writeln(7, "[fkSearchField]: fkSearchValue");
			file.writeln(6, "}");
			file.writeln(5, "});");
			file.writeln(3, "}");
			file.writeln(3, "return ({");
			file.writeln(4, "key: attribute.attr_name,");
			file.writeln(4, "searchOperator: SearchOperator.LIKE,");
			file.writeln(4, "value: this.searchForm.get(attribute.attr_name)?.value");
			file.writeln(3, "});");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-search-form.component.css")) {
			file.writeln(0, ".form {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "flex-direction: column;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".fields {");
			file.writeln(1, "display: grid;");
			file.writeln(1, "grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));");
			file.writeln(1, "gap: 16px;");
			file.writeln(1, "max-height: 500px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".action-panel {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "flex-direction: row;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-search-form.component.html")) {
			file.writeln(0, "<form [formGroup]=\"searchForm\" (ngSubmit)=\"emitSearch()\" class=\"form\">");
			file.writeln(1, "<div class=\"fields\">");
			file.writeln(2, "<ng-container *ngFor=\"let attribute of attributes\">");
			file.writeln(3, "<ng-container *ngIf=\"attribute.type !== 'primaryKey'\">");
			file.writeln(4, "<mat-form-field appearance=\"outline\">");
			file.writeln(5, "<mat-label>{{ attribute.title }}</mat-label>");
			file.writeln(5, "<input *ngIf=\"attribute.type !== 'number'; else numberInput\"");
			file.writeln(6, "matInput formControlName=\"{{ attribute.attr_name }}\">");
			file.writeln(5, "<ng-template #numberInput>");
			file.writeln(6, "<input type=\"number\" matInput formControlName=\"{{ attribute.attr_name }}\">");
			file.writeln(5, "</ng-template>");
			file.writeln(4, "</mat-form-field>");
			file.writeln(3, "</ng-container>");
			file.writeln(2, "</ng-container>");
			file.writeln(1, "</div>");
			file.writeln(1, "<div class=\"action-panel\">");
			file.writeln(2, "<div>");
			file.writeln(3, "<button mat-flat-button type=\"submit\" color=\"primary\" class=\"mr-10px\">Search</button>");
			file.writeln(3, "<button mat-stroked-button type=\"reset\" color=\"primary\">Clear</button>");
			file.writeln(2, "</div>");
			file.writeln(2, "<ng-content></ng-content>");
			file.writeln(1, "</div>");
			file.writeln(0, "</form>");
		}
	}


}