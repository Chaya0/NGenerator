package com.generator;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.reader.XMLModelReader;
import com.generator.util.StringUtils;
import com.generator.writer.Utils;
import com.generator.writer.frontend.angular.AngularBashUtil;
import com.generator.writer.frontend.angular.model.AngularEntityStructureWriter;
import com.generator.writer.frontend.angular.model.AngularEntityWriter;

public class FrontendApplicationGenerator {
	private static AppModel appModel = XMLModelReader.readModel();
	public static void main(String[] args) throws Exception {
		AngularEntityStructureWriter entityStructureWriter = new AngularEntityStructureWriter();
		AngularEntityWriter entityWriter = new AngularEntityWriter();
		AngularBashUtil.createNewAngularApp(Application.getSpringProperties().getProjectPath(), Application.getSpringProperties().getBaseDir());
		for(Entity entity : appModel.getEntities()) {
			String path = Utils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName())+"/";
			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-delete-action");
			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-insert-form");
			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-search-form");
			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-table-view");
			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-update-form");
			entityStructureWriter.create(entity);
			entityWriter.create(entity);
		}
	}
}
