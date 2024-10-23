package com.generator.writer.frontend.angular.assets;

import com.generator.util.FileUtils;
import com.generator.writer.frontend.angular.AngularFileStructureCopier;
import com.generator.writer.utils.WriterUtils;

public class AngularStyleClassWriter {
	public void create() throws Exception {
		String sourceDir = FileUtils.getDiscPackagePath(AngularStyleClassWriter.class);
		String destDir = WriterUtils.getFrontendSrcPath() +  "assets/";
		System.out.println(sourceDir);
		System.out.println(destDir);
		AngularFileStructureCopier.copyAngularFiles(sourceDir, destDir, false);
	}
}
