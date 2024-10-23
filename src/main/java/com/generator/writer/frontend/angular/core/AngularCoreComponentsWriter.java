package com.generator.writer.frontend.angular.core;

import com.generator.util.FileUtils;
import com.generator.writer.frontend.angular.AngularFileStructureCopier;
import com.generator.writer.utils.WriterUtils;

public class AngularCoreComponentsWriter {
	public void create() throws Exception {
		String sourceDir = FileUtils.getDiscPackagePath(AngularCoreComponentsWriter.class);
		String destDir = WriterUtils.getFrontendRootPackagePath() + "core";
		System.out.println(sourceDir);
		System.out.println(destDir);
		AngularFileStructureCopier.copyAngularFiles(sourceDir, destDir);
	}
}
