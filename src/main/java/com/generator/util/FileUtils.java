package com.generator.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

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
				return directory.listFiles();
			} else {
				System.out.println("Package directory not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new File[0]; // Return an empty array if an error occurs or no files found
	}
}
