package com.generator.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
	public static File[] getSubFiles(Class<?> clazz) {
		try {
			// Get the package name of the class and convert it to a path
			String classPackagePath = clazz.getPackage().getName().replace('.', '/');

			// Get the URL for the package directory
			URL packageURL = clazz.getClassLoader().getResource(classPackagePath + "/templates");

			if (packageURL != null) {
				// Convert URL to a File object
				File directory = Paths.get(packageURL.toURI()).toFile();

				// Return the list of files (or null if none exist)
				System.out.println(directory.getAbsolutePath());
				return directory.listFiles();
			} else {
				System.out.println("Package directory not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new File[0]; // Return an empty array if an error occurs or no files found
	}

	
	public static File getTemplateFolder(Class<?> clazz) {
		try {
			// Get the package name of the class and convert it to a path
			String classPackagePath = clazz.getPackage().getName().replace('.', '/');

			// Get the URL for the package directory
			URL packageURL = clazz.getClassLoader().getResource(classPackagePath + "/templates");

			if (packageURL != null) {
				// Convert URL to a File object
				File directory = Paths.get(packageURL.toURI()).toFile();
				File file =  new File(directory.getAbsolutePath().replace("target\\classes", "src\\main\\java"));
				return file;
			} else {
				System.out.println("Package directory not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Copies the content from a source file to a target file.
	 * If the target file already exists, it will be overwritten.
	 *
	 * @param sourceFile the file to copy from
	 * @param targetFile the file to copy to
	 * @throws IOException if there is an error during file operations
	 */
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		// Ensure the target directory exists
		File targetDir = targetFile.getParentFile();
		if (!targetDir.exists()) {
			targetDir.mkdirs(); // Create directories if they don't exist
		}

		Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	public static File findFileInFolderByFileName(File folder, String fileName) throws IOException {
		// Check if the folder is a directory and exists
		if (folder != null && folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				// Ensure file is not null before checking its name
				if (file != null && file.getName().equals(fileName)) {
					return file; // Return the existing file if it matches
				}
			}
		}

		// If the file is not found, create a new one
		File newFile = new File(folder, fileName);
		if (newFile.createNewFile()) {
			// The file was created successfully
			return newFile;
		} else {
			// The file already exists or could not be created
			throw new IOException("Could not create file: " + newFile.getAbsolutePath());
		}
	}
}
