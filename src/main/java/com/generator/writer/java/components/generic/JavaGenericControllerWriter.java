package com.generator.writer.java.components.generic;

import java.io.IOException;

import com.generator.annotations.GenericComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.model.enums.ComponentType;
import com.generator.writer.DefaultWriter;
import com.generator.writer.java.JavaImportUtil;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@GenericComponent(ComponentType.CONTROLLER)
@WriterVersion("1.0")
public class JavaGenericControllerWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		create();
	}

	public void create() throws Exception {

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getControllerPackagePath(false), "GenericController.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportControllerPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.web.bind.annotation.*;");
			file.writeln(0, "import org.springframework.http.*;");
			file.writeln(0, "import org.springframework.data.domain.*;");
			file.writeln(0, "import org.springframework.data.jpa.domain.*;");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import jakarta.validation.constraints.Max;");
			file.writeln(0, "import jakarta.validation.constraints.Min;");
			file.writeln(0, JavaImportUtil.getJakartaValidationValidImport());
			file.writeln(0, "import jakarta.persistence.EntityNotFoundException;");
			file.writeln(0, WriterUtils.getApplicationExceptionImport());
			// TODO dodati logiku za generisanje anotacije za autorizaciju
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false) + ".GenericService;");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".utils.ApiUtil;");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".specification.SpecificationBasic;");
			file.writeln(0, "import " + WriterUtils.getImportDefaultPackage() + ".specification.SearchDTO;");
			file.writeln(0, "");
//			file.writeln(0, "@CrossOrigin");
//			file.writeln(0, "@RestController");
			// TODO dodati logiku za generisanje anotacije za autorizaciju
//			file.writeln(0, "@SecurityRequirement(name = \"bearerAuth\")");
//			file.writeln(0, "@RequestMapping(\"/api/generic/" + entity.getName() +"\")");
			file.writeln(0, "public class GenericController<T> {");
			file.writeln(1, "private final GenericService<T> service;");
			file.writeln(0, "");
			file.writeln(1, "public GenericController(GenericService<T> service) {");
			file.writeln(2, "this.service = service;");
			file.writeln(1, "}");
			file.writeln(0, "");

			writeFindAllEndpoint(file);

			writeSearchEndpoint(file);

			//writeFindAllWithPagingEndpoint(file);

			//writeSearchWithPagingEndpoint(file);

			writeDeleteByIdEndpoint(file);

			writeGetByIdEndpoint(file);

			writeUpdateEndpoint(file);

			writeInsertEndpoint(file);

		}
	}

	@Override
	public void create(Entity entity) throws Exception {
	}

	private void writeFindAllEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@GetMapping(value = \"\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> findAll() {");
		file.writeln(2, "return ResponseEntity.ok(service.findAll());");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSearchEndpoint(GeneratorOutputFile file) throws IOException {
//		file.writeln(1, "@GetMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
//		file.writeln(1, "public ResponseEntity<?> search(@RequestParam(value = \"specification\", required = false) Specification<T> specification) {");
//		file.writeln(2, "return ResponseEntity.ok(service.findAll(specification));");
//		file.writeln(1, "}");
//		file.writeln(0, "");
		
		file.writeln(1, "@PostMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> searchPost(@RequestBody SearchDTO request) throws " + WriterUtils.getApplicationExceptionName() + " {");
		file.writeln(2, "SpecificationBasic<T> specification = request.buildSpecification(service.getEntityClass());");
		file.writeln(2, "return ResponseEntity.ok(service.findAll(specification.getSpecification()));");
		file.writeln(1, "}");
		file.writeln(0, "");
		
		file.writeln(1, "@PostMapping(value = \"/searchPageable\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> searchPageablePost(@RequestBody SearchDTO request) throws " + WriterUtils.getApplicationExceptionName() + " {");
		file.writeln(2, "SpecificationBasic<T> specification = request.buildSpecification(service.getEntityClass());");
		file.writeln(2, "return ResponseEntity.ok(service.findAll(specification.getSpecification(), request.createPageable()));");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	@SuppressWarnings("unused")
	private void writeFindAllWithPagingEndpoint(GeneratorOutputFile file) throws IOException {
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
	}

	@SuppressWarnings("unused")
	private void writeSearchWithPagingEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@GetMapping(value = \"/searchPagable\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> searchPagable(");
		file.writeln(2, "@RequestParam(value = \"specification\", required = false) Specification<T> specification,");
		file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_PAGE) @Min(ApiUtil.MIN_PAGE) Integer page,");
		file.writeln(2, "@RequestParam(defaultValue = ApiUtil.DEFAULT_SIZE) @Min(ApiUtil.MIN_SIZE) @Max(ApiUtil.MAX_SIZE) Integer size,");
		file.writeln(2, "@RequestParam(defaultValue = \"-id\") String[] sort) {");
		file.writeln(2, "Pageable pagable = ApiUtil.resolveSortingAndPagination(page, size, sort);");
		file.writeln(0, "");
		file.writeln(2, "return ResponseEntity.ok(service.findAll(specification,pagable));");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeDeleteByIdEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@DeleteMapping(value = \"/delete/{id}\")");
		file.writeln(1, "public ResponseEntity<?> delete(@PathVariable(\"id\") Long id) {");
		file.writeln(2, "Optional<T> optional = service.findById(id);");
		file.writeln(2, "if (optional.isPresent()) {");
		file.writeln(3, "service.deleteById(id);");
		file.writeln(3, "return ResponseEntity.noContent().build();");
		file.writeln(2, "}");
		file.writeln(2, "throw new EntityNotFoundException(\"id:\" + id.toString());");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeGetByIdEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@GetMapping(value = \"/{id}\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> getById(@PathVariable(\"id\") Long id) {");
		file.writeln(2, "Optional<T> optional = service.findById(id);");
		file.writeln(2, "if (optional.isPresent()) {");
		file.writeln(3, "return ResponseEntity.ok(optional.get());");
		file.writeln(2, "}");
		file.writeln(2, "throw new EntityNotFoundException(\"id:\" + id.toString());");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeUpdateEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@PutMapping(value = \"/update\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> update(@Valid @RequestBody T object) throws " + WriterUtils.getApplicationExceptionName() + " {");
//		file.writeln(2, "Optional<T> optional = service.findById(id);");
//		file.writeln(2, "if (optional.isPresent()) {");
		file.writeln(2, "return ResponseEntity.ok(service.update(object));");
//		file.writeln(2, "}");
//		file.writeln(2, "throw new EntityNotFoundException();");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeInsertEndpoint(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@PostMapping(value = \"\", produces = MediaType.APPLICATION_JSON_VALUE)");
		file.writeln(1, "public ResponseEntity<?> insert(@Valid @RequestBody T object) throws " + WriterUtils.getApplicationExceptionName() + " {");
		file.writeln(2, "return ResponseEntity.ok(service.insert(object));");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(0, "}");
	}

}
