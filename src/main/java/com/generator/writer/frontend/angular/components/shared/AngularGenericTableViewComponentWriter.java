package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.frontend.SimpleComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularGenericTableViewComponentWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-table-view.component.ts")) {
			file.writeln(0, "import { AfterViewInit, Component, inject, Input, OnChanges, OnInit, ViewChild } from '@angular/core';");
			file.writeln(0, "import { PageEvent } from \"@angular/material/paginator\";");
			file.writeln(0, "import { MatSort, Sort } from \"@angular/material/sort\";");
			file.writeln(0, "import { NgForOf, NgIf } from \"@angular/common\";");
			file.writeln(0, "import { MaterialModule } from \"../../material/material.module\";");
			file.writeln(0, "import { RootComponent } from \"../../root/root.component\";");
			file.writeln(0, "import { Attribute } from \"../../../core/entity-utils/attribute\";");
			file.writeln(0, "import { Page } from \"../../../core/entity-utils/page\";");
			file.writeln(0, "import { ApiService } from \"../../../core/services/api.service\";");
			file.writeln(0, "import { Order } from \"../../../core/entity-utils/order\";");
			file.writeln(0, "import { AppUtils } from \"../../utils/app-utils\";");
			file.writeln(0, "import { GenericDeleteActionComponent } from \"../generic-delete-action/generic-delete-action.component\";");
			file.writeln(0, "import { MatMenuTrigger } from \"@angular/material/menu\";");
			file.writeln(0, "import { RouterLink, RouterLinkActive } from \"@angular/router\";");
			file.writeln(0, "import { Structure } from \"../../../features/entities/department-unit/structure\";");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-generic-table-view',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [MaterialModule, NgForOf, NgIf, GenericDeleteActionComponent, MatMenuTrigger, RouterLink, RouterLinkActive],");
			file.writeln(1, "templateUrl: './generic-table-view.component.html',");
			file.writeln(1, "styleUrls: ['./generic-table-view.component.css']");
			file.writeln(0, "})");
			file.writeln(0, "export class GenericTableViewComponent extends RootComponent implements OnInit, AfterViewInit, OnChanges {");
			file.writeln(1, "@Input() structure?: Structure;");
			file.writeln(1, "attributes: Attribute[] = [];");
			file.writeln(1, "entity: string = \"\";");
			file.writeln(1, "@Input() page?: Page;");
			file.writeln(1, "@ViewChild(MatSort) sort!: MatSort;");
			file.writeln(1, "dataSource: any[] = this.page ? this.page.content : [];");
			file.writeln(1, "displayedColumns: string[] = [];");
			file.writeln(1, "foreignKeys: Map<string, string> = new Map<string, string>();");
			file.writeln(0, "");
			file.writeln(1, "override service: ApiService = inject(ApiService);");
			file.writeln(0, "");
			file.writeln(1, "ngOnInit() {");
			file.writeln(2, "this.attributes = this.structure?.attributes || [];");
			file.writeln(2, "this.entity = this.structure?.entityName || \"\";");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "ngAfterViewInit(): void {");
			file.writeln(2, "if (this.sort) {");
			file.writeln(3, "let sub = this.sort.sortChange.subscribe((sortState: Sort) => this.onSortChange(sortState));");
			file.writeln(3, "this.subs.push(sub);");
			file.writeln(2, "}");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "onSortChange(sortState: Sort): void {");
			file.writeln(2, "let searchDto = this.service.searchDTO;");
			file.writeln(2, "if (!searchDto) return;");
			file.writeln(2, "searchDto.sort = [new Order(sortState.active, sortState.direction)];");
			file.writeln(2, "this.service.searchDTO = searchDto;");
			file.writeln(0, "");
			file.writeln(2, "this.search(searchDto, this.entity, (data: Page) => {");
			file.writeln(3, "this.page = data;");
			file.writeln(3, "this.dataSource = this.page ? this.page.content : [];");
			file.writeln(3, "this.dataSource = this.transformContent(this.dataSource);");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "transformContent(dataSource: any[]) {");
			file.writeln(2, "let data = dataSource;");
			file.writeln(2, "for (let object of data) {");
			file.writeln(3, "this.foreignKeys.forEach((type, attr_name) => {");
			file.writeln(4, "if (object[attr_name])");
			file.writeln(5, "object[attr_name] = object[attr_name][AppUtils.getFkSearchAttribute(type)];");
			file.writeln(3, "})");
			file.writeln(2, "}");
			file.writeln(2, "return data;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "ngOnChanges(): void {");
			file.writeln(2, "this.displayedColumns = this.attributes.map(e => e.attr_name);");
			file.writeln(2, "this.displayedColumns.push('actions');");
			file.writeln(2, "this.attributes.filter(e => AppUtils.isForeignKey(e.type)).forEach(e => this.foreignKeys.set(e.attr_name, e.type));");
			file.writeln(2, "this.dataSource = this.page ? this.page.content : [];");
			file.writeln(2, "this.dataSource = this.transformContent(this.dataSource);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "onPageChange(event: PageEvent): void {");
			file.writeln(2, "let searchDto = this.service.searchDTO;");
			file.writeln(2, "if (searchDto) {");
			file.writeln(3, "searchDto.pageNumber = event.pageIndex;");
			file.writeln(3, "this.search(searchDto, this.entity, (data: Page) => {");
			file.writeln(4, "this.page = data;");
			file.writeln(4, "this.dataSource = this.page ? this.page.content : [];");
			file.writeln(4, "this.dataSource = this.transformContent(this.dataSource);");
			file.writeln(3, "});");
			file.writeln(2, "}");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "refreshData() {");
			file.writeln(2, "if (!this.service.searchDTO) return;");
			file.writeln(2, "this.search(this.service.searchDTO, this.entity, (data: Page) => {");
			file.writeln(3, "this.page = data;");
			file.writeln(3, "this.dataSource = this.page ? this.page.content : [];");
			file.writeln(3, "this.dataSource = this.transformContent(this.dataSource);");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-table-view.component.css")) {
			file.writeln(0, ".mat-column-actions {");
			file.writeln(1, "width: 32px;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-table-view.component.html")) {
			file.writeln(0, "<table mat-table [dataSource]=\"dataSource\" matSort>");
			file.writeln(1, "<ng-container matColumnDef=\"{{ attribute.attr_name }}\" *ngFor=\"let attribute of attributes\">");
			file.writeln(2, "<th mat-header-cell mat-sort-header *matHeaderCellDef>{{ attribute.title }}</th>");
			file.writeln(2, "<td mat-cell *matCellDef=\"let item\">{{ item[attribute.attr_name] }}</td>");
			file.writeln(1, "</ng-container>");
			file.writeln(0, "");
			file.writeln(1, "<ng-container matColumnDef=\"actions\">");
			file.writeln(2, "<th mat-header-cell *matHeaderCellDef></th>");
			file.writeln(2, "<td mat-cell *matCellDef=\"let item\">");
			file.writeln(3, "<button mat-icon-button [matMenuTriggerFor]=\"menu\" aria-label=\"more\">");
			file.writeln(4, "<mat-icon>more_horiz</mat-icon>");
			file.writeln(3, "</button>");
			file.writeln(3, "<mat-menu #menu>");
			file.writeln(4, "<a mat-menu-item [routerLink]=\"['update', item['id']]\">");
			file.writeln(5, "<mat-icon color=\"primary\">edit</mat-icon>");
			file.writeln(5, "<div class=\"color-primary\">Edit</div>");
			file.writeln(4, "</a>");
			file.writeln(4, "<app-generic-delete-action [entity]=\"entity\" [object]=\"item\"");
			file.writeln(5, "(dataChange)=\"refreshData()\"></app-generic-delete-action>");
			file.writeln(3, "</mat-menu>");
			file.writeln(2, "</td>");
			file.writeln(1, "</ng-container>");
			file.writeln(0, "");
			file.writeln(1, "<tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>");
			file.writeln(1, "<tr mat-row *matRowDef=\"let row; columns: displayedColumns;\"></tr>");
			file.writeln(0, "</table>");
			file.writeln(0, "");
			file.writeln(0, "<mat-paginator [length]=\"page?.totalElements\"");
			file.writeln(1, "[pageSize]=\"page?.size\"");
			file.writeln(1, "[pageIndex]=\"page?.number\"");
			file.writeln(1, "(page)=\"onPageChange($event)\"");
			file.writeln(1, "[showFirstLastButtons]=\"true\">");
			file.writeln(0, "</mat-paginator>");
		}
	}

}