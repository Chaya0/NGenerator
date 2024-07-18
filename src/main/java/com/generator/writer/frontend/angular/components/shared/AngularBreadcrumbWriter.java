package com.generator.writer.frontend.angular.components.shared;
import java.io.IOException;

import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.frontend.SimpleComponentWriter;

public class AngularBreadcrumbWriter implements SimpleComponentWriter{

	@Override
	public void create() throws Exception {
		writeScript();
        writeHTML();
        writeStyles();
	}
	
    @Override
    public void writeScript() throws IOException {
        try (GeneratorOutputFile file = new GeneratorOutputFile("", "UTF-8", true)) {
            file.writeln(0, "import {Component, inject, OnInit} from '@angular/core';");
            file.writeln(0, "import {NavigationEnd, Route, Router, RouterLink} from '@angular/router';");
            file.writeln(0, "import {NgClass, NgForOf, NgIf} from '@angular/common';");
            file.writeln(0, "import {MatIcon} from '@angular/material/icon';");
            file.writeln(0, "import {TranslatePipe} from '../../../core/pipes/translate.pipe';");
            file.writeln(0, "import {routes} from '../../../app.routes';");
            file.writeln(0, "import {filter, map} from 'rxjs/operators';");
            file.writeln(0, "");
            file.writeln(0, "interface Breadcrumb {");
            file.writeln(1, "label: string;");
            file.writeln(1, "url: string;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, "@Component({");
            file.writeln(1, "selector: 'app-breadcrumb',");
            file.writeln(1, "standalone: true,");
            file.writeln(1, "imports: [");
            file.writeln(2, "RouterLink,");
            file.writeln(2, "NgForOf,");
            file.writeln(2, "NgIf,");
            file.writeln(2, "NgClass,");
            file.writeln(2, "MatIcon,");
            file.writeln(2, "TranslatePipe");
            file.writeln(1, "],");
            file.writeln(1, "templateUrl: './breadcrumb.component.html',");
            file.writeln(1, "styleUrls: ['./breadcrumb.component.css']");
            file.writeln(0, "})");
            file.writeln(0, "export class BreadcrumbComponent implements OnInit {");
            file.writeln(1, "breadcrumbs: Breadcrumb[] = [];");
            file.writeln(1, "");
            file.writeln(1, "routes: Route[] = routes;");
            file.writeln(1, "private router: Router = inject(Router);");
            file.writeln(1, "");
            file.writeln(1, "isHome() {");
            file.writeln(2, "return this.router.url === '/';");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "ngOnInit(): void {");
            file.writeln(2, "// initial load, build breadcrumbs manually");
            file.writeln(2, "this.breadcrumbs = this.buildBreadcrumbs();");
            file.writeln(2, "");
            file.writeln(2, "// subscribe to events to build breadcrumbs automatically");
            file.writeln(2, "this.router.events.pipe(");
            file.writeln(3, "filter(event => event instanceof NavigationEnd),");
            file.writeln(3, "map(() => this.buildBreadcrumbs())");
            file.writeln(2, ").subscribe(breadcrumbs => {");
            file.writeln(3, "this.breadcrumbs = breadcrumbs;");
            file.writeln(2, "});");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "buildBreadcrumbs(): Breadcrumb[] {");
            file.writeln(2, "const currentRoute: Route | undefined = this.findRoute(this.router.url.slice(1));");
            file.writeln(2, "if (!currentRoute || !currentRoute.data) return [];");
            file.writeln(2, "let breadcrumbs = this.fillBreadcrumbs(currentRoute, []);");
            file.writeln(2, "breadcrumbs.reverse().push({label: currentRoute.data['breadcrumb'], url: currentRoute.path || ''});");
            file.writeln(2, "return breadcrumbs;");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "fillBreadcrumbs(current: Route, breadcrumbs: Breadcrumb[]): Breadcrumb[] {");
            file.writeln(2, "if (!current.data || !current.data['parent'] || current.data['parent'] === '') {");
            file.writeln(3, "return breadcrumbs;");
            file.writeln(2, "}");
            file.writeln(2, "const parent: Route | undefined = this.findRoute(current.data['parent']) || {};");
            file.writeln(2, "if (parent && parent.data)");
            file.writeln(3, "breadcrumbs.push({label: parent.data['breadcrumb'], url: parent.path || ''});");
            file.writeln(2, "return this.fillBreadcrumbs(parent, breadcrumbs);");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "findRoute(url: string): Route | undefined {");
            file.writeln(2, "// First, try to find a matching route with parameters");
            file.writeln(2, "let route = this.routes.find(route => this.matchRoute(route.path || '', url));");
            file.writeln(2, "");
            file.writeln(2, "// If no specific match is found, try to match the wildcard route");
            file.writeln(2, "if (!route) {");
            file.writeln(3, "route = this.routes.find(route => route.path === '**');");
            file.writeln(2, "}");
            file.writeln(2, "");
            file.writeln(2, "return route;");
            file.writeln(1, "}");
            file.writeln(1, "");
            file.writeln(1, "matchRoute(pattern: string, url: string): boolean {");
            file.writeln(2, "const regexPattern = pattern.replace(/:[^\\s/]+/g, '([^\\s/]+)').replace(/\\*\\*/g, '.*');");
            file.writeln(2, "const regex = new RegExp(`^${regexPattern}$`);");
            file.writeln(2, "return regex.test(url);");
            file.writeln(1, "}");
            file.writeln(0, "}");
        }
    }

    @Override
	public void writeHTML() throws IOException {
        try (GeneratorOutputFile file = new GeneratorOutputFile("", "UTF-8", true)) {
            file.writeln(0, "<nav *ngIf=\"!isHome()\" aria-label=\"breadcrumb\">");
            file.writeln(1, "<ol class=\"breadcrumb\">");
            file.writeln(2, "<li class=\"breadcrumb-item\">");
            file.writeln(3, "<a [routerLink]=\"'/'\">");
            file.writeln(4, "<mat-icon class=\"material-icons color-gray\">home</mat-icon>");
            file.writeln(3, "</a>");
            file.writeln(2, "</li>");
            file.writeln(2, "<li *ngFor=\"let breadcrumb of breadcrumbs; let last = last\" class=\"breadcrumb-item color-gray\"");
            file.writeln(3, "[ngClass]=\"{ 'active': last }\">");
            file.writeln(3, "<ng-container *ngIf=\"!last\">");
            file.writeln(4, "<mat-icon class=\"color-gray\">chevron_right</mat-icon>");
            file.writeln(4, "<a [routerLink]=\"breadcrumb.url\">{{ breadcrumb.label | translate }}</a>");
            file.writeln(3, "</ng-container>");
            file.writeln(3, "<ng-container *ngIf=\"last\">");
            file.writeln(4, "<mat-icon class=\"color-gray\">chevron_right</mat-icon>");
            file.writeln(4, "<span>{{ breadcrumb.label | translate }}</span>");
            file.writeln(3, "</ng-container>");
            file.writeln(2, "</li>");
            file.writeln(1, "</ol>");
            file.writeln(0, "</nav>");
        }
    }

    @Override
	public void writeStyles() throws IOException {
        try (GeneratorOutputFile file = new GeneratorOutputFile("", "UTF-8", true)) {
            file.writeln(0, ".breadcrumb {");
            file.writeln(1, "display: flex;");
            file.writeln(1, "align-items: center;");
            file.writeln(1, "list-style: none;");
            file.writeln(1, "padding: 0;");
            file.writeln(1, "margin: 0;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, ".breadcrumb-item {");
            file.writeln(1, "display: flex;");
            file.writeln(1, "align-items: center;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, ".breadcrumb-item a {");
            file.writeln(1, "color: inherit;");
            file.writeln(1, "text-decoration: none;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, ".breadcrumb-item .mat-icon {");
            file.writeln(1, "vertical-align: middle;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(1, ".breadcrumb-item.active span {");
            file.writeln(1, "font-weight: bold;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, ".color-gray {");
            file.writeln(1, "color: #4e4e4e;");
            file.writeln(0, "}");
            file.writeln(0, "");
            file.writeln(0, ".material-icons.color-gray {");
            file.writeln(1, "color: #4e4e4e;");
            file.writeln(0, "}");
            file.writeln(0, "");
        }
    }
}
