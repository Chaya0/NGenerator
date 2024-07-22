package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.frontend.SimpleComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularGenericFormComponentWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-form.component.ts")) {
			file.writeln(0, "import {Component, inject, Input, OnInit} from '@angular/core';");
			file.writeln(0, "import {Observable, startWith} from 'rxjs';");
			file.writeln(0, "import {map} from 'rxjs/operators';");
			file.writeln(0, "import {FormBuilder, FormGroup} from '@angular/forms';");
			file.writeln(0, "import {MatSnackBar} from '@angular/material/snack-bar';");
			file.writeln(0, "import {RootComponent} from '../../root/root.component';");
			file.writeln(0, "import {Attribute} from '../../../core/entity-utils/attribute';");
			file.writeln(0, "import {AppUtils} from '../../utils/app-utils';");
			file.writeln(0, "import {FormUtils} from '../../utils/form-utils';");
			file.writeln(0, "import {Structure} from '../../../features/entities/department-unit/structure';");
			file.writeln(0, "import {Location} from '@angular/common';");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-generic-form',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [],");
			file.writeln(1, "template: '',");
			file.writeln(1, "styles: []");
			file.writeln(0, "})");
			file.writeln(0, "export abstract class GenericFormComponent extends RootComponent implements OnInit {");
			file.writeln(1, "@Input() structure?: Structure;");
			file.writeln(1, "entity: string = '';");
			file.writeln(1, "attributes: Attribute[] = [];");
			file.writeln(1, "data: any;");
			file.writeln(1, "form?: FormGroup;");
			file.writeln(1, "options: Map<String, any[]> = new Map<String, any[]>();");
			file.writeln(1, "filteredOptions: Map<String, Observable<String[]>> = new Map<String, Observable<String[]>>();");
			file.writeln(0, "");
			file.writeln(1, "protected fb: FormBuilder = inject(FormBuilder);");
			file.writeln(1, "protected snackBar: MatSnackBar = inject(MatSnackBar);");
			file.writeln(1, "protected location: Location = inject(Location);");
			file.writeln(0, "");
			file.writeln(1, "initOptions(): void {");
			file.writeln(2, "for (let attr of this.attributes) {");
			file.writeln(3, "if (!AppUtils.isForeignKey(attr.type)) continue;");
			file.writeln(3, "this.getAll(this.entity, (data: any[]) => {");
			file.writeln(4, "this.options.set(attr.attr_name, data);");
			file.writeln(3, "});");
			file.writeln(2, "}");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "ngOnInit() {");
			file.writeln(2, "this.attributes = this.structure?.attributes || [];");
			file.writeln(2, "this.entity = this.structure?.entityName || '';");
			file.writeln(2, "this.initForm({});");
			file.writeln(2, "this.initOptions();");
			file.writeln(2, "this.initFilteredOptions();");
			file.writeln(2, "this.setupValueChangeHandlers();");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "private initFilteredOptions() {");
			file.writeln(2, "for (let attr of this.attributes) {");
			file.writeln(3, "let control = this.form?.controls[attr.attr_name];");
			file.writeln(3, "this.filteredOptions.set(attr.attr_name, control?.valueChanges.pipe(");
			file.writeln(4, "startWith(''),");
			file.writeln(4, "map(value => this.filterObject(value || '', this.options.get(attr.attr_name) || [], attr.type))");
			file.writeln(3, ") || new Observable());");
			file.writeln(2, "}");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "private filterObject(value: string, options: any[], type: string): string[] {");
			file.writeln(2, "if (typeof value === 'object') {");
			file.writeln(3, "value = this.getDisplayValue(value, type);");
			file.writeln(2, "}");
			file.writeln(2, "return options.filter(option => this.getDisplayValue(option, type).toLowerCase().includes(value.toLowerCase()));");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "initForm(formData: any): void {");
			file.writeln(2, "this.attributes = this.attributes.filter(attr => attr.type !== 'primaryKey');");
			file.writeln(0, "");
			file.writeln(2, "const formControls: { [key: string]: any } = this.attributes.reduce((controls, attr) => {");
			file.writeln(3, "// @ts-ignore");
			file.writeln(3, "controls[attr.attr_name] = [formData[attr.attr_name] || null, FormUtils.getValidators(attr)];");
			file.writeln(3, "return controls;");
			file.writeln(2, "}, {});");
			file.writeln(2, "this.form = this.fb.group(formControls);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "getFilteredOptions(attr_name: string) {");
			file.writeln(2, "return this.filteredOptions.get(attr_name);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "getDisplayValue(object: any, type: string) {");
			file.writeln(2, "let key = AppUtils.getFkSearchAttribute(type);");
			file.writeln(2, "if (!object || !object[key] || object[key] === '') return null;");
			file.writeln(2, "return object[key];");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "displayFunction(type: string): (option: any) => string {");
			file.writeln(2, "return (option: any): string => this.getDisplayValue(option, type);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "private setupValueChangeHandlers(): void {");
			file.writeln(2, "this.attributes.forEach(attr => {");
			file.writeln(3, "const control = this.form?.controls[attr.attr_name];");
			file.writeln(3, "control?.valueChanges.subscribe(value => {");
			file.writeln(4, "if (value === '') {");
			file.writeln(5, "control.setValue(null, {emitEvent: false});");
			file.writeln(4, "}");
			file.writeln(3, "});");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "onCancel() {");
			file.writeln(2, "this.location.back(); // Navigate to a specific path");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-form.component.css")) {
			file.writeln(0, ".title {");
			file.writeln(1, "padding: 16px 16px 24px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".fields {");
			file.writeln(1, "display: grid;");
			file.writeln(1, "grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));");
			file.writeln(1, "gap: 16px;");
			file.writeln(1, "max-height: 500px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".card {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "margin: auto;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".field {");
			file.writeln(1, "width: 100%;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".form-footer {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "justify-content: space-between;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-form.component.html")) {
			file.writeln(0, "<mat-card class=\"card\" appearance=\"outlined\">");
			file.writeln(1, "<mat-card-title class=\"title\">{{ structure?.title || \"\" | translate }}</mat-card-title>");
			file.writeln(1, "<mat-card-content *ngIf=\"form\">");
			file.writeln(2, "<form [formGroup]=\"form\" (ngSubmit)=\"onSubmit()\">");
			file.writeln(3, "<div class=\"fields\">");
			file.writeln(4, "<div *ngFor=\"let attr of attributes\">");
			file.writeln(5, "<mat-form-field *ngIf=\"!AppUtils.isForeignKey(attr.type)\" appearance=\"outline\" class=\"field\">");
			file.writeln(6, "<mat-label>{{ attr.title }}</mat-label>");
			file.writeln(6, "<input matInput [formControlName]=\"attr.attr_name\" [type]=\"attr.type\"/>");
			file.writeln(6, "<mat-error *ngIf=\"form?.get(attr.attr_name)?.hasError('required')\">");
			file.writeln(7, "{{ attr.title }} is required");
			file.writeln(6, "</mat-error>");
			file.writeln(6, "<mat-error *ngIf=\"form?.get(attr.attr_name)?.hasError('maxlength')\">");
			file.writeln(7, "{{ attr.title }} cannot be longer than {{ attr.maxLength }} characters");
			file.writeln(6, "</mat-error>");
			file.writeln(6, "<mat-error *ngIf=\"form?.get(attr.attr_name)?.hasError('minlength')\">");
			file.writeln(7, "{{ attr.title }} must be at least {{ attr.minLength }} characters");
			file.writeln(6, "</mat-error>");
			file.writeln(5, "</mat-form-field>");
			file.writeln(5, "<mat-form-field *ngIf=\"AppUtils.isForeignKey(attr.type)\" appearance=\"outline\" class=\"field\">");
			file.writeln(6, "<mat-label>{{ attr.title }}</mat-label>");
			file.writeln(6, "<input matInput type=\"text\" [matAutocomplete]=\"auto\" [formControlName]=\"attr.attr_name\"/>");
			file.writeln(6, "<mat-autocomplete #auto=\"matAutocomplete\" [displayWith]=\"displayFunction(attr.type)\">");
			file.writeln(7, "<mat-option *ngFor=\"let option of getFilteredOptions(attr.attr_name) | async; trackBy: trackByFn\" [value]=\"option\">");
			file.writeln(8, "{{ getDisplayValue(option, attr.type) }}");
			file.writeln(7, "</mat-option>");
			file.writeln(6, "</mat-autocomplete>");
			file.writeln(5, "</mat-form-field>");
			file.writeln(4, "</div>");
			file.writeln(3, "</div>");
			file.writeln(3, "<div class=\"form-footer\">");
			file.writeln(4, "<div>");
			file.writeln(5, "<button mat-stroked-button (click)=\"onCancel()\">Cancel</button>");
			file.writeln(4, "</div>");
			file.writeln(4, "<div>");
			file.writeln(5, "<button mat-stroked-button type=\"reset\" class=\"mr-10px\">Clear</button>");
			file.writeln(5, "<button mat-flat-button color=\"primary\" type=\"submit\" [disabled]=\"form.invalid\">Save</button>");
			file.writeln(4, "</div>");
			file.writeln(3, "</div>");
			file.writeln(2, "</form>");
			file.writeln(1, "</mat-card-content>");
			file.writeln(0, "</mat-card>");
		}
	}
}