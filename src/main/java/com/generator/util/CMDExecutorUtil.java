package com.generator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMDExecutorUtil {

	private static void executeCommand(String command) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
			Process process = processBuilder.start();

			// Read the output of the command
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for the command to complete
			int exitCode = process.waitFor();
			System.out.println("Command exited with code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
