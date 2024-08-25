package com.generator.writer.java.components.generic;

import java.io.IOException;

import com.generator.annotations.GenericComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.model.enums.ComponentType;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@GenericComponent(ComponentType.SERVICE)
@WriterVersion("1.0")
public class JavaGenericServiceWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		create();
	}

	public void create() throws Exception {

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false), "GenericService.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false) + ".GenericRepository" + ";");
			file.writeln(0, WriterUtils.getApplicationExceptionImport());
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "");
			file.writeln(0, "public abstract class GenericService<T> {");
			file.writeln(1, "private final GenericRepository<T> repository;");
			file.writeln(0, "");
			file.writeln(1, "public GenericService(GenericRepository<T> repository) {");
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");

			writeGetDataMethods(file);

			writeFindByIdMethod(file);

			writeSaveMethod(file);

			writeDeleteByIdMethod(file);
			
			file.writeln(1, "public abstract Class<T> getEntityClass();");
			
			file.writeln(0, "}");
		}

	}

	@Override
	public void create(Entity entity) throws Exception {
	}

	private void writeDeleteByIdMethod(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public void deleteById(Long id) {");
		file.writeln(2, "repository.deleteById(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeGetDataMethods(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public List<T> findAll() {");
		file.writeln(2, "return repository.findAll();");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public Page<T> findAll(Pageable pageable) {");
		file.writeln(2, "return repository.findAll(pageable);");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public List<T> findAll(Specification<T> specification) {");
		file.writeln(2, "return repository.findAll(specification);");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public Page<T> findAll(Specification<T> specification, Pageable pageable) {");
		file.writeln(2, "return repository.findAll(specification, pageable);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSaveMethod(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public abstract T insert(T object) throws " + WriterUtils.getApplicationExceptionName() + ";");
		file.writeln(0, "");

		file.writeln(1, "public abstract T update(T object) throws " + WriterUtils.getApplicationExceptionName() + ";");
		file.writeln(0, "");

		file.writeln(1, "public T save(T object) {");
		file.writeln(2, "return repository.save(object);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeFindByIdMethod(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public Optional<T> findById(Long id) {");
		file.writeln(2, "return repository.findById(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

}
