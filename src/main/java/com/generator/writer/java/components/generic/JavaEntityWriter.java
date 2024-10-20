package com.generator.writer.java.components.generic;

import org.jsoup.internal.StringUtil;

import com.generator.Application;
import com.generator.annotations.GenericComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.model.enums.ComponentType;
import com.generator.model.enums.FetchType;
import com.generator.model.enums.InheritanceType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@GenericComponent(ComponentType.MODEL)
@WriterVersion("1.0")
public class JavaEntityWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getModelPackagePath(), upperCaseName + ".java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportModelPackageName() + ";");
			file.writeln(0, "");
			file.writeln(0, "import jakarta.persistence.*;");
			file.writeln(0, "import jakarta.validation.constraints.NotEmpty;");
			file.writeln(0, "import jakarta.validation.constraints.NotNull;");
			file.writeln(0, "import lombok.Data;");
			file.writeln(0, "import lombok.AllArgsConstructor;");
			file.writeln(0, "import lombok.NoArgsConstructor;");
			file.writeln(0, "import com.fasterxml.jackson.annotation.JsonIgnore;");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			if (entity.getName().equalsIgnoreCase("permission") || entity.getName().equalsIgnoreCase("role")) {
				file.writeln(0, "import java.util.stream.*;");
				file.writeln(0, "import org.springframework.security.core.GrantedAuthority;");
				if(entity.getName().equalsIgnoreCase("role")) file.writeln(0, "import org.springframework.security.core.authority.SimpleGrantedAuthority;");
			}
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "@Entity");
			if (!entity.getInheritanceType().equals(InheritanceType.NULL)) {
				file.writeln(0, entity.getInheritanceType().getGeneratorCode());
			}
			file.writeln(0, "@Table(name = \"" + (StringUtil.isBlank(entity.getTableName()) ? entity.getName() : entity.getTableName()) + "\")");
			if (entity.getInherits() != null && !entity.getInherits().isEmpty()) {
				file.writeln(0, "@PrimaryKeyJoinColumn(name = \"id\")");
				file.writeln(0, "public class " + upperCaseName + " extends " + StringUtils.uppercaseFirst(entity.getInherits()) + " {");
				file.writeln(0, "");
			} else {
				if (entity.getName().equalsIgnoreCase("permission") && Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
					file.writeln(0, "public class " + upperCaseName + " implements GrantedAuthority{");
				} else {
					file.writeln(0, "public class " + upperCaseName + " {");
				}
				file.writeln(0, "");
				file.writeln(1, "@Id");
				file.writeln(1, entity.getGenerationType().getGeneratorCode());
				file.writeln(1, "private Long id;");
			}

			writeAttributes(file, entity);
			writeRelations(file, entity);
			if (entity.getName().equalsIgnoreCase("permission") && Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
				file.writeln(0, "");
				file.writeln(1, "@Override");
				file.writeln(1, "public String getAuthority() {");
				file.writeln(2, "return name;");
				file.writeln(1, "}");
			}
			if (entity.getName().equalsIgnoreCase("role") && Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
				file.writeln(0, "");
				file.writeln(1, "@JsonIgnore");
				file.writeln(1, "public List<GrantedAuthority> getAuthorities() {");
				file.writeln(2, "return permissionList.stream().map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toList());");
				file.writeln(1, "}");
			}
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	private void writeAttributes(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Attribute attribute : entity.getAttributes()) {
			StringBuilder annotation = new StringBuilder("@Column");
			boolean addComma = false;
			boolean closeBracket = false;
			if (!attribute.isNullable()) {
				annotation.append("(");
				file.writeln(1, attribute.getType().equals(AttributeType.ENUM) ? "@NotNull" : "@NotEmpty");
				annotation.append("nullable = false");
				closeBracket = true;
				addComma = true;
			}
			if (attribute.isUnique()) {
				if (addComma) {
					annotation.append(", ");
//					addComma = false;
				} else {
					annotation.append("(");
				}
				annotation.append("unique = true");
				closeBracket = true;
			}
			if (closeBracket) {
				annotation.append(")");
			}

			file.writeln(1, annotation.toString());
			if (attribute.getType().equals(AttributeType.ENUM)) {
				if (attribute.getEnumName().isEmpty()) throw new Exception("Attribute defined as Enum but enum name wasn't provided.");
				file.writeln(1, "@Enumerated(EnumType.STRING)");
				file.writeln(1, "private " + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ";");
			} else {
				file.writeln(1, "private " + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ";");
			}
		}
	}

	private void writeRelations(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Relation relation : entity.getRelations()) {
			String fetchType = relation.getFetchType().equals(FetchType.NULL) ? "" : (", " + relation.getFetchType().getGeneratorCode());
			String relationName = StringUtil.isBlank(relation.getRelationName()) ? relation.getEntityName() : relation.getRelationName();
			switch (relation.getRelationType()) {
			case ONE_TO_MANY:
				file.writeln(1, relation.getRelationType().getGeneratorCode() + "(mappedBy = \"" + entity.getName() + "\"" + fetchType + ")");
				file.writeln(1, "@JsonIgnore");
				file.writeln(1, "List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relationName + "List;");
				file.writeln(0, "");
				break;
			case ONE_TO_ONE:
				if (relation.isOwningSide()) {
					file.writeln(1, relation.getRelationType().getGeneratorCode() + (relation.getFetchType().equals(FetchType.NULL) ? "" : ("(" + relation.getFetchType().getGeneratorCode() + ")")));
					file.writeln(1, "@JoinColumn(name = \"" + relation.getEntityName() + "\", referencedColumnName = \"id\")");
					file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relationName + ";");
					file.writeln(0, "");
				} else {
					file.writeln(1, relation.getRelationType().getGeneratorCode() + "(mappedBy = \"" + entity.getName() + "\")");
					file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relationName + ";");
					file.writeln(0, "");
				}
				break;
			case MANY_TO_MANY:
				if (relation.isOwningSide()) {
					file.writeln(1, relation.getRelationType().getGeneratorCode() + (relation.getFetchType().equals(FetchType.NULL) ? "" : ("(" + relation.getFetchType().getGeneratorCode() + ")")));
					file.writeln(1, "@JsonIgnore");
					file.writeln(1, "@JoinTable(name = \"" + entity.getName() + "_" + relation.getEntityName() + "\",");
					file.writeln(2, "joinColumns = @JoinColumn(name = \"" + entity.getName() + "\", referencedColumnName = \"id\"),");
					file.writeln(2, "inverseJoinColumns = @JoinColumn(name = \"" + relation.getEntityName() + "\", referencedColumnName = \"id\"))");
					file.writeln(1, "private List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relationName + "List;");
					file.writeln(0, "");
				} else {
					file.writeln(1, relation.getRelationType().getGeneratorCode() + "(mappedBy = \"" + entity.getName() + "List\")");
					file.writeln(0, "private List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relationName + "List;");
				}
				break;
			case MANY_TO_ONE:
				file.writeln(1, relation.getRelationType().getGeneratorCode() + (relation.getFetchType().equals(FetchType.NULL) ? "" : ("(" + relation.getFetchType().getGeneratorCode() + ")")));
				file.writeln(1, "@JoinColumn(name = \"" + (StringUtil.isBlank(relation.getTableName()) ? relation.getEntityName() : relation.getTableName()) + "\", referencedColumnName = \"id\")");
				file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relationName + ";");
				file.writeln(0, "");
				break;
			default:
				break;
			}
		}
	}
}
