package com.generator.writer.utils;

import java.io.File;

import com.generator.Application;
import com.generator.util.StringUtils;

/**
 * Utility class for various operations related to file handling and package
 * path generation in the code generator.
 */
public class WriterUtils {

	/**
	 * Checks if a file exists at the specified folder and filename.
	 *
	 * @param folder   The folder path where the file is expected to be located.
	 * @param filename The name of the file to check for existence.
	 * @return true if the file exists, false otherwise.
	 */
	public static boolean fileExists(String folder, String filename) {
		String fullPath = folder + "/" + filename;
		File file = new File(fullPath);
		return file.exists();
	}

	/**
	 * Retrieves a {@link GeneratorOutputFile} instance for the specified folder and
	 * filename with the given charset and overwrite flag.
	 * Creates the folder if it does not exist.
	 *
	 * @param folder    The folder where the file will be created.
	 * @param filename  The name of the file to create.
	 * @param charset   The character encoding to use for the file.
	 * @param overwrite Whether to overwrite the file if it already exists.
	 * @return A {@link GeneratorOutputFile} instance for the specified file.
	 * @throws Exception if the folder cannot be created or any other error occurs.
	 */
	public static GeneratorOutputFile getOutputResource(String folder, String filename, String charset, boolean overwrite) throws Exception {
		folder = getCorrectPath(folder);
		String fullPath = folder + "/" + filename;
		GFolder fullFolderPath = new GFolder(folder);
		if (!fullFolderPath.create()) throw new Exception("Could not create folder " + folder);

		return new GeneratorOutputFile(fullPath, charset, overwrite);
	}

