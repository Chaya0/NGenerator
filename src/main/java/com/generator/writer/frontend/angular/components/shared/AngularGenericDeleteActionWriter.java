package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.frontend.SimpleComponentWriter;

public class AngularGenericDeleteActionWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-delete-action.component.ts")) {
			file.writeln(0, "import {Component, EventEmitter, inject, Input, Output} from '@angular/core';");
			file.writeln(0, "import {MatSnackBar} from '@angular/material/snack-bar';");
			file.writeln(0, "import {RootComponent} from '../../root/root.component';");
			file.writeln(0, "import {ApiService} from '../../../core/services/api.service';");
			file.writeln(0, "import {MaterialModule} from '../../material/material.module';");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-generic-delete-action',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "imports: [");
			file.writeln(2, "MaterialModule");
			file.writeln(1, "],");
			file.writeln(1, "templateUrl: './generic-delete-action.component.html',");
			file.writeln(1, "styleUrl: './generic-delete-action.component.css'");
			file.writeln(0, "})");
			file.writeln(0, "export class GenericDeleteActionComponent extends RootComponent {");
			file.writeln(1, "@Input() object: any;");
			file.writeln(1, "@Input() entity: string = '';");
			file.writeln(1, "@Input() callback?: Function;");
			file.writeln(1, "@Output() dataChange = new EventEmitter<object>();");
			file.writeln(1, "override service: ApiService = inject(ApiService);");
			file.writeln(1, "private snackBar: MatSnackBar = inject(MatSnackBar);");
			file.writeln(0, "");
			file.writeln(1, "onDelete(): void {");
			file.writeln(2, "this.delete(this.object['id'], this.entity, () => {");
			file.writeln(3, "this.dataChange.emit();");
			file.writeln(3, "this.snackBar.open('Item deleted successfully', 'Close', {");
			file.writeln(4, "duration: 2000,");
			file.writeln(3, "});");
			file.writeln(2, "});");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-delete-action.component.css")) {
			file.writeln(0, "");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-delete-action.component.html")) {
			file.writeln(0, "<button mat-menu-item (click)=\"onDelete()\">");
			file.writeln(1, "<mat-icon color=\"warn\">delete</mat-icon>");
			file.writeln(1, "<div style=\"color: #ba1a1a\">Delete</div>");
			file.writeln(0, "</button>");
		}
	}
}
