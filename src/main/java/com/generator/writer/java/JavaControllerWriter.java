package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.util.Utils;
import com.generator.writer.BuilderOutputFile;
import com.generator.writer.Writer;

public class JavaControllerWriter implements Writer {
	
	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String uperCaseName = StringUtils.uppercaseFirst(entity.getName());
//		String outputFile = Application.getSpringProperties().getProjectPath() + Application.getSpringProperties().getPackageName()
		if (Utils.classExsists()) {
			return;
		}

		String outputPackage = "";
		try (BuilderOutputFile file = Utils.getOutputResource(Utils.getPackageName(properties.getControllerPackageName(), outputPackage), StringUtils.uppercaseFirst(entity.getName()) + "BusinessService.java",
				false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + ";");
			file.writeln(0, "");
			file.writeln(0, "import lombok.Data");
			file.writeln(0, "import lombok.AllArgsConstructor");
			file.writeln(0, "import lombok.NoArgsConstructor");
			file.writeln(0, "import javax.persistence.*");
			file.writeln(0, "import java.util.*");
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "@Entity(name = \"" + entity.getName() + "\")");
			file.writeln(0, "public class " + uperCaseName + " {");
			file.writeln(0, "");

			file.writeln(1, "public List<" + uperCaseName + "> findAll() {");
			file.writeln(2, "return repository.findAll()");
			file.writeln(1, "}");
			file.writeln(0, "");

			file.writeln(1, "public List<" + uperCaseName + "> findAll(Pageable pageSort) {");
			file.writeln(2, "return repository.findAll(pageSort)");
			file.writeln(1, "}");
			file.writeln(0, "");

			file.writeln(1, "public List<" + uperCaseName + "> findAll(Specification<" + uperCaseName + "> specification) {");
			file.writeln(2, "return repository.findAll(specification)");
			file.writeln(1, "}");
			file.writeln(0, "");
			
			file.writeln(1, "public Optional<" + uperCaseName + "> findById(Long id) {");
			file.writeln(2, "return repository.findById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			
			file.writeln(1, "public " + uperCaseName + " save(" + uperCaseName + " " +  entity.getName() + ") {");
			file.writeln(2, "return repository.findById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");

			file.writeln(1, "public void deleteById(Long id) {");
			file.writeln(2, "return repository.deleteById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			
			file.writeln(1, outputPackage);

			/*
			 * public Optional<Faktura> findById(Long id) {
			 * return fakturaRepository.findByDokumentId(id);
			 * }
			 * 
			 * public List<Faktura> findAll(Specification<Faktura> spec) {
			 * return fakturaRepository.findAll(spec);
			 * }
			 * 
			 * @Override
			 * public Page<Faktura> findAll(Pageable pageSort) {
			 * return fakturaRepository.findAll(pageSort);
			 * }
			 */
			file.writeln(0, "}");
		}

	}

}
