package es.uniovi.eii.ds.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class Document {
	// Represents the document of the editor.
	StringBuilder text = new StringBuilder();
	
	public void open(String[] args) {
		if (!checkArguments(args, 1, "open <file>"))
			return;
		try {
			String filename = args[0];
			text = new StringBuilder(readFile(filename));
		} catch (Exception e) {
			System.out.println("Document could not be opened");
		}
	}
	
	public void insert(String[] args) {
		for (String word : args) {
			text.append(" ").append(word);
		}
	}
	
	public void delete() {
		int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
		if (indexOfLastWord == -1)
			text = new StringBuilder("");
		else
			text.setLength(indexOfLastWord);
	}
	
	public void replace(String[] args) {
		if (!checkArguments(args, 2, "replace <find> <replace>"))
			return;
		String find = args[0];
		String replace = args[1];
		text = new StringBuilder(text.toString().replace(find, replace));
	}
	
	public void showText() {
		System.out.println(text);
	}
	
	
	// ----------- $ - Auxiliary methods -------------------------------
	
	private boolean checkArguments(String[] args, int expected, String syntax) {
		if (args.length != expected) {
			System.out.println("Invalid number of arguments => " + syntax);
			return false;
		}
		return true;
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
}
