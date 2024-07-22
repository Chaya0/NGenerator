package com.generator.tools;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCreator {
	public static void cloneAndRename(String sourceDir, String targetDir) throws IOException {
		Path sourcePath = Paths.get(sourceDir);
		Path targetPath = Paths.get(targetDir);

		Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (file.toString().endsWith(".java")) {
					// Define the target file path
					Path relativePath = sourcePath.relativize(file);
					
					Path testFile = targetPath.resolve(relativePath.getParent()).resolve(getTestClassName(relativePath.getFileName().toString()));

					if (Files.exists(testFile)) {
						// Skip existing test files
						System.out.println("Test file already exists: " + testFile);
					} else {
						// Copy the file to the target directory
						Files.createDirectories(testFile.getParent());
						Files.copy(file, testFile, StandardCopyOption.REPLACE_EXISTING);

						// Rename the class inside the file to end with 'Test'
						renameClassInFile(testFile);

						// Add basic test methods to the test file
						addTestMethods(testFile);
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static String getTestClassName(String fileName) {
		if (fileName.endsWith(".java")) {
			return fileName.replace(".java", "Test.java");
		}
		return fileName + "Test.java";
	}

	private static void renameClassInFile(Path file) throws IOException {
		String content = new String(Files.readAllBytes(file));
		String newContent = content;

		// Find class declarations and rename them
		int classIndex = 0;
		while ((classIndex = newContent.indexOf("class ", classIndex)) != -1) {
			int classNameStart = classIndex + 6;
			int classNameEnd = newContent.indexOf(' ', classNameStart);
			if (classNameEnd == -1) {
				classNameEnd = newContent.indexOf('{', classNameStart);
			}
			if (classNameEnd == -1) break;

			String className = newContent.substring(classNameStart, classNameEnd).trim();
			if (!className.endsWith("Test")) {
				newContent = newContent.substring(0, classNameStart) + className + "Test" + newContent.substring(classNameEnd);
			}
			classIndex = classNameEnd;
		}

		Files.write(file, newContent.getBytes());
	}

	private static void addTestMethods(Path file) throws IOException {
		String content = new String(Files.readAllBytes(file));
		StringBuilder newContent = new StringBuilder(content);

		// Add basic test methods to the end of the file
		newContent.append("\n\n// Basic test methods\n");
		newContent.append("import org.junit.jupiter.api.Test;\n");
		newContent.append("import static org.junit.jupiter.api.Assertions.*;\n");

		// Find the class name for method naming
		Pattern classNamePattern = Pattern.compile("class (\\w+Test)");
		Matcher matcher = classNamePattern.matcher(content);
		if (matcher.find()) {

			// Add a sample test method
			newContent.append("\n@Test\n");
			newContent.append("public void sampleTest() {\n");
			newContent.append("    // TODO: Add your test logic here\n");
			newContent.append("    assertTrue(true);\n");
			newContent.append("}\n");
		}

		Files.write(file, newContent.toString().getBytes());
	}

	public static void main(String[] args) {
		try {
			cloneAndRename("src/main/java", "src/test/java");
			System.out.println("Cloning and renaming completed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
