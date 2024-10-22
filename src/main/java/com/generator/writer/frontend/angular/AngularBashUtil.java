package com.generator.writer.frontend.angular;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.generator.util.StringUtils;
import com.generator.writer.frontend.angular.types.AngularDependencies;
import com.generator.writer.utils.GFolder;

public class AngularBashUtil {

	public static void createComponent(String path, String componentName) {
		String command = AngularBashCommand.GENERATE_COMPONENT.getCommand() + StringUtils.camelToKebabCase(componentName);
		executeCommand(path, command);
	}

	public static void createGuard(String path, String guardName) {
		String command = AngularBashCommand.GENERATE_GUARD.getCommand() + StringUtils.camelToKebabCase(guardName);
		executeCommand(path, command);
	}

	public static void createPipe(String path, String pipeName) {
		String command = AngularBashCommand.GENERATE_PIPE.getCommand() + StringUtils.camelToKebabCase(pipeName);
		executeCommand(path, command);
	}

	public static void createClass(String path, String className) {
		String command = AngularBashCommand.GENERATE_CLASS.getCommand() + StringUtils.camelToKebabCase(className);
		executeCommand(path, command);
	}

	public static void createConfig(String path, String configName) {
		String command = AngularBashCommand.GENERATE_CONFIG.getCommand() + StringUtils.camelToKebabCase(configName);
		executeCommand(path, command);
	}

	public static void createEnum(String path, String enumName) {
		String command = AngularBashCommand.GENERATE_ENUM.getCommand() + StringUtils.camelToKebabCase(enumName);
		executeCommand(path, command);
	}

	public static void createModule(String path, String moduleName) {
		String command = AngularBashCommand.GENERATE_MODULE.getCommand() + StringUtils.camelToKebabCase(moduleName);
		executeCommand(path, command);
	}

	public static void createNewAngularApp(String path, String appName) throws Exception {
		String command = AngularBashCommand.NEW_APP.getCommand() + StringUtils.camelToKebabCase(appName) + "-frontend --routing --style=scss --skip-install";
		executeCommand(path, command);
	}

	public static void createNewAngularApp(String path, String appName, String angularVersion) throws Exception {
		// Check if the desired Angular version is already installed
		if (angularVersion != null && !angularVersion.isEmpty() && !isAngularVersionInstalled(path, angularVersion)) {
			// Install the specified version of Angular CLI if it does not exist
			String installAngularCommand = "npm install -g @angular/cli@" + angularVersion;
			executeCommand(path, installAngularCommand);
		} else {
			System.out.println("Angular CLI version " + angularVersion + " is already installed. Skipping installation.");
		}

		// Create the Angular app
		String command = AngularBashCommand.NEW_APP.getCommand() + StringUtils.camelToKebabCase(appName) + "-frontend --routing --style=scss --skip-install";
		executeCommand(path, command);
		installModules(path + "/" + StringUtils.camelToKebabCase(appName) + "-frontend");
		System.out.println("All moduels installed successfully!");
	}

	private static void installModules(String path) {
		for (AngularDependencies dependency : AngularDependencies.values()) {
			String command = "npm install " + dependency.getPackageName();
			executeCommand(path, command);
		}
	}

	// Method to check if a specific version of Angular CLI is installed
	private static boolean isAngularVersionInstalled(String path, String version) {
		try {
			File folder = new File(path);
			// Run "ng version" command to check installed Angular CLI versions
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.directory(folder);
			processBuilder.command("cmd.exe", "/c", "ng version");
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			boolean foundCli = false;
			while ((line = reader.readLine()) != null) {
				if (line.contains("Angular CLI")) {
					foundCli = true;
					String installedVersion = line.replaceAll("[^0-9.]", "").trim();
					if (installedVersion.startsWith(version)) {
						return true; // The required version is already installed
					}
				}
			}
			process.waitFor();
			if (!foundCli) {
				System.out.println("Angular CLI version not found in output.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; // Angular CLI version not found
	}

	private static void executeCommand(String location, String command) {
		System.out.println("Executing command: " + command + " at location: " + location);
		ProcessBuilder processBuilder = new ProcessBuilder();
		File folder = new File(location);
		if (folder.exists()) {
			GFolder fullFolderPath = new GFolder(location);
			if (!fullFolderPath.create()) {
				System.out.println("Created folder successfully : " + folder.getName());
			}
//			else{
//				System.out.println("Folder aleardy exsists: " + folder.getName() );
//			}
		}
		processBuilder.directory(folder);
		processBuilder.command("cmd.exe", "/c", command);
		try {
			Process process = processBuilder.start();
			int exitCode = process.waitFor();
			if (exitCode == 0) {
				System.out.println("Command executed successfully successfully!");
			} else {
				System.out.println("Failed to execute command. Exit code: " + exitCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
