package com.generator.writer.frontend.angular.utils;

import com.generator.writer.Utils;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularEnvironmentWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendEnvironmentPath(), "environment.development.ts", false)) {
			file.writeln(0, "export const environment = {");
			file.writeln(1, "production: false,");
			file.writeln(1, "apiUrl: 'http://localhost:8080/',");
			file.writeln(1, "defaultLanguage: 'en',");
			file.writeln(0, "}");
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendEnvironmentPath(), "environment.test.ts", false)) {
			file.writeln(0, "export const environment = {");
			file.writeln(1, "production: true,");
			file.writeln(1, "apiUrl: 'http://localhost:8080/',");
			file.writeln(1, "defaultLanguage: 'en',");
			file.writeln(0, "}");
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendEnvironmentPath(), "environment.prod.ts", false)) {
			file.writeln(0, "export const environment = {");
			file.writeln(1, "production: true,");
			file.writeln(1, "apiUrl: 'http://localhost:8080/',");
			file.writeln(1, "defaultLanguage: 'en',");
			file.writeln(0, "}");
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendEnvironmentPath(), "environment.ts", false)) {
			file.writeln(0, "export const environment = {");
			file.writeln(1, "production: false,");
			file.writeln(1, "apiUrl: 'http://localhost:8080/',");
			file.writeln(1, "defaultLanguage: 'en',");
			file.writeln(0, "}");
		}
	}
}
