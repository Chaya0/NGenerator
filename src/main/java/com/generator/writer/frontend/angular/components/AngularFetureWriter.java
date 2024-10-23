package com.generator.writer.frontend.angular.components;

import com.generator.model.AppModel;

public class AngularFetureWriter {
	public void create(AppModel appModel) throws Exception {
		AngularInsertFormComponentWriter angularInsertFormComponentWriter = new AngularInsertFormComponentWriter();
		AngularSearchFormComponentWriter angularSearchFormComponentWriter = new AngularSearchFormComponentWriter();
		AngularTableViewComponentWriter angularTableViewComponentWriter = new AngularTableViewComponentWriter();
		AngularUpdateFormComponentWriter angularUpdateFormComponentWriter = new AngularUpdateFormComponentWriter();

		angularInsertFormComponentWriter.create(appModel);
		angularSearchFormComponentWriter.create(appModel);
		angularTableViewComponentWriter.create(appModel);
		angularUpdateFormComponentWriter.create(appModel);
	}
}
