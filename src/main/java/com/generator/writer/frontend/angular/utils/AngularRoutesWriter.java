package com.generator.writer.frontend.angular.utils;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.Utils;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularRoutesWriter {

	public void createRoutes(AppModel appModel) throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendEnvironmentPath(), "app.routes.ts", false)) {
			file.writeln(0, "import {Routes} from '@angular/router';");
			// TODO add logic for handling imports when authorization is enabled
			file.writeln(0, "import { LoginComponent } from './pages/login/login.component';");
			file.writeln(0, "import { AuthGuard } from './core/guards/auth.guard';");
			file.writeln(0, "import { LoginGuard } from './core/guards/login.guard';");
			file.writeln(0, "import { DashboardComponent } from './shared/components/dashboard/dashboard.component';");
			file.writeln(0, "import {PageNotFoundComponent} from './shared/components/page-not-found/page-not-found.component';");
			for (Entity entity : appModel.getEntities()) {
				String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
				String kebabCaseName = StringUtils.camelToKebabCase(entity.getName());
				file.writeln(0,
						"import { " + upperCaseName + "SearchPageComponent } from './pages/" + kebabCaseName + "/" + kebabCaseName + "-search-page/" + kebabCaseName + "-search-page.component';");
				file.writeln(0,
						"import { " + upperCaseName + "CreatePageComponent } from './pages/" + kebabCaseName + "/" + kebabCaseName + "-create-page/" + kebabCaseName + "-create-page.component';");
				file.writeln(0, "import { " + upperCaseName + "EditPageComponent } from './pages/" + kebabCaseName + "/" + kebabCaseName + "-edit-page/" + kebabCaseName + "-edit-page.component';");
			}
			file.breakLine();
			file.writeln(0, "export const routes: Routes = [");
			file.writeln(1, "{");
			file.writeln(2, "path: 'login',");
			file.writeln(2, "component: LoginComponent,");
			file.writeln(2, "canActivate: [LoginGuard]");
			file.writeln(1, "},");

			for (Entity entity : appModel.getEntities()) {
				writeSearchPageRoute(file, entity);
				if(entity.getName().equals("permission")) continue;
				writeCreatePageRoute(file, entity);
				writeEditPageRoute(file, entity);
			}
			file.writeln(1, "{ path: 'pageNotFound', component: PageNotFoundComponent, canActivate: [AuthGuard] },");
			file.writeln(1, "{ path: '', component: DashboardComponent, canActivate: [AuthGuard] },");
			file.writeln(1, "{ path: '**', redirectTo: 'pageNotFound' }");
			file.writeln(0, "];");			
		}
	}

	private void writeSearchPageRoute(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		file.writeln(1, "{");
		file.writeln(2, "path: '" + entity.getName() + "',");
		file.writeln(2, "component: " + upperCaseName + "SearchPageComponent,");
		file.writeln(2, "canActivate: [AuthGuard],");
		file.writeln(2, "data: { breadcrumb: '"+entity.getName()+".title', parent: '', permissions: ['can_view_"+snakeCaseName+"'] },");
		file.writeln(1, "},");
	}

	private void writeCreatePageRoute(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		file.writeln(1, "{");
		file.writeln(2, "path: '" + entity.getName() + "/create',");
		file.writeln(2, "component: " + upperCaseName + "CreatePageComponent,");
		file.writeln(2, "canActivate: [AuthGuard],");
		file.writeln(2, "data: { breadcrumb: 'create', parent: '" + entity.getName() + "', permissions: ['can_create_"+snakeCaseName+"'] },");
		file.writeln(1, "},");
	}

	private void writeEditPageRoute(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		file.writeln(1, "{");
		file.writeln(2, "path: '" + entity.getName() + "/update/:id',");
		file.writeln(2, "component: " + upperCaseName + "EditPageComponent,");
		file.writeln(2, "canActivate: [AuthGuard],");
		file.writeln(2, "data: { breadcrumb: 'update', parent: '" + entity.getName() + "', permissions: ['can_update_"+snakeCaseName+"'] },");
		file.writeln(1, "},");
	}
}
