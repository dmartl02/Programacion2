package Practica2Final_2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;


import Practica2Final.ControllerInitialMenu;

public class ViewInitialMenu extends JFrame {
	private ControllerInitialMenu controlerInitialMenu;
	
	public ViewInitialMenu(ControllerInitialMenu controller) {
		this.controlerInitialMenu = controller;
		this.createInitialWindow();
	}
	
	public void createInitialWindow() {
		JLabel welcome = new JLabel();
		welcome.setText("Welcome to the game of trains!");
		welcome.setBounds(140, 10, 350, 50);
		this.add(welcome);
		
		JButton buttonCreate = new JButton();
		buttonCreate.setText("Create new board");
		buttonCreate.setBounds(125, 80, 200, 50);
		buttonCreate.setBackground(Color.ORANGE);
		buttonCreate.addActionListener(controlerInitialMenu);
		buttonCreate.setActionCommand("createNewBoard");
		this.add(buttonCreate);

		JMenuBar menuBarMenu = new JMenuBar();
		this.setLayout(new BorderLayout());
		this.add(menuBarMenu, BorderLayout.NORTH);
		
		this.setTitle("Initial Menu");
		this.setResizable(false);
		this.setBounds(458, 234, 450, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
