package com.generator.writer.java.components.basic;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaBasicRepositoryWriter implements DefaultWriter{

	@Override
	public void create(AppModel model) throws Exception {
		for(Entity entity : model.getEntities()){
			create(entity);
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
