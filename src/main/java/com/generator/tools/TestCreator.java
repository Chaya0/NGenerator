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
	                    String classPath = file.getParent().replace("target\\classes", "src\\test\\java");
	                    if (isValidClass(className, classPath)) {
	                        classMap.put(className, classPath);
	                    }
	                }
	            }
	        }
	    }
	}

	private static boolean isValidClass(String className, String classPath) {
	    try {
	        System.out.println("Attempting to load class: " + getClassPackage(classPath) + "." +className); 
	        Class<?> clazz = Class.forName(getClassPackage(classPath) + "." +className);
	        return !clazz.isEnum() && !clazz.isAnnotation() && !clazz.isInterface();
	    } catch (ClassNotFoundException e) {
	        System.err.println("Class not found: " + className);  // Debugging line
	        e.printStackTrace();
	        return false;
	    }
	}

	private static String getClassName(File baseDir, File classFile) {
	    String relativePath = classFile.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
	    String className = relativePath.replace(File.separatorChar, '.').replace(".class", "");
	    return className;
	}
	
	private static String getClassPackage(String path) {
		String relativePath = path.substring(path.indexOf("src\\test\\java\\") + "src\\test\\java\\".length());
		return relativePath.replace("\\", ".");
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
					String relativePath = path.substring(path.indexOf("src\\test\\java\\") + "src\\test\\java\\".length());
					file.writeln(0, "package " + relativePath.replace("\\", ".") + ";");

					file.writeln(0, "");
					file.writeln(0, "import org.junit.jupiter.api.Test;");
					file.writeln(0, "import static org.junit.jupiter.api.Assertions.*;");
					file.writeln(0, "");
					file.writeln(0, "public class " + name + "Test {");
					file.writeln(0, "");
					file.writeln(1, "@Test");
					file.writeln(1, "void testPlaceholder() {");
					file.writeln(2, "fail(\"Not yet implemented\");");
					file.writeln(1, "}");
					file.writeln(0, "}");

				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
