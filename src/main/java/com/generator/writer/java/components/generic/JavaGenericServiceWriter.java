package com.generator.writer.java.components.generic;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaGenericServiceWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
		create();
	}
	
	public void create() throws Exception {

		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false), "GenericService.java", true)) {

			file.writeln(0, "package " + Utils.getImportServicePackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false) + ".GenericRepository" + ";");
			file.writeln(0, Utils.getApplicationExceptionImport());
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

//			writeRepositoryGetter(file);

			writeDeleteByIdMethod(file);
			file.writeln(0, "}");
		}

	}


	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false, entity.getName()), upperCaseName + "ServiceBasic.java", true)) {

			file.writeln(0, "package " + Utils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + Utils.getImportServicePackageName(false) + ".GenericService;");
			file.writeln(0, Utils.getApplicationExceptionImport());
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository" + ";");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "");
			file.writeln(0, "public class " + upperCaseName + "ServiceBasic extends GenericService<" + upperCaseName +"> {");
			file.writeln(1, "protected final " + upperCaseName + "Repository repository;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "ServiceBasic(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " insert(" + upperCaseName + " object) throws " + Utils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() != null) throw new " + Utils.getApplicationExceptionName() + "(\"\");");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " update(" + upperCaseName + " object) throws " + Utils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() == null || !repository.findById(object.getId()).isPresent()) throw new " + Utils.getApplicationExceptionName() + "(\"\");");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
			

			
				// TODO Auto-generated method stub
				

//			writeGetDataMethods(upperCaseName, file);
//
//			writeFindByIdMethod(upperCaseName, file);
//
//			writeSaveMethod(upperCaseName, file);
//
//			writeRepositoryGetter(upperCaseName, file);
//
//			writeDeleteByIdMethod(file);
	
	}
	
	private void writeGetDataMethods(String upperCaseName, GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public List<" + upperCaseName + "> findAll() {");
		file.writeln(2, "return repository.findAll();");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public Page<" + upperCaseName + "> findAll(Pageable pageable) {");
		file.writeln(2, "return repository.findAll(pageable);");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public List<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> specification) {");
		file.writeln(2, "return repository.findAll(specification);");
		file.writeln(1, "}");
		file.writeln(0, "");
		file.writeln(1, "public Page<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> specification, Pageable pageable) {");
		file.writeln(2, "return repository.findAll(specification, pageable);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeDeleteByIdMethod(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public void deleteById(Long id) {");
		file.writeln(2, "repository.deleteById(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeSaveMethod(String upperCaseName, GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public " + upperCaseName + " save(" + upperCaseName + " object) {");
		file.writeln(2, "return repository.save(object);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeFindByIdMethod(String upperCaseName, GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public Optional<" + upperCaseName + "> findById(Long id) {");
		file.writeln(2, "return repository.findById(id);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeRepositoryGetter(String upperCaseName, GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public " + upperCaseName + "Repository getRepository() {");
		file.writeln(2, "return repository;");
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
		file.writeln(1, "public abstract T insert(T object) throws " + Utils.getApplicationExceptionName() + ";");
		file.writeln(0, "");
		
		file.writeln(1, "public abstract T update(T object) throws " + Utils.getApplicationExceptionName() + ";");
		file.writeln(0, "");
		
		file.writeln(1, "public T save(T object) throws " + Utils.getApplicationExceptionName() + " {");
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

	private void writeRepositoryGetter(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public GenericRepository<T> getRepository() {");
		file.writeln(2, "return repository;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

}
