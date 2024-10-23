package com.generator.writer.frontend.angular.components.pages;

import com.generator.model.AppModel;

public class AngularPagesWriter {

	public void create(AppModel appModel) throws Exception{
		AngularSearchEntityPageWriter angularSearchEntityPageWriter = new AngularSearchEntityPageWriter();
		AngularCreateEntityPageWriter angularCreateEntityPageWriter = new AngularCreateEntityPageWriter();
		AngularEditEntityPageWriter angularEditEntityPageWriter = new AngularEditEntityPageWriter();
		
		angularSearchEntityPageWriter.create(appModel);
		angularCreateEntityPageWriter.create(appModel);
		angularEditEntityPageWriter.create(appModel);
	}
}
