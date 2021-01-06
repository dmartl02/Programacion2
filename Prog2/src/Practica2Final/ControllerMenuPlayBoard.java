package Practica2Final;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuPlayBoard implements ActionListener{
	private ViewMenuPlayBoard viewPlayBoardMenu;

	public ControllerMenuPlayBoard() { 
		this.viewPlayBoardMenu = new ViewMenuPlayBoard(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("buttonAccept")) {
			int filas = Integer.parseInt(viewPlayBoardMenu.getTextFieldRows().getText());
			int columnas = Integer.parseInt(viewPlayBoardMenu.getTextFieldColumns().getText());
			//new ControllerBoardGame(filas, columnas);
		}
	}
}