package es.uniovi.eii.ds.main;

import es.uniovi.eii.ds.core.Editor;
import es.uniovi.eii.ds.core.UI;

public class Main {
    public record UserCommand(String name, String[] args) {}
    private Editor editor = new Editor();

    public static void main(String[] args) {
        new Main().run();
    }
	
    public void run() {
		UI.drawLogo();
		UI.showHelp();

		while (true) {
			UserCommand command = UI.promptUser();
			String[] args = command.args;

			switch (command.name) {
				case "open" -> editor.open(args);
				case "insert" -> editor.insert(args);
				case "delete" -> editor.delete();
				case "replace" -> editor.replace(args);
				case "help" -> UI.showHelp();
				case "record" -> editor.recordMacro(args[0]);
				case "stop" -> editor.stopMacro();
				case "execute" -> editor.executeMacro(args[0]);
				default -> {
					System.out.println("Unknown command");
					continue;
				}
			}
			editor.showText();
		}
	}
}
