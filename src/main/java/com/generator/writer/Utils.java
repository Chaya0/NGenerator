package com.generator.writer;

import java.io.File;

import com.generator.Application;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GFolder;
import com.generator.writer.utils.GeneratorOutputFile;

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
		stringBuilder.append(Application.getSpringProperties().getBaseDir());
		stringBuilder.append("/src/main/java/");
		stringBuilder.append(getCorrectPath(Application.getSpringProperties().getPackageName()));
		return stringBuilder.toString();
	}

	public static String getImportDefaultPackage() {
		return Application.getSpringProperties().getPackageName();
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getControllerPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/controller/generic" : getMainPackagePath() + "/controller";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getRepositoryPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/repository/generic" : getMainPackagePath() + "/repository";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getServicePackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/service/generic" : getMainPackagePath() + "/service";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getControllerPackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/controller/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/controller/" + entityName.toLowerCase();
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getRepositoryPackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/repository/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/repository/" + entityName.toLowerCase();
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getServicePackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/service/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/service/" + entityName.toLowerCase();
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

	public static String getResourcesPath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getBaseDir() + "");
		stringBuilder.append("/src/main/resources/");
		return stringBuilder.toString();
	}

	public static String getConfigPackagePath() {
		return getMainPackagePath() + "/config";
	}

	public static String getExceptionsPackagePath() {
		return getMainPackagePath() + "/exceptions";
	}

	public static String getDTOPackagePath() {
		return getMainPackagePath() + "/dto";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportControllerPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".controller.generic" : getImportDefaultPackage() + ".controller";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportServicePackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".service.generic" : getImportDefaultPackage() + ".service";

	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportRepositoryPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".repository.generic" : getImportDefaultPackage() + ".repository";
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportControllerPackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".controller.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".controller." + entityName.toLowerCase();
	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportServicePackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".service.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".service." + entityName.toLowerCase();

	}

	/**
	 * @param generic true - returns generic package name
	 */
	public static String getImportRepositoryPackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".repository.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".repository." + entityName.toLowerCase();
	}

	public static String getImportModelPackageName() {
		return getImportDefaultPackage() + ".model";
	}

	public static String getImportExceptionsPackageName() {
		return getImportDefaultPackage() + ".exceptions";
	}

	public static String getApplicationExceptionImport() {
		return "import " + getImportExceptionsPackageName() + "." + getApplicationExceptionName() + ";";
	}

	public static String getImportModelEnumsPackageName() {
		return getImportModelPackageName() + ".enums";
	}

	public static String getApplicationExceptionName() {
		return StringUtils.uppercaseFirst(Application.getSpringProperties().getName()) + "Exception";
	}
	public static String getFrontendSourceDirectoryPath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getGeneratorProperties().getFrontendProjectPath());
		stringBuilder.append(Application.getSpringProperties().getName() + "-frontend");
		stringBuilder.append("/src/");
		return stringBuilder.toString();
	}

	public static String getFrontendRootPackagePath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getGeneratorProperties().getFrontendProjectPath());
		stringBuilder.append(Application.getSpringProperties().getName() + "-frontend");
		stringBuilder.append("/src/app/");
		return stringBuilder.toString();
	}
	public static String getFrontendEnvironmentPath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getGeneratorProperties().getFrontendProjectPath());
		stringBuilder.append(Application.getSpringProperties().getName() + "-frontend");
		stringBuilder.append("/src/environments/");
		return stringBuilder.toString();
	}

	public static String getFrontendFeaturesEntitiesPath() {
		return getFrontendRootPackagePath() + "features/entities/";
	}

	public static String getFrontendPagesPath() {
		return getFrontendRootPackagePath() + "pages/";
	}

	public static String getFrontendLocalizationPath() {
		return getFrontendSourceDirectoryPath() + "assets/i18n/";
	}
}
