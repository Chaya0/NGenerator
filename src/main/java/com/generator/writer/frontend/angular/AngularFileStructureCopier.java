package com.generator.writer.frontend.angular;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AngularFileStructureCopier {
    private final String sourceDir;
    private final String destinationDir;

    public AngularFileStructureCopier(String sourceDir, String destinationDir) {
        this.sourceDir = sourceDir;
        this.destinationDir = destinationDir;
    }

    public void copyAngularFiles() throws IOException {
        File sourceFolder = new File(sourceDir);
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            throw new IllegalArgumentException("Source directory does not exist or is not a directory.");
        }
        processDirectory(sourceFolder, destinationDir);
    }

    protected void processDirectory(File sourceFolder, String currentDestDir) throws IOException {
        File[] files = sourceFolder.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                String newDestDir = currentDestDir + "/" + file.getName();
                processDirectory(file, newDestDir);
            } else if (isAngularFile(file)) {
                Path destinationPath = Paths.get(currentDestDir);
                if (!Files.exists(destinationPath)) {
                    Files.createDirectories(destinationPath);
                }
                Path destFile = destinationPath.resolve(file.getName());
                Files.copy(file.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private boolean isAngularFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".ts") || fileName.endsWith(".html") || fileName.endsWith(".scss") || fileName.endsWith(".css");
    }
}