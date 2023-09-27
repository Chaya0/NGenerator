package com.generator.util;

import java.io.File;

public class Utils {
	public static boolean fileExists(String folder, String filename) {
		String fullPath = folder + "/" + filename;

		File file = new File(fullPath);
		return file.exists();
	}

//	public static BuilderOutputFile getOutputResource(String folder, String filename, String charset, boolean overwrite) throws Exception {
//		folder = getCorrectPath(folder);
//		String fullPath = folder + "/" + filename;
//
//		GFolder fullFolderPath = new GFolder(folder);
//		if (!fullFolderPath.create())
//			throw new Exception("Could not create folder " + folder);
//
//		return new BuilderOutputFile(fullPath, charset, overwrite);
//	}

//	public static BuilderOutputFile getOutputResource(String folder, String filename) throws Exception {
//		return getOutputResource(folder, filename, "UTF-8", true);
//	}
//
//	public static BuilderOutputFile getOutputResource(String folder, String filename, boolean overwrite) throws Exception {
//		return getOutputResource(folder, filename, "UTF-8", overwrite);
//	}

//	public static BuilderOutputFile createGenericOutputResource(int type, String baseFolder, String prefix, String filename, String suffix, String extension, boolean createJunction) throws Exception {
//		BuilderOutputFile file;
//
//		file = Utils.getOutputResource(baseFolder + "/specific", prefix + filename + suffix + "." + extension, false);
//		file.close();
//
//		file = Utils.getOutputResource(baseFolder + "/generic", prefix + filename + suffix + "." + extension, true);
//		return file;
//	}

//	public static BuilderOutputFile createGenericOutputResource(int type, String baseFolder, String filename, String suffix, String extension, boolean createJunction) throws Exception {
//		return createGenericOutputResource(type, baseFolder, "", filename, suffix, extension, createJunction);
//	}

	public static String getCorrectPath(String pathWithDots) {
		return pathWithDots.replace(".", "/");
	}

	public static String normalizeName(String name) {
		String s = name;
		if (s.startsWith("npl_")) {
			s = s.substring(4);
		}
		if (s.startsWith("ct_")) {
			s = s.substring(3);
		}

		while (s.indexOf("_") >= 0) {
			int index = s.indexOf("_");
			s = s.substring(0, index) + s.substring(index + 1, index + 2).toUpperCase() + s.substring(index + 2);
		}
		return s;
	}
}
