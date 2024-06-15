package com.generator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImportOrganizerUtil {
	private static Logger logger = LogManager.getLogger(ImportOrganizerUtil.class);

	public static void organizeImports(String javaFile) {
		logger.info("Organizing imports for " + javaFile);
		try {
			// Command to execute the shell script
			String[] command = { "/bin/bash", "organize_imports.sh", javaFile };

			ProcessBuilder pb = new ProcessBuilder(command);

			// Redirect error stream to output stream
			pb.redirectErrorStream(true);

			Process process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				logger.info("Import organization completed successfully.");
			} else {
				logger.error("Import organization failed with exit code: " + exitCode);
			}

		} catch (IOException | InterruptedException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
