package com.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ErrorLogHandler {
	public static void writeErrorLog(Exception exception) {
		try {
			File file = new File("./ErrorLog.txt");
			if (!file.exists()) file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			writer.write(exception.getMessage());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
