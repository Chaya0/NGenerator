package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaGenericControllerWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getControllerPackagePath(true), upperCaseName + "GenericController.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getControllerPackagePath(true), upperCaseName + "GenericController.java")) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportControllerPackageName(true) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.web.bind.annotation.*;");
			file.writeln(0, "import org.springframework.http.*;");
			file.writeln(0, "import org.springframework.data.domain.*;");
			file.writeln(0, "import org.springframework.data.jpa.domain.*;");
			file.writeln(0, "import javax.persistence.*");
			file.writeln(0, "import java.util.*");
			file.writeln(0, "import javax.validation.constraints.Max;");
			file.writeln(0, "import javax.validation.constraints.Min;");
			file.writeln(0, "import javax.validation.Valid;");
			file.writeln(0, "import " + Utils.getImportDefaultPackage() + ".utils.ApiUtil;");
			file.writeln(0, "");
			file.writeln(0, "@CrossOrigin");
			file.writeln(0, "@RestController");
			file.writeln(0, "@SecurityRequirement(name = \"bearerAuth\")");
			file.writeln(0, "@RequestMapping(\"/api/generic\")");
			file.writeln(0, "public class " + upperCaseName + "GenericController {");
			file.writeln(0, "private final " + upperCaseName + "Service service;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "GenericController(" + upperCaseName + "Service service) {");
			file.writeln(2, "this.service = service;");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Find all api
			 */
			file.writeln(1, "@GetMapping(value = \"/all\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> findAll() {");
			file.writeln(2, "return ResponseEntity.ok(service.findAll());");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Search api
			 */
			file.writeln(1, "@GetMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> search(@RequestParam(value = \"specification\", required = false) Specification<" + upperCaseName + "> specification) {");
			file.writeln(2, "return ResponseEntity.ok(service.findAll(specification));");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Find all with paging api
			 */
			file.writeln(1, "@GetMapping(value = \"/allPagable\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> findAllPagable(");
			file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_PAGE) @Min(ApiUtil.MIN_PAGE) Integer page,");
			file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_SIZE) @Min(ApiUtil.MIN_SIZE) @Max(ApiUtil.MAX_SIZE) Integer size,");
			file.writeln(2, "@RequestParam(defaultValue = \"-id\") String[] sort) {");
			file.writeln(2, "Pageable pagable = ApiUtil.resolveSortingAndPagination(page, size, sort);");
			file.writeln(0, "");
			file.writeln(2, "return ResponseEntity.ok(service.findAll(pagable));");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Search all with paging api
			 */
			file.writeln(1, "@GetMapping(value = \"/searchPagable\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> searchPagable(");
			file.writeln(2, "@RequestParam(value = \"specification\", required = false) Specification<" + upperCaseName + "> specification,");
			file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_PAGE) @Min(ApiUtil.MIN_PAGE) Integer page,");
			file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_SIZE) @Min(ApiUtil.MIN_SIZE) @Max(ApiUtil.MAX_SIZE) Integer size,");
			file.writeln(2, "@RequestParam(defaultValue = \"-id\") String[] sort) {");
			file.writeln(2, "Pageable pagable = ApiUtil.resolveSortingAndPagination(page, size, sort);");
			file.writeln(0, "");
			file.writeln(2, "return ResponseEntity.ok(service.findAll(specification,pagable));");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Delete by id api
			 */
			file.writeln(1, "@DeleteMapping(value = \"/delete/{id}\")");
			file.writeln(1, "public ResponseEntity<?> delete(@PathVariable(\"id\") Long id) {");
			file.writeln(2, "Optional<" + upperCaseName + "> optional = service.findById(id);");
			file.writeln(2, "if (optional.isPresent()) {");
			file.writeln(3, "service.deleteById(id);");
			file.writeln(3, "return ResponseEntity.noContent().build();");
			file.writeln(2, "}");
			file.writeln(2, "throw new EntityNotFoundException();");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Get by id api
			 */
			file.writeln(1, "@GetMapping(value = \"/get/{id}\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> getById(@PathVariable(\"id\") Long id) {");
			file.writeln(2, "Optional<" + upperCaseName + "> optional = service.findById(id);");
			file.writeln(2, "if (optional.isPresent()) {");
			file.writeln(3, "return ResponseEntity.ok(optional.get());");
			file.writeln(2, "}");
			file.writeln(2, "throw new EntityNotFoundException();");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Update api
			 */
			file.writeln(1, "@PutMapping(value = \"/update/{id}\", produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> update(@Valid @RequestBody " + upperCaseName + " object, @PathVariable Long id) {");
			file.writeln(2, "Optional<" + upperCaseName + "> optional = service.findById(id);");
			file.writeln(2, "if (optional.isPresent()) {");
			file.writeln(3, "return ResponseEntity.ok(service.save(object));");
			file.writeln(2, "}");
			file.writeln(2, "throw new EntityNotFoundException();");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Post api
			 */
			file.writeln(1, "@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)");
			file.writeln(1, "public ResponseEntity<?> update(@Valid @RequestBody " + upperCaseName + " object) {");
			file.writeln(2, "return ResponseEntity.ok(service.save(object));");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(0, "}");

		}
	}

}