	/**
	 * Retrieves a {@link GeneratorOutputFile} instance for the specified folder and
	 * filename with default charset "UTF-8" and overwrite flag set to true.
	 *
	 * @param folder    The folder where the file will be created.
	 * @param filename  The name of the file to create.
	 * @param overwrite Whether to overwrite the file if it already exists.
	 * @return A {@link GeneratorOutputFile} instance for the specified file.
	 * @throws Exception if any error occurs.
	 */
	public static GeneratorOutputFile getOutputResource(String folder, String filename, boolean overwrite) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", overwrite);
	}

	/**
	 * Retrieves a {@link GeneratorOutputFile} instance for the specified folder and
	 * filename with default charset "UTF-8" and overwrite flag set to true.
	 *
	 * @param folder   The folder where the file will be created.
	 * @param filename The name of the file to create.
	 * @return A {@link GeneratorOutputFile} instance for the specified file.
	 * @throws Exception if any error occurs.
	 */
	public static GeneratorOutputFile getOutputResource(String folder, String filename) throws Exception {
		return getOutputResource(folder, filename, "UTF-8", true);
	}

	/**
	 * Converts a path with dots to a standard path with slashes.
	 *
	 * @param pathWithDots The path string containing dots to be converted.
	 * @return The converted path with slashes.
	 */
	public static String getCorrectPath(String pathWithDots) {
		return pathWithDots.replace(".", "/");
	}

	/**
	 * Constructs the main package path for the Java source files.
	 *
	 * @return The main package path as a string.
	 */
	public static String getMainPackagePath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getBaseDir());
		stringBuilder.append("/src/main/java/");
		stringBuilder.append(getCorrectPath(Application.getSpringProperties().getPackageName()));
		return stringBuilder.toString();
	}

	/**
	 * Retrieves the default package name for imports.
	 *
	 * @return The default package name for imports.
	 */
	public static String getImportDefaultPackage() {
		return Application.getSpringProperties().getPackageName();
	}

	/**
	 * Constructs the path for the controller package based on whether a generic
	 * path is required.
	 *
	 * @param generic true if a generic package path is required, false otherwise.
	 * @return The path for the controller package.
	 */
	public static String getControllerPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/controller/generic" : getMainPackagePath() + "/controller";
	}

	/**
	 * Constructs the path for the repository package based on whether a generic
	 * path is required.
	 *
	 * @param generic true if a generic package path is required, false otherwise.
	 * @return The path for the repository package.
	 */
	public static String getRepositoryPackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/repository/generic" : getMainPackagePath() + "/repository";
	}

	/**
	 * Constructs the path for the service package based on whether a generic path
	 * is required.
	 *
	 * @param generic true if a generic package path is required, false otherwise.
	 * @return The path for the service package.
	 */
	public static String getServicePackagePath(boolean generic) {
		return generic ? getMainPackagePath() + "/service/generic" : getMainPackagePath() + "/service";
	}

	/**
	 * Constructs the path for the controller package based on whether a generic
	 * path is required and the entity name.
	 *
	 * @param generic    true if a generic package path is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The path for the controller package.
	 */
	public static String getControllerPackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/controller/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/controller/" + entityName.toLowerCase();
	}

	/**
	 * Constructs the path for the repository package based on whether a generic
	 * path is required and the entity name.
	 *
	 * @param generic    true if a generic package path is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The path for the repository package.
	 */
	public static String getRepositoryPackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/repository/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/repository/" + entityName.toLowerCase();
	}

	/**
	 * Constructs the path for the service package based on whether a generic path
	 * is required and the entity name.
	 *
	 * @param generic    true if a generic package path is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The path for the service package.
	 */
	public static String getServicePackagePath(boolean generic, String entityName) {
		return generic ? getMainPackagePath() + "/service/generic/" + entityName.toLowerCase() : getMainPackagePath() + "/service/" + entityName.toLowerCase();
	}

	/**
	 * Constructs the path for the model package.
	 *
	 * @return The path for the model package.
	 */
	public static String getModelPackagePath() {
		return getMainPackagePath() + "/model";
	}

	/**
	 * Constructs the path for the model enums package.
	 *
	 * @return The path for the model enums package.
	 */
	public static String getModelEnumsPackagePath() {
		return getModelPackagePath() + "/enums";
	}

	/**
	 * Constructs the path for the utils package.
	 *
	 * @return The path for the utils package.
	 */
	public static String getUtilsPackagePath() {
		return getMainPackagePath() + "/utils";
	}

	/**
	 * Constructs the path for the resources directory.
	 *
	 * @return The path for the resources directory.
	 */
	public static String getResourcesPath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getBaseDir() + "");
		stringBuilder.append("/src/main/resources/");
		return stringBuilder.toString();
	}

	/**
	 * Constructs the path for the configuration package.
	 *
	 * @return The path for the configuration package.
	 */
	public static String getConfigPackagePath() {
		return getMainPackagePath() + "/config";
	}

	/**
	 * Constructs the path for the exceptions package.
	 *
	 * @return The path for the exceptions package.
	 */
	public static String getExceptionsPackagePath() {
		return getMainPackagePath() + "/exceptions";
	}

	/**
	 * Constructs the path for the DTO package.
	 *
	 * @return The path for the DTO package.
	 */
	public static String getDTOPackagePath() {
		return getMainPackagePath() + "/dto";
	}

	/**
	 * Retrieves the import package name for the controller based on whether a
	 * generic package name is required.
	 *
	 * @param generic true if a generic package name is required, false otherwise.
	 * @return The import package name for the controller.
	 */
	public static String getImportControllerPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".controller.generic" : getImportDefaultPackage() + ".controller";
	}

	/**
	 * Retrieves the import package name for the service based on whether a generic
	 * package name is required.
	 *
	 * @param generic true if a generic package name is required, false otherwise.
	 * @return The import package name for the service.
	 */
	public static String getImportServicePackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".service.generic" : getImportDefaultPackage() + ".service";
	}

	/**
	 * Retrieves the import package name for the repository based on whether a
	 * generic package name is required.
	 *
	 * @param generic true if a generic package name is required, false otherwise.
	 * @return The import package name for the repository.
	 */
	public static String getImportRepositoryPackageName(boolean generic) {
		return generic ? getImportDefaultPackage() + ".repository.generic" : getImportDefaultPackage() + ".repository";
	}

	/**
	 * Retrieves the import package name for the controller based on whether a
	 * generic package name is required and the entity name.
	 *
	 * @param generic    true if a generic package name is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The import package name for the controller.
	 */
	public static String getImportControllerPackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".controller.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".controller." + entityName.toLowerCase();
	}

	/**
	 * Retrieves the import package name for the service based on whether a generic
	 * package name is required and the entity name.
	 *
	 * @param generic    true if a generic package name is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The import package name for the service.
	 */
	public static String getImportServicePackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".service.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".service." + entityName.toLowerCase();
	}

	/**
	 * Retrieves the import package name for the repository based on whether a
	 * generic package name is required and the entity name.
	 *
	 * @param generic    true if a generic package name is required, false
	 *                   otherwise.
	 * @param entityName The name of the entity.
	 * @return The import package name for the repository.
	 */
	public static String getImportRepositoryPackageName(boolean generic, String entityName) {
		return generic ? getImportDefaultPackage() + ".repository.generic." + entityName.toLowerCase() : getImportDefaultPackage() + ".repository." + entityName.toLowerCase();
	}

	/**
	 * Retrieves the import package name for the model.
	 *
	 * @return The import package name for the model.
	 */
	public static String getImportModelPackageName() {
		return getImportDefaultPackage() + ".model";
	}

	/**
	 * Retrieves the import package name for the exceptions.
	 *
	 * @return The import package name for the exceptions.
	 */
	public static String getImportExceptionsPackageName() {
		return getImportDefaultPackage() + ".exceptions";
	}

	/**
	 * Retrieves the import statement for the application exception class.
	 *
	 * @return The import statement for the application exception class.
	 */
	public static String getApplicationExceptionImport() {
		return "import " + getImportExceptionsPackageName() + "." + getApplicationExceptionName() + ";";
	}

	/**
	 * Retrieves the import package name for the model enums.
	 *
	 * @return The import package name for the model enums.
	 */
	public static String getImportModelEnumsPackageName() {
		return getImportModelPackageName() + ".enums";
	}

	/**
	 * Constructs the name of the application exception class based on the
	 * application name.
	 *
	 * @return The name of the application exception class.
	 */
	public static String getApplicationExceptionName() {
		return StringUtils.uppercaseFirst(Application.getSpringProperties().getName()) + "Exception";
	}

	/**
	 * Constructs the root package path for the frontend application.
	 *
	 * @return The root package path for the frontend application.
	 */
	public static String getFrontendRootPackagePath() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Application.getSpringProperties().getProjectPath());
		stringBuilder.append(Application.getSpringProperties().getBaseDir() + "-frontend");
		stringBuilder.append("/src/app/");
		return stringBuilder.toString();
	}

	/**
	 * Constructs the path for the frontend features entities.
	 *
	 * @return The path for the frontend features entities.
	 */
	public static String getFrontendFeaturesEntitiesPath() {
		return getFrontendRootPackagePath() + "features/entities/";
	}

	/**
	 * Constructs the path for the frontend pages.
	 *
	 * @return The path for the frontend pages.
	 */
	public static String getFrontendPagesPath() {
		return getFrontendRootPackagePath() + "pages/";
	}
	
	public static String getSpecificationPackagePath() {
		return getMainPackagePath() + "/specification";
	}
	
	public static String getSpecificationPackageImportPath() {
		return getImportDefaultPackage() + ".specification";
	}
	
	public static String getUtilsPackageImportPath() {
		return getImportDefaultPackage() + ".utils";
	}
	
	public static String getExceptionsPackageImportPath() {
		return getImportDefaultPackage() + ".exceptions";
	}
	
}
