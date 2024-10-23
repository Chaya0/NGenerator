package com.generator.writer.frontend.angular.shared;

import com.generator.util.FileUtils;
import com.generator.writer.frontend.angular.AngularFileStructureCopier;
import com.generator.writer.frontend.angular.AngularTemplateWriter;
import com.generator.writer.utils.WriterUtils;

public class AngularSharedComponentsWriter implements AngularTemplateWriter {
	public void create() throws Exception {
		String sourceDir = FileUtils.getDiscPackagePath(AngularSharedComponentsWriter.class);
		String destDir = WriterUtils.getFrontendRootPackagePath() + "shared";
		System.out.println(sourceDir);
		System.out.println(destDir);
		AngularFileStructureCopier.copyAngularFiles(sourceDir, destDir);
	}
	
}
