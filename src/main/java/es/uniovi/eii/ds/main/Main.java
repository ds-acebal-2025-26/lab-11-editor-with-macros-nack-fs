package es.uniovi.eii.ds.main;

import java.io.*;
import java.util.Arrays;

import es.uniovi.eii.ds.core.UI;

public class Main {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public record UserCommand(String name, String[] args) {}
	
	// Represents the document of the editor.
	StringBuilder text = new StringBuilder();

    public static void main(String[] args) {
        new Main().run();
    }
	
	// Main program loop.
    public void run() {
		UI.drawLogo();
		UI.showHelp();

		while (true) {
			UserCommand command = UI.promptUser();
			String[] args = command.args;

			switch (command.name) {
				case "open" -> open(args);
				case "insert" -> { 
					for (String word : args) {
						text.append(" ").append(word);
					}
				}
				case "delete" -> {
					int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
					if (indexOfLastWord == -1)
						text = new StringBuilder("");
					else
						text.setLength(indexOfLastWord);
				}
				case "replace" -> replace(args);
				case "help" -> UI.showHelp();
				case "record" -> {
					// String macroName = args[0];
					// ...
				}
				case "stop" -> { 
					// ...
				}
				case "execute" -> {
					// String macroName = args[0];
					// ...
				}
				default -> {
					System.out.println("Unknown command");
					continue;
				}
			}

			System.out.println(text);
		}
	}

	//$-- Some individual user commands that do a bit more work ---------------

	private void open(String[] args) {
		if (!checkArguments(args, 1, "open <file>"))
			return;
		try {
			String filename = args[0];
			text = new StringBuilder(readFile(filename));
		} catch (Exception e) {
			System.out.println("Document could not be opened");
		}
	}

	private String readFile(String filename) {
		InputStream in = getClass().getResourceAsStream("/" + filename);
		if (in == null)
			throw new IllegalArgumentException("File not found: " + filename);

		try (BufferedReader input = new BufferedReader(new InputStreamReader(in))) {
			StringBuilder result = new StringBuilder();
			String line;
			boolean firstLine = true;
			while ((line = input.readLine()) != null) {
				if (!firstLine)
					result.append(System.lineSeparator());
				result.append(line);
				firstLine = false;
			}
			return result.toString();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private void replace(String[] args) {
		if (!checkArguments(args, 2, "replace <find> <replace>"))
			return;
		String find = args[0];
		String replace = args[1];
		text = new StringBuilder(text.toString().replace(find, replace));
	}
	
	private boolean checkArguments(String[] args, int expected, String syntax) {
		if (args.length != expected) {
			System.out.println("Invalid number of arguments => " + syntax);
			return false;
		}
		return true;
	}
}
