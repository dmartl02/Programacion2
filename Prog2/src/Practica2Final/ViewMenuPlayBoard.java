package Practica2Final;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class ViewMenuPlayBoard extends JFrame {
	private ControllerMenuPlayBoard controllerPlayBoardMenu;
	private JTextField textFieldRows;
	private JTextField textFieldColumns;
	
	
	public ViewMenuPlayBoard(ControllerMenuPlayBoard controller) {
		this.controllerPlayBoardMenu = controller;
		this.createInitialWindowToPlay();
	}

	
	public void createInitialWindowToPlay() {
		JLabel labelLines = new JLabel();
		labelLines.setText("Number of lines:");
		labelLines.setBounds(83, 40, 200, 20);
		this.add(labelLines);
		
		textFieldRows = new JTextField();
		textFieldRows.setBounds(83, 60, 200, 20);
		this.add(textFieldRows);
		
		JLabel labelColumns = new JLabel();
		labelColumns.setText("Number of columns:");
		labelColumns.setBounds(83, 80, 200, 20);
		this.add(labelColumns);
		
		textFieldColumns = new JTextField();
		textFieldColumns.setBounds(83, 100, 200, 20);
		this.add(textFieldColumns);
		
		JButton buttonAccept = new JButton();
		buttonAccept.setText("Accept");
		buttonAccept.setBounds(133, 175, 100, 50);
		buttonAccept.addActionListener(controllerPlayBoardMenu);
		buttonAccept.setActionCommand("buttonAccept");
		this.add(buttonAccept);
		
		JMenuBar menuBarMenu = new JMenuBar();
		this.setLayout(new BorderLayout());

		this.add(menuBarMenu, BorderLayout.NORTH);
		
		this.setTitle("Create board");
		this.setResizable(false);
		this.setBounds(500, 250, 366, 268);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	
	public JTextField getTextFieldRows() {
		return textFieldRows;
	}

	
	public JTextField getTextFieldColumns() {
		return textFieldColumns;
	}
}

