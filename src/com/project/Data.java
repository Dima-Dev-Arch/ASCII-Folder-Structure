package com.project;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Data {

	public static class Console {
		public static String masterPrefix    = " ";
		public static String blockString     = "|--------------------|";
		public static String directorySuffix = "/";
		public static String tab             = "--";
		public static String tabSuffix       = " ";
		public static String tabPrefix       = "|";
		public static String newLine         = "\n";
	}

	public static class Defaults {
		public static final String targetDirectory = System.getProperty("user.dir");
		public static final String outputFilename  = "output.txt";
	}

	public static class Shortcuts {
		public static final String argumentTargetDirectory     = "_current_";
		public static final String argumentOutputFile          = "_nofile_";
	}

	public static class File {
		public static boolean ready     = true;
		public static Path    path      = Paths.get(Defaults.outputFilename);
	}
	
}