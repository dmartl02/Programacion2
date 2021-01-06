package Practica2Final;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class ControllerInitialMenu implements ActionListener{
	
	public static void main(String[] args) {
		new ControllerInitialMenu();
	}
	
	public ControllerInitialMenu() {
		new ViewInitialMenu(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("createNewBoard")) {
			new ControllerMenuCreateBoard();
		}
		
		if (e.getActionCommand().equals("playExistingBoard")) {
			new ControllerMenuPlayBoard();
		}
	}
}
