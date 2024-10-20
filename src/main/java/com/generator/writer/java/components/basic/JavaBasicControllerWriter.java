package com.generator.writer.java.components.basic;

import java.io.IOException;

import com.generator.Application;
import com.generator.annotations.BasicComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.model.enums.ComponentType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.java.JavaImportUtil;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@BasicComponent(ComponentType.CONTROLLER)
@WriterVersion("1.0")
public class JavaBasicControllerWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getControllerPackagePath(false, entity.getName()), upperCaseName + "ControllerBasic.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportControllerPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportControllerPackageName(false) + ".GenericController;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, WriterUtils.getApplicationExceptionImport());
			file.writeln(0, "");
			file.writeln(0, JavaImportUtil.getJakartaValidationValidImport());
			file.writeln(0, "import org.springframework.http.ResponseEntity;");
			// TODO FIX IMPORTS!!!
			file.writeln(0, JavaImportUtil.getSearchDtoImport());
			if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(0, JavaImportUtil.getSecurityUtilsImport());
			file.writeln(0, "");
			file.writeln(0, "public class " + upperCaseName + "ControllerBasic extends GenericController<" + upperCaseName + "> {");
			file.writeln(1, "protected " + upperCaseName + "Service " + entity.getName() + "Service;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "ControllerBasic(" + upperCaseName + "Service service) {");
			file.writeln(2, "super(service);");
			file.writeln(2, "this." + entity.getName() + "Service = service;");
			file.writeln(1, "}");
			file.writeln(0, "");

			writeEndpointPermissions(file, upperCaseName, snakeCaseName);

			file.writeln(0, "");
			file.writeln(0, "}");

		}

	}

	private void writeEndpointPermissions(GeneratorOutputFile file, String upperCaseName, String snakeCaseName) throws Exception {
		writeFindAllEndpoint(file, snakeCaseName);

		writeSearchEndpoint(file, snakeCaseName);

		writeDeleteByIdEndpoint(file, snakeCaseName);

		writeGetByIdEndpoint(file, snakeCaseName);

		writeUpdateEndpoint(file, upperCaseName, snakeCaseName);

		writeInsertEndpoint(file, upperCaseName, snakeCaseName);

	}

	private void writeFindAllEndpoint(GeneratorOutputFile file, String snakeCaseName) throws IOException {
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_view_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> findAll() {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_view_" + snakeCaseName + "\");");
		file.writeln(2, "return super.findAll();");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSearchEndpoint(GeneratorOutputFile file, String snakeCaseName) throws IOException {
//		file.writeln(1, "@GetMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
//		file.writeln(1, "public ResponseEntity<?> search(@RequestParam(value = \"specification\", required = false) Specification<T> specification) {");
//		file.writeln(2, "return ResponseEntity.ok(service.findAll(specification));");
//		file.writeln(1, "}");
//		file.writeln(0, "");
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_view_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> searchPost(SearchDTO request) throws " + WriterUtils.getApplicationExceptionName() + " {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_view_" + snakeCaseName + "\");");
		file.writeln(2, "return super.searchPost(request);");
		file.writeln(1, "}");
		file.writeln(0, "");

		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_view_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> searchPageablePost(SearchDTO request) throws " + WriterUtils.getApplicationExceptionName() + " {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_view_" + snakeCaseName + "\");");
		file.writeln(2, "return super.searchPageablePost(request);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeDeleteByIdEndpoint(GeneratorOutputFile file, String snakeCaseName) throws IOException {
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_delete_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> delete(Long id) {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_delete_" + snakeCaseName + "\");");
		file.writeln(2, "return super.delete(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeGetByIdEndpoint(GeneratorOutputFile file, String snakeCaseName) throws IOException {
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_view_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> getById(Long id) {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_view_" + snakeCaseName + "\");");
		file.writeln(2, "return super.getById(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeUpdateEndpoint(GeneratorOutputFile file, String upperCaseName, String snakeCaseName) throws IOException {
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_update_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> update(@Valid " + upperCaseName + " object) throws " + WriterUtils.getApplicationExceptionName() + " {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_update_" + snakeCaseName + "\");");
		file.writeln(2, "return super.update(object);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeInsertEndpoint(GeneratorOutputFile file, String upperCaseName, String snakeCaseName) throws IOException {
		file.writeln(1, "@Override");
//		file.writeln(1, "@PreAuthorize(\"hasAuthority('" + "can_create_" + snakeCaseName + "')\")");
		file.writeln(1, "public ResponseEntity<?> insert(@Valid " + upperCaseName + " object) throws " + WriterUtils.getApplicationExceptionName() + " {");
		if (Application.getGeneratorProperties().generatePermissionsOnEndpoints()) file.writeln(2, "SecurityUtils.checkAuthority(\"can_create_" + snakeCaseName + "\");");
		file.writeln(2, "return super.insert(object);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

}
