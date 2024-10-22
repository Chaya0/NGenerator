package com.generator.writer.java.config.security;

import java.io.File;
import java.util.List;

import com.generator.Application;
import com.generator.util.FileUtils;
import com.generator.writer.TemplateWriter;
import com.generator.writer.java.JavaTemplateWriterUtil;
import com.generator.writer.utils.SSLCertificateGenerator;
import com.generator.writer.utils.WriterUtils;

public class JavaSecurityWriter implements TemplateWriter {

	@Override
	public void create() throws Exception {
		if (!Application.getGeneratorProperties().isGenerateAuthorisationComponents()) return;
		for (File file : FileUtils.getSubFiles(JavaSecurityWriter.class)) {
			String[] fileParts = file.getName().split("\\.");
			if (fileParts.length > 2) {
				switch (Application.getGeneratorProperties().getAuthenticationType()) {
				case JWT: {
					if (file.getName().contains("jwt") && !file.getName().contains("jwtCookie")) {
						File targetFile = FileUtils.findFileInFolderByFileName(FileUtils.getTemplateFolder(JavaSecurityWriter.class), fileParts[0] + ".template");
						FileUtils.copyFile(file, targetFile);
					}
					break;
				}
				case JWT_COOKIE: {
					if (file.getName().contains("jwtCookie") && !file.getName().contains("Secure")) {
						File targetFile = FileUtils.findFileInFolderByFileName(FileUtils.getTemplateFolder(JavaSecurityWriter.class), fileParts[0] + ".template");
						FileUtils.copyFile(file, targetFile);
					}
					break;
				}
				case JWT_COOKIE_SECURE: {
					if (file.getName().contains("jwtCookieSecure")) {
						File targetFile = FileUtils.findFileInFolderByFileName(FileUtils.getTemplateFolder(JavaSecurityWriter.class), fileParts[0] + ".template");
						FileUtils.copyFile(file, targetFile);
						createCertificate();
					}
					break;
				}
				default: {
					break; // No action for other cases
				}
				}
			}
		}

		for (File file : FileUtils.getSubFiles(JavaSecurityWriter.class)) {
			if (file.getName().split("\\.").length <= 2) {
				if (file.getName().contains("AuthController")) {
					JavaTemplateWriterUtil.processFile(file, WriterUtils.getControllerPackagePath(false, "auth"));
				} else {
					JavaTemplateWriterUtil.processFile(file, WriterUtils.getSecurityPackagePath());
				}
			}
		}
	}

	private void createCertificate() {
		try {
			SSLCertificateGenerator.generateSelfSignedCertificate(WriterUtils.getProjectPath(), Application.getSpringProperties().getName() + "-alias",
					Application.getSpringProperties().getName() + "-password", Application.getSpringProperties().getName() + "-keystore-password");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void create(List<String> templeteFilesPath) throws Exception {

	}

	@Override
	public void create(String templetaFilePath) throws Exception {
		// TODO Auto-generated method stub

	}

}
