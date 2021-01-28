package Practica2Final_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	}
}
