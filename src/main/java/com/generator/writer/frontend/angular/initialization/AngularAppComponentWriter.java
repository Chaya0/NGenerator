package com.generator.writer.frontend.angular.initialization;

import com.generator.util.FileUtils;
import com.generator.writer.frontend.angular.AngularFileStructureCopier;
import com.generator.writer.utils.WriterUtils;

public class AngularAppComponentWriter {
	public void create() throws Exception {
		String sourceDir = FileUtils.getDiscPackagePath(AngularAppComponentWriter.class);
		String destDir = WriterUtils.getFrontendSrcPath();
		System.out.println(sourceDir);
		System.out.println(destDir);
		AngularFileStructureCopier.copyAngularFiles(sourceDir, destDir);
	}
}
