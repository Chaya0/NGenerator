package com.generator.writer.java.components.basic;

import com.generator.Application;
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

@BasicComponent(ComponentType.SERVICE)
@WriterVersion("1.0")
public class JavaBasicServiceWriter implements DefaultWriter {
	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false, entity.getName()), upperCaseName + "ServiceBasic.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false) + ".GenericService;");
			file.writeln(0, WriterUtils.getApplicationExceptionImport());
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository" + ";");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, "import jakarta.persistence.EntityNotFoundException;");
			if(entity.getName().equals("user") && Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "import org.springframework.security.core.userdetails.*;");
			}
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + StringUtils.uppercaseFirst(attribute.getEnumName()) + ";");
				}
			}
			file.writeln(0, "");
			if(entity.getName().equals("user") && Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(0, "public class " + upperCaseName + "ServiceBasic extends GenericService<" + upperCaseName + "> implements UserDetailsService {");
			}else {
				file.writeln(0, "public class " + upperCaseName + "ServiceBasic extends GenericService<" + upperCaseName + "> {");
			}
			file.writeln(1, "protected final " + upperCaseName + "Repository repository;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "ServiceBasic(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " insert(" + upperCaseName + " object) throws " + WriterUtils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() != null) throw new " + WriterUtils.getApplicationExceptionName() + "(\"Entity already exists: \" + object.getId());");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " update(" + upperCaseName + " object) throws " + WriterUtils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() == null || repository.findById(object.getId()).isEmpty()) throw new EntityNotFoundException(String.valueOf(object.getId()));");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public Class<" + upperCaseName + "> getEntityClass() {");
			file.writeln(2, "return " + upperCaseName + ".class;");
			file.writeln(1, "}");
			file.writeln(0, "");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.isUnique()) {
					if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
						file.writeln(1, "public " + upperCaseName + " findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ") {");
					}else{
						file.writeln(1, "public " + upperCaseName + " findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ") {");
					}				
					file.writeln(2, "return repository.findBy" + StringUtils.uppercaseFirst(attribute.getName())+ "(" + attribute.getName() + ").orElseThrow();");

				}else {
					if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
						file.writeln(1, "public List<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ") {");
					}else{
						file.writeln(1, "public List<" + upperCaseName + "> findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ") {");
					}
					file.writeln(2, "return repository.findBy" + StringUtils.uppercaseFirst(attribute.getName())+ "(" + attribute.getName() + ");");
				}
				file.writeln(1, "}");
			}
			if(entity.getName().equals("user") && Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.breakLine();
				file.writeln(1, "@Override");
				file.writeln(1, "public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {");
				file.writeln(2, "Optional<User> myUser = repository.findByUsername(username);");
				file.writeln(2, "if (myUser.isEmpty()) {");
				file.writeln(3, "throw new UsernameNotFoundException(\"User name \" + username + \" not found\");");
				file.writeln(2, "}");
				if(Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
					file.writeln(2, "return new org.springframework.security.core.userdetails.User(myUser.get().getUsername(), myUser.get().getPassword(), myUser.get().getRole().getPermissionList());");
				} else {
					file.writeln(2, "return new org.springframework.security.core.userdetails.User(myUser.get().getUsername(), myUser.get().getPassword(), null);");
				}
				file.writeln(1, "}");
				
			}
			
			file.breakLine();
			
			file.writeln(0, "}");
		}
	}

}
