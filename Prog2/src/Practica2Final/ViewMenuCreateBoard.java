package Practica2Final;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JMenuBar;

import javax.swing.JTextField;

public class ViewMenuCreateBoard extends JFrame {
	private ControllerMenuCreateBoard controllerCreateBoardMenu;
	private JTextField textFieldTrains;
	private JTextField textFieldRows;
	private JTextField textFieldColumns;
	
	
	public ViewMenuCreateBoard(ControllerMenuCreateBoard controller) {
		this.controllerCreateBoardMenu = controller;
		this.createInitialWindowToPlay();
	}

	
	public void createInitialWindowToPlay() {
		JLabel labelTrains = new JLabel();
		labelTrains.setText("Number of trains:");
		labelTrains.setBounds(83, 40, 200, 20);
		this.add(labelTrains);
		
		textFieldTrains = new JTextField();
		textFieldTrains.setBounds(83, 60, 200, 20);
		this.add(textFieldTrains);
		
		JLabel labelRows = new JLabel();
		labelRows.setText("Number of rows:");
		labelRows.setBounds(83, 80, 200, 20);
		this.add(labelRows);
		
		textFieldRows = new JTextField();
		textFieldRows.setBounds(83, 100, 200, 20);
		this.add(textFieldRows);
		
		JLabel labelColumns = new JLabel();
		labelColumns.setText("Number of columns:");
		labelColumns.setBounds(83, 120, 200, 20);
		this.add(labelColumns);
		
		textFieldColumns = new JTextField();
		textFieldColumns.setBounds(83, 140, 200, 20);
		this.add(textFieldColumns);
		
		JButton buttonAccept = new JButton();
		buttonAccept.setText("OK");
		buttonAccept.setBounds(133, 175, 100, 50);
		buttonAccept.addActionListener(controllerCreateBoardMenu);
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
	
	
	public JTextField getTextFieldTrains() {
		return textFieldTrains;
	}
	
	public JTextField getTextFieldRows() {
		return textFieldRows;
	}

	public JTextField getTextFieldColumns() {
		return textFieldColumns;
	}
}

