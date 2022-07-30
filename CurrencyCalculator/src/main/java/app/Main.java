package app;

import java.awt.EventQueue;

import parser.XMLParser;
import ui.UserInterface;

public class Main {

	public static void main(String[] args) {
		XMLParser parser = new XMLParser();
		
		parser.parse();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserInterface(parser);
			}
		});
	}

}
