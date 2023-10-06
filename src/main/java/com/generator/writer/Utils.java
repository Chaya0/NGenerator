package com.generator.writer;

import java.io.File;

import com.generator.Application;

public class Utils {
	/**
	 * @param folder   - String
	 * @param fileName - String
	 */
	public static boolean fileExists(String folder, String filename) {
		String fullPath = folder + "/" + filename;

		File file = new File(fullPath);
		return file.exists();
	}

	public static GeneratorOutputFile getOutputResource(String folder, String filename, String charset, boolean overwrite) throws Exception {
		folder = getCorrectPath(folder);
		String fullPath = folder + "/" + filename;
		GFolder fullFolderPath = new GFolder(folder);
		if (!fullFolderPath.create()) throw new Exception("Could not create folder " + folder);

		return new GeneratorOutputFile(fullPath, charset, overwrite);
	}
	
	public static GeneratorOutputFile getOutputResource(String folder, String filename, boolean overwrite) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", overwrite);
	}

	public static GeneratorOutputFile getOutputResource(String folder, String filename) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", true);
	}

	public static String getCorrectPath(String pathWithDots) {
		return pathWithDots.replace(".", "/");
	}

	public static String getMainPackagePath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getArtifactId());
		stringBuilder.append("/src/main/java/");
		stringBuilder.append(getCorrectPath(Application.getSpringProperties().getPackageName()));
		return stringBuilder.toString();
	}

	public static String getImportDefaultPackage() {
		return Application.getSpringProperties().getPackageName();
	}
	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getControllerPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/controller/generic" : getMainPackagePath() + "/controller";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getRepositoryPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/repository/generic" : getMainPackagePath() + "/repository";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getServicePackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/service/generic" : getMainPackagePath() + "/service";
	}

	public static String getModelPackagePath() {
		return getMainPackagePath() + "/model";
	}

	public static String getModelEnumsPackagePath() {
		return getModelPackagePath() + "/enums";
	}
	public static String getUtilsPackagePath() {
		return getMainPackagePath() + "/utils";
	}
	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportControllerPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".controller.generic" : getImportDefaultPackage() + ".controller";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportServicePackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".service.generic" : getImportDefaultPackage() + ".service";

	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportRepositoryPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".repository.generic" : getImportDefaultPackage() + ".repository";
	}

	public static String getImportModelPackageName() {
		return getImportDefaultPackage() + ".model";
	}
	public static String getImportModelEnumsPackageName() {
		return getImportModelPackageName() + ".enums";
	}
}
