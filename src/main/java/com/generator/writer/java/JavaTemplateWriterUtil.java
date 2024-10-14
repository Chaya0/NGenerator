package com.generator.writer.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.generator.validator.exceptions.UnknowImportTypeException;
import com.generator.validator.exceptions.UnknowPackageTypeException;
import com.generator.writer.java.imports.GeneratorTemplatePlaceholderType;
import com.generator.writer.java.imports.ImportType;
import com.generator.writer.java.imports.PackageType;

public class JavaTemplateWriterUtil {

	public static void processTemplateFiles(File folder, String path) throws Exception {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".template")) {
						try {
							processFile(file, path);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

//	public static void processFile(File file, String outputPath) throws Exception {
//		String outputFilePath = outputPath + File.separator + file.getName().replace(".template", ".java");
//		File javaFile = new File(outputFilePath);
//		try (BufferedReader reader = new BufferedReader(new FileReader(file));
//				BufferedWriter writer = new BufferedWriter(new FileWriter(javaFile))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				line = replacePlaceholders(line);
//				writer.write(line);
//				writer.newLine();
//			}
//		}
//	}
	
	public static void processFile(File file, String path) throws Exception {
        processFileInternal(file, path, null);
    }

    public static void processFile(File file, String path, String packagePath) throws Exception {
        processFileInternal(file, path, packagePath);
    }
	
	public static void processFileInternal(File file, String path, String packagePath) throws Exception {
	    // Log the provided path and file details for debugging
	    System.out.println("Output Path: " + path);
	    System.out.println("Template File: " + file.getAbsolutePath());
	    
	    // Prepare the output Java file path
	    String javaFilePath = path + "/" + file.getName().replace(".template", ".java");
	    File javaFile = new File(javaFilePath.replace("/", "\\"));

	    // Ensure the parent directories exist
	    Path parentDir = Paths.get(javaFile.getParent());
	    if (!Files.exists(parentDir)) {
	        Files.createDirectories(parentDir);  // Create all necessary parent directories
	    }

	    // Check if the file already exists before creating it
	    if (!javaFile.exists()) {
	        System.out.println("Creating new file: " + javaFile.getPath());
	        javaFile.createNewFile(); // Create the new file
	    }

	    System.out.println("Processing file to the location: " + javaFile);

	    try (BufferedReader reader = new BufferedReader(new FileReader(file));
	    		BufferedWriter writer = new BufferedWriter(new FileWriter(javaFile))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
                line = packagePath == null ? replacePlaceholders(line) : replacePlaceholders(line, packagePath);
	            writer.write(line);
	            writer.newLine();
	        }
	    }
	}


	private static String replacePlaceholders(String line) throws Exception {
		if (line.startsWith(GeneratorTemplatePlaceholderType.GI.getCode())) {
			return replaceImportPlaceholder(line);
		} else if (line.startsWith(GeneratorTemplatePlaceholderType.GP.getCode())) {
			return replacePackagePlaceholder(line);
		} else {
			return line;
		}
	}

	private static String replacePackagePlaceholder(String line) throws Exception {
		String packageName = line.replace(GeneratorTemplatePlaceholderType.GP.getCode(), "").replace("(", "").replace(")", "").trim();
		for (PackageType type : PackageType.values()) {
			if (type.toString().equalsIgnoreCase(packageName)) {
				return type.getPackageImport();
			}
		}
		throw new UnknowPackageTypeException(packageName);
	}
	
	private static String replacePlaceholders(String line, String packagePath) throws Exception {
		if (line.startsWith(GeneratorTemplatePlaceholderType.GI.getCode())) {
			return replaceImportPlaceholder(line);
		} else if (line.startsWith(GeneratorTemplatePlaceholderType.GP.getCode())) {
			return replacePackagePlaceholder(line, packagePath);
		} else {
			return line;
		}
	}

	private static String replacePackagePlaceholder(String line, String packagePath) throws Exception {
		String packageName = line.replace(GeneratorTemplatePlaceholderType.GP.getCode(), "").replace("(", "").replace(")", "").trim();
		for (PackageType type : PackageType.values()) {
			if (type.toString().equalsIgnoreCase(packageName)) {
				return type.getPackageImport(packagePath);
			}
		}
		throw new UnknowPackageTypeException(packageName);
	}

	private static String replaceImportPlaceholder(String line) throws Exception {
		String importName[] = line.replace(GeneratorTemplatePlaceholderType.GI.getCode(), "").replace("(", "").replace(")", "").trim().split(",");
		for (ImportType type : ImportType.values()) {
			if (type.toString().equalsIgnoreCase(importName[0])) {
				if (importName.length > 1) {
					return type.getImportCode(importName[1].replace("\"", "").trim());
				} else {
					return type.getFullImportCode();
				}
			}
		}
		throw new UnknowImportTypeException();
	}

}
