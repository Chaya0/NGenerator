package com.generator.writer.frontend.angular;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AngularFileStructureCopier {

	public static void copyAngularFiles(String sourceDir, String destinationDir) throws IOException {
		copyAngularFiles(sourceDir, destinationDir, true);
	}
    public static void copyAngularFiles(String sourceDir, String destinationDir, boolean transformCharacters) throws IOException {
        File sourceFolder = new File(sourceDir);
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            throw new IllegalArgumentException("Source directory does not exist or is not a directory.");
        }
        processDirectory(sourceFolder, destinationDir, transformCharacters);
    }

    protected static void processDirectory(File sourceFolder, String currentDestDir, boolean transformCharacters) throws IOException {
    	
        File[] files = sourceFolder.listFiles();
        
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
            	String newDestDir = currentDestDir + "/" + file.getName();
            	if(transformCharacters) {
            		System.out.println("replacing for file " + newDestDir);
            		newDestDir = newDestDir.replace("_", "-");
            	}
                processDirectory(file, newDestDir, transformCharacters);
            } else if (isAngularFile(file)) {
                Path destinationPath = Paths.get(currentDestDir);
                if (!Files.exists(destinationPath)) {
                    Files.createDirectories(destinationPath);
                }
                Path destFile;
                if(transformCharacters) {
                	destFile = destinationPath.resolve(file.getName().replace("_", "-"));
                }else {
                	destFile = destinationPath.resolve(file.getName());
                }
                Files.copy(file.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private static boolean isAngularFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".ts") || fileName.endsWith(".html") || fileName.endsWith(".scss") || fileName.endsWith(".css");
    }
}