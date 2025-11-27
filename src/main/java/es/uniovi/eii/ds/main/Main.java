package es.uniovi.eii.ds.main;

import java.io.*;
import java.util.Arrays;

import es.uniovi.eii.ds.core.Document;
import es.uniovi.eii.ds.core.UI;

public class Main {
    public record UserCommand(String name, String[] args) {}

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
}
