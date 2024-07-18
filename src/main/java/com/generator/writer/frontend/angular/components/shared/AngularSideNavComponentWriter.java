package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.frontend.SimpleComponentWriter;

public class AngularSideNavComponentWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/side-nav.component.ts")) {
			file.writeln(0, "import { Component, Input, signal } from '@angular/core';");
			file.writeln(0, "import { MaterialModule } from '../../material/material.module';");
			file.writeln(0, "import { RouterModule } from '@angular/router';");
			file.writeln(0, "import { CommonModule } from '@angular/common';");
			file.writeln(0, "import { MenuItemComponent } from \"../menu-item/menu-item.component\";");
			file.writeln(0, "import { MENU_ITEMS, MenuItem } from './menu-items.model';");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-side-nav',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "templateUrl: './side-nav.component.html',");
			file.writeln(1, "styleUrls: ['./side-nav.component.css'],");
			file.writeln(1, "imports: [");
			file.writeln(2, "MaterialModule,");
			file.writeln(2, "RouterModule,");
			file.writeln(2, "CommonModule,");
			file.writeln(2, "MenuItemComponent");
			file.writeln(1, "]");
			file.writeln(0, "})");
			file.writeln(0, "export class SideNavComponent {");
			file.writeln(1, "sidenavCollapsed = signal(false);");
			file.writeln(0, "");
			file.writeln(1, "@Input() set collapsed(val: boolean) {");
			file.writeln(2, "this.sidenavCollapsed.set(val);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "menuItems = signal<MenuItem[]>(MENU_ITEMS);");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/side-nav.component.css")) {
			file.writeln(0, ":host * {");
			file.writeln(1, "transition: all 250ms ease-in-out;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".sidenav-headder {");
			file.writeln(1, "padding-top: 10px;");
			file.writeln(1, "text-align: center;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".headder-text {");
			file.writeln(1, "height: 3rem;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".headder-text > h2 {");
			file.writeln(1, "margin: 0;");
			file.writeln(1, "font-size: 1rem;");
			file.writeln(1, "line-height: 1.5rem;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".hide-header-text {");
			file.writeln(1, "opacity: 0;");
			file.writeln(1, "height: 0 !important;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".menu-item {");
			file.writeln(1, "border-left: 5px solid;");
			file.writeln(1, "border-left-color: rgba(0, 0, 0, 0);");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".selected-menu-item {");
			file.writeln(1, "border-left-color: #005cbb;");
			file.writeln(1, "background: rgba(0, 0, 0, 0.05);");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/side-nav.component.html")) {
			file.writeln(0, "<mat-nav-list>");
			file.writeln(1, "@for (item of menuItems(); track item.label) {");
			file.writeln(2, "<app-menu-item [item]=\"item\" [collapsed]=\"sidenavCollapsed()\"></app-menu-item>");
			file.writeln(1, "}");
			file.writeln(0, "</mat-nav-list>");
		}
	}

}