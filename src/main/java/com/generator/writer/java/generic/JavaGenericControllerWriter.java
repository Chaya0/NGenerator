package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.util.Utils;
import com.generator.writer.BuilderOutputFile;
import com.generator.writer.Writer;

public class JavaGenericControllerWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for(Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		if (Utils.classExsists()) {
			return;
		}

		String upercaseName = StringUtils.uppercaseFirst(entity.getName());
		String outputPackage = "";
		try (BuilderOutputFile file = Utils.getOutputResource(Utils.getPackageName(properties.getControllerPackageName(), outputPackage), StringUtils.uppercaseFirst(entity.getName()) + "BusinessService.java",
				false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			// ==============================================================================
			// Zaglavlje
			// ==============================================================================
			file.writeln(0, "package " + Utils.getPackageName(data.getParameters(), data.getParameters().get("packageBusinessServiceImplementation").getValue()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import lombok.Data");
			file.writeln(0, "import lombok.AllArgsConstructor");
			file.writeln(0, "import lombok.NoArgsConstructor");
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "public class " + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessService extends " + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessServiceBase {");
			file.writeln(1, "protected static final Logger log = Logger.getLogger(" + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessService.class);");
			file.writeln(0, "");
			file.writeln(1, "public " + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessService() throws Exception {");
			file.writeln(2, "super();");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public " + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessService(String connectionName) throws Exception {");
			file.writeln(2, "super(connectionName);");
			file.writeln(1, "}");
			file.writeln(0, "");
			if (!entity.isCodeTable()) {
				file.writeln(1, "public " + GStringUtils.uppercaseFirst(datagrid.getName()) + "BusinessService(String connectionName, CCMAuthentication authentication) throws Exception {");
				file.writeln(2, "super(connectionName, authentication);");
				file.writeln(1, "}");
			}
			file.writeln(0, "}");
		}

	}

}
