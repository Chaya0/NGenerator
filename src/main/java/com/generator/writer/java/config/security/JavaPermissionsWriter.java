package com.generator.writer.java.config.security;

import com.generator.Application;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaPermissionsWriter implements DefaultWriter {
	@Override
	public void create(AppModel model) throws Exception {
		if(Application.getGeneratorProperties().isGenerateAuthorisationComponents() && Application.getGeneratorProperties().isGeneratePermissionsAndRoles())
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false, "permission"), "Permissions.java", true)) {
			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false, "permission") + ";");
			file.writeln(0, "");
			file.writeln(0, "public class Permissions {");
			file.writeln(0, "");
			file.writeln(1, "public interface EntityPermission {");
			file.writeln(2, "String getName();");
			file.writeln(1, "}");
			file.writeln(0, "");
			for (Entity entity : model.getEntities()) {
				createPermissionEnum(entity, file);
			}
			file.writeln(0, "}");
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
	}
	
	private void createPermissionEnum(Entity entity, GeneratorOutputFile file) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String snakeCaseName = StringUtils.camelToSnakeCase(entity.getName());
		file.writeln(1, "public enum " + upperCaseName + " implements EntityPermission {");
		file.writeln(2, "READ(\"can_view_" + snakeCaseName + "\"),");
		file.writeln(2, "UPDATE(\"can_update_" + snakeCaseName + "\"),");
		file.writeln(2, "CREATE(\"can_create_" + snakeCaseName + "\"),");
		file.writeln(2, "DELETE(\"can_delete_" + snakeCaseName + "\");");
		file.writeln(0, "");
		file.writeln(2, upperCaseName + "(String name) {");
		file.writeln(3, "this.name = name;");
		file.writeln(2, "}");
		file.writeln(0, "");
		file.writeln(2, "private final String name;");
		file.writeln(0, "");
		file.writeln(2, "@Override");
		file.writeln(2, "public String getName() {");
		file.writeln(3, "return name;");
		file.writeln(2, "}");
		file.writeln(1, "}");
		file.writeln(0, "");
	}
}
