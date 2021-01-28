package Practica9_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class TextEditor extends JFrame {
	private JFrame frame;
	private JPanel panel;
	private JTextArea text;
	private JScrollPane displacement;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuSave;
	private JMenuItem menuItemNewFile, menuItemOpen, menuItemSave, menuItemSaveAs, menuItemCopy, menuItemCut, menuItemPaste, menuItemExit;
	private File file;
	private static TextEditor textEditor;
	private JSeparator separator1, separator2, separator3, separator4;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textEditor = new TextEditor();
					textEditor.setBounds(0,0,800,600);
					textEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public TextEditor() {
		initialize();
	}
	
	
	private void initialize() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setTitle("Editor de texto");
		setBounds(0, 0, 300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		text = new JTextArea();
		displacement = new JScrollPane(text);
		panel.add(displacement, BorderLayout.CENTER);
		getContentPane().add(panel);

		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		menuItemNewFile = new JMenuItem("New");
		menuFile.add(menuItemNewFile);
		
		separator1 = new JSeparator();
		menuFile.add(separator1);

		menuItemOpen = new JMenuItem("Open...");
		menuFile.add(menuItemOpen);
		
		separator2 = new JSeparator();
		menuFile.add(separator2);

		menuSave = new JMenu("Save...");
		menuFile.add(menuSave);

		menuItemSave = new JMenuItem("Save");
		menuSave.add(menuItemSave);

		menuItemSaveAs = new JMenuItem("Save As...");
		menuSave.add(menuItemSaveAs);
		
		separator3 = new JSeparator();
		menuFile.add(separator3);

		menuItemCopy = new JMenuItem("Copy");
		menuFile.add(menuItemCopy);

		menuItemCut = new JMenuItem("Cut");
		menuFile.add(menuItemCut);

		menuItemPaste = new JMenuItem("Paste");
		menuFile.add(menuItemPaste);
		
		separator4 = new JSeparator();
		menuFile.add(separator4);

		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);

		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		menuItemNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file = null;
				text.setText(" ");
				menuItemSave.setEnabled(false);
			}
		});

		menuItemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog;
				dialog = new JFileChooser();
				dialog.showDialog(null, "Open");
				file = dialog.getSelectedFile();

				// Read selected file
				FileInputStream in;

				try {
					in = new FileInputStream(file);
					BufferedReader br;
					br = new BufferedReader(new InputStreamReader(in));
					String linea;
					text.setText(" ");

					// Store the content in the JTextArea
					while ((linea = br.readLine()) != null) {
						text.append(linea + "\n");
					}

					br.close();
					menuItemSave.setEnabled(true);
				}

				catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menuItemCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});

		menuItemCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});

		menuItemPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});

		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter printwriter;
					printwriter = new PrintWriter(file);
					printwriter.write(text.getText());
					printwriter.close();
				}

				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		menuItemSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser dialog;
					dialog = new JFileChooser();
					dialog.showDialog(null, "Save as");
					file = dialog.getSelectedFile();
					PrintWriter printwriter;
					printwriter = new PrintWriter(file);
					printwriter.write(text.getText());
					printwriter.close();
				}

				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	
	
	
	


}
