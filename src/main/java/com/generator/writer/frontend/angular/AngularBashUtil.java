package com.generator.writer.frontend.angular;

import java.io.File;
import java.io.IOException;

import com.generator.util.StringUtils;
import com.generator.writer.GFolder;

public class AngularBashUtil {

	public static void createComponent(String path, String componentName) {
		String command = AngularBashCommand.GENERATE_COMPONENT.getCommand() + StringUtils.camelToKebabCase(componentName);
		executeCommand(path, command);
	}

	public static void createGuard(String path, String guardName) {
		String command = AngularBashCommand.GENERATE_GUARD.getCommand() + StringUtils.camelToKebabCase(guardName);
		executeCommand("", command);
	}

	public static void createPipe(String path, String pipeName) {
		String command = AngularBashCommand.GENERATE_PIPE.getCommand() + StringUtils.camelToKebabCase(pipeName);
		executeCommand("", command);
	}

	public static void createClass(String path, String className) {
		String command = AngularBashCommand.GENERATE_CLASS.getCommand() + StringUtils.camelToKebabCase(className);
		executeCommand("", command);
	}

	public static void createConfig(String path, String configName) {
		String command = AngularBashCommand.GENERATE_CONFIG.getCommand() + StringUtils.camelToKebabCase(configName);
		executeCommand("", command);
	}

	public static void createEnum(String path, String enumName) {
		String command = AngularBashCommand.GENERATE_ENUM.getCommand() + StringUtils.camelToKebabCase(enumName);
		executeCommand("", command);
	}

	public static void createModule(String path, String moduleName) {
		String command = AngularBashCommand.GENERATE_MODULE.getCommand() + StringUtils.camelToKebabCase(moduleName);
		executeCommand("", command);
	}

	public static void createNewAngularApp(String location, String appName) throws Exception {
		String command = AngularBashCommand.NEW_APP.getCommand() + StringUtils.camelToKebabCase(appName) + " --routing --style=css --skip-install";
	}
	
	private static void executeCommand(String location, String command)  {
		ProcessBuilder processBuilder = new ProcessBuilder();
		File folder = new File(location);
		if (folder.exists()) {
			GFolder fullFolderPath = new GFolder(location);
			if (!fullFolderPath.create()) {
				System.out.println();
			};
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
			// TODO handle the exception and write it in error log
		}

	}

}
