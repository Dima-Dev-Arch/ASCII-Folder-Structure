package com.project;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Main {

	static Path targetPath = Paths.get(Data.Defaults.targetDirectory);
	static List<Path> targetListOfFiles;

	public static void main(String[] args) {
		// Get arguments
		if (args.length != 3) {
			System.out.println(args.length + "Something went wrong with arguments! Enter <Target Directory (or " + Data.Shortcuts.argumentTargetDirectory + " to work with current directory)> <Output Filename (or " + Data.Shortcuts.argumentOutputFile + " to not saving the result to a file)> otherwise program will use defaults");
		}

		for (String arg : args) {
			// Get target directory
			if (arg.equalsIgnoreCase(Data.Shortcuts.argumentTargetDirectory))
				{targetPath = Paths.get(Data.Defaults.targetDirectory); continue;}
			else if (arg.equalsIgnoreCase(Data.Shortcuts.argumentOutputFile))
				{Data.File.ready = false; continue;}

			// Assing the directory to target, otherwise it is a file
			Path path = Paths.get(arg);
			if (Files.exists(path) && Files.isDirectory(path))
				targetPath = Paths.get(arg);
			else
				Data.File.path = Paths.get(arg);
		}

		// Check if the target is a directory & readable & exists
		if (!Structure.checkDirectory(targetPath))
			System.exit(1);

		// If the output file is already exists
		if (Files.exists(Data.File.path) && Data.File.ready) {
			System.out.println("Warning: " + Data.File.path.toAbsolutePath() + " already exists!");
			Data.File.ready = false;
		}

		// Check if entered Data.Shortcuts.argumentOutputFile shortcut
		if (Data.File.path.getFileName().toString().equalsIgnoreCase(Data.Shortcuts.argumentOutputFile)) {
			System.out.println("Warning: entered \"" + Data.Shortcuts.argumentOutputFile + "\" so file will not be created!");
			Data.File.ready = false;
		}

		System.out.println(String.format("Passed arguments: (%s, %s)", targetPath, Data.File.path.getFileName()));
		System.out.println("-".repeat(50)); // To separate the result

		// Get target structure
		try {
			targetListOfFiles = Structure.getStructure(targetPath, Integer.MAX_VALUE);
		} catch (IOException error) {
			System.err.println("Failed to get target streams! " + error.getMessage());
			System.exit(1);
		}

		// Output the result
		String outputResult = Structure.getStringFromStructure(targetListOfFiles);
		System.out.println(outputResult);

		try {
			if (Data.File.ready)
				Files.writeString(Data.File.path, outputResult);
		} catch (IOException error) {
			System.err.println("Something went wrong while writing the result to the output file!");
		}
	}

}