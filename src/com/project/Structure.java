package com.project;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class Structure {
	
	public static List<Path> getStructure(Path path, int depth) throws IOException {
		try (var stream = Files.walk(path, depth)) {
			return stream.toList();
		}
	}

	public static boolean checkDirectory(Path path) { // Returns success
		if (!Files.exists(path)) {
			System.err.println(path.toAbsolutePath() + " Doesn\'t exist!");
			return false;
		} else if (!Files.isDirectory(path)) {
			System.err.println(path.toAbsolutePath() + " Isn\'t a directory!");
			return false;
		} else if (!Files.isReadable(path)) {
			System.err.println(path.toAbsolutePath() + " Isn\'t readable!");
			return false;
		}

		return true;
	}

	public static int getDirectoriesCount(Path start, Path end) {
		return start.relativize(end).getNameCount();
	}

	public static String getStringFromStructure(List<Path> paths) {
		StringBuilder output = new StringBuilder();

		output.append(Data.Console.masterPrefix + paths.get(0).toAbsolutePath() + Data.Console.newLine); // Output master
		output.append(Data.Console.blockString + Data.Console.newLine);

		// Output files / directories in the target directory
		for (int i = 1; i < paths.size(); i++) { // 1 - Skip master
			String filename = paths.get(i).getFileName().toString();                                             // Get filename
			int    length   = getDirectoriesCount(paths.get(0), paths.get(i));                                   // Get directories count starting from the master to currect directory
			String prefix   = Data.Console.tabPrefix + Data.Console.tab.repeat(length) + Data.Console.tabSuffix; // Creating prefix

			// Check if it is a directory and add directory's suffix to filename
			if (Files.isDirectory(paths.get(i)))
				filename += Data.Console.directorySuffix;

			output.append(prefix + filename + Data.Console.newLine);
		}

		output.append(Data.Console.blockString);

		return output.toString();
	}

}