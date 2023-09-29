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

	public static GeneratorOutputFile getOutputResource(String folder, String filename) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", true);
	}

	public static GeneratorOutputFile getOutputResource(String folder, String filename, boolean overwrite) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", overwrite);
	}

	public static GeneratorOutputFile createGenericOutputResource(int type, String baseFolder, String prefix, String filename, String suffix, String extension, boolean createJunction)
			throws Exception {
		GeneratorOutputFile file;
		file = Utils.getOutputResource(baseFolder, prefix + filename + suffix + "." + extension, false);
		file.close();

		file = Utils.getOutputResource(baseFolder + "/generic", prefix + filename + suffix + "." + extension, true);
		return file;
	}

	public static GeneratorOutputFile createGenericOutputResource(int type, String baseFolder, String filename, String suffix, String extension, boolean createJunction) throws Exception {
		return createGenericOutputResource(type, baseFolder, "", filename, suffix, extension, createJunction);
	}

	public static String getCorrectPath(String pathWithDots) {
		return pathWithDots.replace(".", "/");
	}

	public static String getMainPackagePath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getArtifactId());
		stringBuilder.append("src/main/java");
		stringBuilder.append(getCorrectPath(Application.getSpringProperties().getPackageName()));

		return stringBuilder.toString();
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getControllerPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "controller/generic/" : getMainPackagePath() + "controller/";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getRepositoryPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "controller/generic/" : getMainPackagePath() + "controller/";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getServicePackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "controller/generic/" : getMainPackagePath() + "controller/";
	}

	public static String getModelPackagePath() {
		return getMainPackagePath() + "model/";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportControllerPackageName(boolean generic) {
		return generic ? Application.getSpringProperties().getPackageName() + ".controller.generic" : Application.getSpringProperties().getPackageName() + ".controller";
	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportServicePackageName(boolean generic) {
		return generic ? Application.getSpringProperties().getPackageName() + ".service.generic" : Application.getSpringProperties().getPackageName() + ".service";

	}

	/**
	 * @param generic
	 *                true - returns generic package name
	 */
	public static String getImportRepositoryPackageName(boolean generic) {
		return generic ? Application.getSpringProperties().getPackageName() + ".repository.generic" : Application.getSpringProperties().getPackageName() + ".repository";
	}

	public static String getImportModelPackageName() {
		return Application.getSpringProperties().getPackageName() + ".model";
	}
}
