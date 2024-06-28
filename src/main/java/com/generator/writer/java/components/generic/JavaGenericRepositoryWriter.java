package com.generator.writer.java.components.generic;

import org.apache.poi.util.StringUtil;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaGenericRepositoryWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		create();
		for(Entity entity : model.getEntities()){
			create(entity);
		}
	}
	
	/**
	 * 	Creates a generic repository that doesn't require object type.
	 * @throws Exception
	 */
	public void create() throws Exception {
		
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getRepositoryPackagePath(false), "GenericRepository.java", true)) {
			file.writeln(0, "package " + Utils.getImportRepositoryPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
			file.writeln(0, "import org.springframework.data.repository.NoRepositoryBean;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "@NoRepositoryBean");
			file.writeln(0, "public interface GenericRepository<T> extends JpaRepository<T, Long> {");
			file.writeln(0, "");
			file.writeln(1, "Page<T> findAll(Specification<T> spec, Pageable pageSort);");
			file.writeln(1, "List<T> findAll(Specification<T> spec);");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "RepositoryBasic.java", true)) {

			file.writeln(0, "package " + Utils.getImportRepositoryPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.repository.NoRepositoryBean;");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false) + ".GenericRepository;");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(0, "import " + Utils.getImportModelEnumsPackageName() + "." + StringUtils.uppercaseFirst(attribute.getEnumName()) + ";");
				}
			}
			file.writeln(0, "");
			file.writeln(0, "@NoRepositoryBean");
			file.writeln(0, "public interface " + upperCaseName + "RepositoryBasic extends GenericRepository<" + upperCaseName + "> {");
			file.writeln(0, "");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(1, "Optional<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ");");
				}else{
					file.writeln(1, "Optional<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ");");
				}
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}
}
