package com.generator.writer.frontend.angular.model;

import com.generator.model.AppModel;

public class AngularEntityModelWriter {
	public void create(AppModel appModel) throws Exception {
		AngularEntitiesWriter angularEntitiesWriter = new AngularEntitiesWriter();
		AngularEntityStructureWriter angularEntityStructureWriter = new AngularEntityStructureWriter();
		AngularEntityWriter angularEntityWriter = new AngularEntityWriter();
		AngularEnumWriter angularEnumWriter = new AngularEnumWriter();
		AngularStructureWriter angularStructureWriter = new AngularStructureWriter();

		angularEntitiesWriter.create(appModel);
		angularEntityStructureWriter.create(appModel);
		angularEntityWriter.create(appModel);
		angularEnumWriter.create(appModel);
		angularStructureWriter.create();
	}
}
