package com.generator.writer.frontend.angular.initialization;

import java.io.IOException;

import com.generator.Application;
import com.generator.writer.Utils;
import com.generator.writer.frontend.angular.assets.AngularStyleClassWriter;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularInitializationWriter {
	
	public void create() throws IOException, Exception {
		AngularAppComponentWriter angularAppComponentWriter = new AngularAppComponentWriter();
		AngularStyleClassWriter angularStyleClassWriter = new AngularStyleClassWriter();
		angularAppComponentWriter.create();
		angularStyleClassWriter.create();
		writeIndexHtml();
	}

	private void writeIndexHtml() throws IOException, Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendSourceDirectoryPath(), "index.html", true)) {
			file.writeln(0, "<!doctype html>");
			file.writeln(0, "<html lang=\"en\">");
			file.writeln(0, "<head>");
			file.writeln(1, "<meta charset=\"utf-8\">");
			file.writeln(1, "<title>" + Application.getSpringProperties().getName().toUpperCase() + "</title>");
			file.writeln(1, "<base href=\"/\">");
			file.writeln(1, "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			file.writeln(1, "<link rel=\"icon\" type=\"image/x-icon\" href=\"assets/merv/stack.svg\">");
			file.writeln(1, "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/gh/lipis/flag-icons@7.0.0/css/flag-icons.min.css\" />");
			file.writeln(1, "<link href=\"https://unpkg.com/quill@1.3.7/dist/quill.snow.css\" rel=\"stylesheet\" />");
			file.writeln(1, "<link id=\"theme-css\" rel=\"stylesheet\" type=\"text/css\" href=\"assets/layout/styles/theme/lara-light-blue/theme.css\">");
			file.breakLine();
			file.writeln(0, "</head>");
			file.breakLine();
			file.writeln(0, "<body>");
			file.writeln(1, "<app-root></app-root>");
			file.writeln(0, "</body>");
			file.breakLine();
			file.writeln(0, "</html>");
		}
	}

	private void copyStyles() {
		
	}
}
