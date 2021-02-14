package Practica2Final;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ControllerMenuCreateBoard implements ActionListener{
	public JFrame frame = new JFrame();
	private final int MIN_ROWS = 10;
	private final int MAX_ROWS = 100;
	private final int MIN_COLUMNS = 10;
	private final int MAX_COLUMNS = 100;
	private final int MIN_TRAINS = 1;
	private ViewMenuCreateBoard viewCreateBoardMenu;

	public ControllerMenuCreateBoard() { 
		this.viewCreateBoardMenu = new ViewMenuCreateBoard(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("buttonAccept")) {
			int trains = Integer.parseInt(viewCreateBoardMenu.getTextFieldTrains().getText());
			int rows = Integer.parseInt(viewCreateBoardMenu.getTextFieldRows().getText());
			int columns = Integer.parseInt(viewCreateBoardMenu.getTextFieldColumns().getText());
			
			if((rows < MIN_ROWS) || (rows > MAX_ROWS) || (columns < MIN_COLUMNS) || (columns > MAX_COLUMNS)) {
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frame, "The number of rows and columns must be greater than 10 and smaller than 100", 
						"Warning", JOptionPane.INFORMATION_MESSAGE);
				
			} else if(trains < MIN_TRAINS) {
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frame, "The number of trains must be greater than 1.", 
						"Warning", JOptionPane.INFORMATION_MESSAGE);
				
			} else {
				new ControllerCreateBoard(trains, rows, columns);
			}
		}
	}
}
