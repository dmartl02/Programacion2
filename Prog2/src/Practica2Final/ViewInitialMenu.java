package Practica2Final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import Practica2Final.ControllerInitialMenu;

public class ViewInitialMenu extends JFrame{
	private ControllerInitialMenu controlerInitialMenu;
	private File file;
	
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

		JButton buttonPlay = new JButton();
		buttonPlay.setText("Play with an existing board");
		buttonPlay.setBounds(125, 150, 200, 50);
		buttonPlay.setBackground(Color.ORANGE);
		buttonPlay.addActionListener(controlerInitialMenu);
		buttonPlay.setActionCommand("playExistingBoard");
		this.add(buttonPlay);
		
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
