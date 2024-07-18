package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.frontend.SimpleComponentWriter;

public class AngularHeaderComponentWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();
	}

	@Override
	public void writeScript() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/header.component.ts")) {
			file.writeln(0, "import { Component, computed, inject, signal } from '@angular/core';");
			file.writeln(0, "import { SideNavComponent } from \"../side-nav/side-nav.component\";");
			file.writeln(0, "import { MaterialModule } from '../../material/material.module';");
			file.writeln(0, "import { CommonModule, NgOptimizedImage } from '@angular/common';");
			file.writeln(0, "import { Router, RouterModule } from '@angular/router';");
			file.writeln(0, "import { LanguageService } from '../../../core/services/language.service';");
			file.writeln(0, "import { BreadcrumbComponent } from \"../breadcrumb/breadcrumb.component\";");
			file.writeln(0, "import { AuthService } from '../../../core/services/auth.service';");
			file.writeln(0, "");
			file.writeln(0, "@Component({");
			file.writeln(1, "selector: 'app-header',");
			file.writeln(1, "standalone: true,");
			file.writeln(1, "templateUrl: './header.component.html',");
			file.writeln(1, "styleUrls: ['./header.component.css'],");
			file.writeln(1, "imports: [");
			file.writeln(2, "SideNavComponent,");
			file.writeln(2, "MaterialModule,");
			file.writeln(2, "CommonModule,");
			file.writeln(2, "RouterModule,");
			file.writeln(2, "BreadcrumbComponent,");
			file.writeln(2, "NgOptimizedImage");
			file.writeln(1, "]");
			file.writeln(0, "})");
			file.writeln(0, "export class HeaderComponent {");
			file.writeln(1, "opened = true;");
			file.writeln(1, "languageService = inject(LanguageService);");
			file.writeln(1, "authService = inject(AuthService);");
			file.writeln(1, "router: Router = inject(Router);");
			file.writeln(0, "");
			file.writeln(1, "toggleSidenav() {");
			file.writeln(2, "this.opened = !this.opened;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "switchLanguage(languageCode: string) {");
			file.writeln(2, "this.languageService.switchLanguage(languageCode);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "collapsed = signal(false);");
			file.writeln(1, "sidenavWidth = computed(() => this.collapsed() ? '65px' : '250px');");
			file.writeln(0, "");
			file.writeln(1, "showBreadcrumb(): boolean {");
			file.writeln(2, "return this.router.url !== '/';");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeStyles() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/header.component.css")) {
			file.writeln(0, "mat-toolbar {");
			file.writeln(1, "position: relative;");
			file.writeln(1, "z-index: 5;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, "mat-sidenav-container {");
			file.writeln(1, "height: calc(100vh - 64px);");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, "mat-sidenav, mat-sidenav-content {");
			file.writeln(1, "transition: all 200ms ease-in-out;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".header-toolbar {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "justify-content: space-between;");
			file.writeln(1, "align-items: center;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".language-dropdown {");
			file.writeln(1, "margin-left: auto;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".flag-icon {");
			file.writeln(1, "margin-right: 8px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".content {");
			file.writeln(1, "padding: 10px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".container {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "flex-direction: column;");
			file.writeln(1, "margin: auto;");
			file.writeln(1, "width: 80%;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".breadcrumb-container {");
			file.writeln(1, "margin-bottom: 10px;");
			file.writeln(1, "font-size: 14px;");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, ".header-part {");
			file.writeln(1, "display: flex;");
			file.writeln(1, "flex-direction: row;");
			file.writeln(1, "justify-content: space-between;");
			file.writeln(1, "align-items: center;");
			file.writeln(0, "}");
		}
	}

	@Override
	public void writeHTML() throws IOException {
		try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/header.component.html")) {
			file.writeln(0, "<mat-toolbar class=\"header-toolbar\">");
			file.writeln(1, "<div class=\"header-part\">");
			file.writeln(2, "<button mat-icon-button (click)=\"collapsed.set(!collapsed())\">");
			file.writeln(3, "<mat-icon>menu</mat-icon>");
			file.writeln(2, "</button>");
			file.writeln(2, "<div class=\"app-logo ml-10px\">");
			file.writeln(3, "<img priority ngSrc=\"assets/merv/merv_stack.svg\" alt=\"mds\" height=\"90\" width=\"90\">");
			file.writeln(2, "</div>");
			file.writeln(1, "</div>");
			file.writeln(1, "<div class=\"header-part\">");
			file.writeln(2, "<div class=\"language-dropdown\">");
			file.writeln(3, "<button mat-button [matMenuTriggerFor]=\"menu\">");
			file.writeln(4, "<span class=\"fi\" [ngClass]=\"languageService.getSelectedLanguage()?.flag\"></span>");
			file.writeln(4, "{{ languageService.getSelectedLanguage()?.shortName }}");
			file.writeln(3, "</button>");
			file.writeln(3, "<mat-menu #menu=\"matMenu\">");
			file.writeln(4, "<button mat-menu-item *ngFor=\"let lang of languageService.getLanguages()\" (click)=\"switchLanguage(lang.code)\">");
			file.writeln(5, "<span class=\"fi\" [ngClass]=\"lang.flag\"></span>");
			file.writeln(5, "{{ lang.fullName }}");
			file.writeln(4, "</button>");
			file.writeln(3, "</mat-menu>");
			file.writeln(2, "</div>");
			file.writeln(2, "<button mat-icon-button (click)=\"authService.logout()\">");
			file.writeln(3, "<mat-icon color=\"warn\">exit_to_app</mat-icon>");
			file.writeln(2, "</button>");
			file.writeln(1, "</div>");
			file.writeln(0, "</mat-toolbar>");
			file.writeln(0, "<mat-sidenav-container>");
			file.writeln(1, "<mat-sidenav opened mode=\"side\" [style.width]=\"sidenavWidth()\">");
			file.writeln(2, "<app-side-nav [collapsed]=\"collapsed()\">");
			file.writeln(2, "</app-side-nav>");
			file.writeln(1, "</mat-sidenav>");
			file.writeln(1, "<mat-sidenav-content class=\"content\" [style.margin-left]=\"sidenavWidth()\">");
			file.writeln(2, "<div class=\"container\">");
			file.writeln(3, "<app-breadcrumb class=\"breadcrumb-container\"></app-breadcrumb>");
			file.writeln(3, "<router-outlet></router-outlet>");
			file.writeln(2, "</div>");
			file.writeln(1, "</mat-sidenav-content>");
			file.writeln(0, "</mat-sidenav-container>");
		}
	}

}