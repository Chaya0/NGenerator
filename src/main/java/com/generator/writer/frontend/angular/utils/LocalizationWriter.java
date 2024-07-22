package com.generator.writer.frontend.angular.utils;

import java.util.Iterator;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.model.enums.RelationType;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class LocalizationWriter {

	public void create(AppModel appModel) throws Exception{
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendLocalizationPath(),"en.json", true)) {
//			if (file.hasAlreadyExisted()) {
//				return;
//			}
			Iterator<Entity> entityIterator = appModel.getEntities().iterator();
			while(entityIterator.hasNext()) {
				Entity entity = entityIterator.next();
				file.writeln(1, "\"" + entity.getName() + "\": {");
				Iterator<Attribute> attributeIterator = entity.getAttributes().iterator();
				for(Relation relation : entity.getRelations()) {
					file.writeln(2, "\"" + (relation.getRelationName() == null ? relation.getEntityName() : relation.getRelationName()) + "\": \"\",");
				}
				while(attributeIterator.hasNext()) {
					Attribute attribute = attributeIterator.next();
					if(attributeIterator.hasNext()) {
						file.writeln(2, "\"" + attribute.getName() + "\": \"\",");
					}else {
						file.writeln(2, "\"" + attribute.getName() + "\": \"\"");
					}
				}
				if(entityIterator.hasNext()) {
					file.writeln(1, "},");
				}else {
					file.writeln(1, "}");
				}
			}
		}
	}
	
}
