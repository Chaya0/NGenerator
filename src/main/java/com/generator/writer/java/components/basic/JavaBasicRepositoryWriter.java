package com.generator.writer.java.components.basic;

import com.generator.annotations.BasicComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.enums.ComponentType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@BasicComponent(ComponentType.REPOSITORY)
@WriterVersion("1.0")
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
		
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "RepositoryBasic.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.repository.NoRepositoryBean;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false) + ".GenericRepository;");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + StringUtils.uppercaseFirst(attribute.getEnumName()) + ";");
				}
			}
			file.writeln(0, "");
			file.writeln(0, "@NoRepositoryBean");
			file.writeln(0, "public interface " + upperCaseName + "RepositoryBasic extends GenericRepository<" + upperCaseName + "> {");
			file.writeln(0, "");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.isUnique()) {
					if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
						file.writeln(1, "Optional<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ");");
					}else{
						file.writeln(1, "Optional<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ");");
					}
				}else {
					if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
						file.writeln(1, "List<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ");");
					}else{
						file.writeln(1, "List<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ");");
					}
				}
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

}
