package com.generator.writer.frontend.angular.pages;

import com.generator.util.FileUtils;
import com.generator.writer.frontend.angular.AngularFileStructureCopier;
import com.generator.writer.utils.WriterUtils;

public class AngularTemplatePagesWriter {
	public void create() throws Exception {
		String sourceDir = FileUtils.getDiscPackagePath(AngularTemplatePagesWriter.class);
		String destDir = WriterUtils.getFrontendRootPackagePath() + "pages";
		System.out.println(sourceDir);
		System.out.println(destDir);
		AngularFileStructureCopier.copyAngularFiles(sourceDir, destDir);
	}
}
