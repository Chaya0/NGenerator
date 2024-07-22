package com.generator.writer.frontend.angular.components.shared;

import java.io.IOException;

import com.generator.writer.frontend.SimpleComponentWriter;
import com.generator.writer.utils.GeneratorOutputFile;

public class AngularGenericUpdateFormWriter implements SimpleComponentWriter {

	@Override
	public void create() throws Exception {
		writeScript();
		writeStyles();
		writeHTML();		
	}
	
    @Override
    public void writeScript() throws IOException {
        try (GeneratorOutputFile file = new GeneratorOutputFile("" + "/generic-update-form.component.ts")) {
            file.writeln(0, "import { Component, inject, Input } from '@angular/core';");
            file.writeln(0, "import { AsyncPipe, NgForOf, NgIf } from \"@angular/common\";");
            file.writeln(0, "import { FormBuilder } from \"@angular/forms\";");
            file.writeln(0, "import { MatSnackBar } from \"@angular/material/snack-bar\";");
            file.writeln(0, "import { GenericFormComponent } from \"../generic-form/generic-form.component\";");
            file.writeln(0, "import { MaterialModule } from \"../../material/material.module\";");
            file.writeln(0, "import { ApiService } from \"../../../core/services/api.service\";");
            file.writeln(0, "import { AppUtils } from \"../../utils/app-utils\";");
            file.writeln(0, "");
            file.writeln(0, "@Component({");
            file.writeln(1, "selector: 'app-generic-update-form',");
            file.writeln(1, "standalone: true,");
            file.writeln(1, "imports: [");
            file.writeln(2, "AsyncPipe,");
            file.writeln(2, "MaterialModule,");
            file.writeln(2, "NgForOf,");
            file.writeln(2, "NgIf");
            file.writeln(1, "],");
            file.writeln(1, "templateUrl: '../generic-form/generic-form.component.html',");
            file.writeln(1, "styleUrls: ['../generic-form/generic-form.component.css']");
            file.writeln(0, "})");
            file.writeln(0, "export class GenericUpdateFormComponent extends GenericFormComponent {");
            file.writeln(1, "@Input() id: any;");
            file.writeln(1, "override fb: FormBuilder = inject(FormBuilder);");
            file.writeln(1, "override snackBar: MatSnackBar = inject(MatSnackBar);");
            file.writeln(1, "protected apiService: ApiService = inject(ApiService);");
            file.writeln(0, "");
            file.writeln(1, "onSubmit(): void {");
            file.writeln(2, "if (!this.form?.valid) return;");
            file.writeln(2, "this.update(this.form.value, this.entity, () => {");
            file.writeln(3, "this.snackBar.open('Entity updated successfully', 'Close', { duration: 3000 });");
            file.writeln(2, "});");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "@Override");
            file.writeln(1, "initOptions(): void {");
            file.writeln(2, "super.initOptions();");
            file.writeln(2, "this.getById(this.id, this.entity, (data: { [key: string]: any; }) => {");
            file.writeln(3, "this.data = data;");
            file.writeln(3, "this.form?.patchValue(data);");
            file.writeln(2, "});");
            file.writeln(1, "}");
            file.writeln(0, "");
            file.writeln(1, "protected readonly AppUtils = AppUtils;");
            file.writeln(0, "}");
        }
    }

    @Override
    public void writeStyles() throws IOException {
        // Assuming the styles are shared with the generic form and no specific styles are needed for the update form
        // If specific styles are needed, update this section accordingly
    }

    @Override
    public void writeHTML() throws IOException {
        // The HTML template is shared with the generic form, no need to generate a new file for the update form
        // If a specific template is needed, update this section accordingly
    }

}