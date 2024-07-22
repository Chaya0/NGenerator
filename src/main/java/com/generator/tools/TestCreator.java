package com.generator.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class TestCreator {
	public static Map<String, String> scanClasses() throws IOException {
		Map<String, String> classMap = new HashMap<>();
		List<File> classpathEntries = getClasspathEntries();

		for (File entry : classpathEntries) {
			scanDirectory(entry, classMap);
		}

		return classMap;
	}

	private static List<File> getClasspathEntries() throws IOException {
		List<File> classpathEntries = new ArrayList<>();
		String classpath = System.getProperty("java.class.path");
		String[] classpathElements = classpath.split(File.pathSeparator);

		for (String element : classpathElements) {
			File file = new File(element);
			if (file.exists()) {
				classpathEntries.add(file);
			}
		}

		return classpathEntries;
	}

	private static void scanDirectory(File directory, Map<String, String> classMap) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						scanDirectory(file, classMap);
					} else if (file.getName().endsWith(".class")) {
						String className = getClassName(directory, file);
						System.out.println(file.getParent());
						String classPath = file.getParent().replace("target\\classes", "src\\test\\java");;
						classMap.put(className, classPath);
					}
				}
			}
		}
	}

	private static String getClassName(File baseDir, File classFile) {
		String relativePath = classFile.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
		String className = relativePath.replace(File.separatorChar, '.').replace(".class", "");
		return className;
	}

	public static void main(String[] args) {
		try {
			Map<String, String> classes = scanClasses();

			classes.forEach((name, path) -> {
//				System.out.println("Class: " + name + ", Path: " + path);
				try (GeneratorOutputFile file = WriterUtils.getOutputResource(path, name + "Test.java", true)) {
					if (file.hasAlreadyExisted()) {
						System.out.println(file);
						return;
					}
					System.out.println(path);
					System.out.println("Class: " + name + " generated successfuly on path: " + path);
					file.writeln(0, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
