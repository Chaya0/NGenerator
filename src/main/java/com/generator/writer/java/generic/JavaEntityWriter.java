package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.enums.AttributeType;
import com.generator.util.StringUtils;
import com.generator.util.Utils;
import com.generator.writer.BuilderOutputFile;
import com.generator.writer.Writer;

public class JavaEntityWriter implements Writer {

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
			
			file.writeln(0, "package " +";");
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
//			if(entity.getInhe)
			file.writeln(0, "@Entity(name = \"" + entity.getName() + "\")");
			file.writeln(0, "public class " + StringUtils.uppercaseFirst(entity.getName()) + " {");
		
			for(Attribute attribute : entity.getAttributes()) {
				writeAttributes(file, attribute);
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	private void writeAttributes(BuilderOutputFile file, Attribute attribute) throws Exception {
		if(attribute.isNullable()) {
			file.writeln(1, "@Column(nullable = true)");			
		}else {
			file.writeln(1, "@Column(nullable = false)");			
		}
		if(attribute.getAttributeType().equals(AttributeType.ENUM)) {
			file.writeln(1, "@Enumerated(EnumType.STRING)");
		}
		file.writeln(1, "private " + attribute.getAttributeType().getCode() + " " + attribute.getName() + ";");
	}
}
