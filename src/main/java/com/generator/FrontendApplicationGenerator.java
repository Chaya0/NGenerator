package com.generator;

import java.util.Arrays;
import java.util.List;

import com.generator.model.AppModel;
import com.generator.reader.XMLModelReader;
import com.generator.writer.frontend.ComponentWriter;
import com.generator.writer.frontend.angular.components.AngularDeleteActionComponentWriter;
import com.generator.writer.frontend.angular.components.AngularInsertFormComponentWriter;
import com.generator.writer.frontend.angular.components.AngularSearchFormComponentWriter;
import com.generator.writer.frontend.angular.components.AngularTableViewComponentWriter;
import com.generator.writer.frontend.angular.components.AngularUpdateFormComponentWriter;
import com.generator.writer.frontend.angular.components.pages.AngularCreateEntityPageWriter;
import com.generator.writer.frontend.angular.components.pages.AngularEditEntityPageWriter;
import com.generator.writer.frontend.angular.components.pages.AngularSearchEntityPageWriter;
import com.generator.writer.frontend.angular.model.AngularEntitiesWriter;
import com.generator.writer.frontend.angular.model.AngularEntityStructureWriter;
import com.generator.writer.frontend.angular.model.AngularEntityWriter;
import com.generator.writer.frontend.angular.utils.LocalizationWriter;

public class FrontendApplicationGenerator {
	private static AppModel appModel = XMLModelReader.readModel();

	public static void main(String[] args) throws Exception {
//		AngularEntityStructureWriter entityStructureWriter = new AngularEntityStructureWriter();
//		AngularEntityWriter entityWriter = new AngularEntityWriter();
//		AngularBashUtil.createNewAngularApp(Application.getSpringProperties().getProjectPath(), Application.getSpringProperties().getBaseDir());
//		for(Entity entity : appModel.getEntities()) {
//			String path = Utils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName())+"/";
//			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-delete-action");
//			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-insert-form");
//			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-search-form");
//			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-table-view");
//			AngularBashUtil.createComponent(path, StringUtils.camelToKebabCase(entity.getName()) + "-update-form");
//			entityStructureWriter.create(entity);
//			entityWriter.create(entity);
//		}
		try {
			List<ComponentWriter> writers = Arrays.asList(new AngularCreateEntityPageWriter(), new AngularEditEntityPageWriter(), new AngularSearchEntityPageWriter(),
					new AngularDeleteActionComponentWriter(), new AngularInsertFormComponentWriter(), new AngularSearchFormComponentWriter(), new AngularTableViewComponentWriter(),
					new AngularUpdateFormComponentWriter());
			AngularEntityWriter angularEntityWriter = new AngularEntityWriter();
			AngularEntitiesWriter angularEntitiesWriter = new AngularEntitiesWriter();
			AngularEntityStructureWriter angularEntityStructureWriter = new AngularEntityStructureWriter();
			angularEntityWriter.create(appModel);
			angularEntitiesWriter.create(appModel);
			angularEntityStructureWriter.create(appModel);
			for (ComponentWriter writer : writers) {
				writer.create(appModel);
			}
//			
//			LocalizationWriter writer = new LocalizationWriter();
//			writer.create(appModel);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
