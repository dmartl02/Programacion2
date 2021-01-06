package Practica2Final;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewCreateBoard extends JFrame{
	private ControllerCreateBoard controllerCreateBoard;
	private int rows, columns;
	private JButton[][] buttonsBoard;
	JButton button;
	JFrame gameWindow;  //ventana que aparece al grabar la solucion correctamente
	private JTextField textFieldDirection, textFieldWagons, textFieldRow, textFieldColumn;
	JButton buttonAdd, buttonDelete;
	private JTextField textFieldDirectionDelete, textFieldWagonsDelete, textFieldRowDelete, textFieldColumnDelete;
	private JFrame windowAddTrain, windowDeleteTrain, windowChooseTrainToMove, windowInstructions;


	public ViewCreateBoard(ControllerCreateBoard controller, int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.controllerCreateBoard = controller;
		
		this.gameWindow = new JFrame();
		this.createWindowCreateBoard();
		
		this.windowAddTrain = new JFrame();
		
	}

	
	public void createWindowCreateBoard() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu();
		JMenuItem menuItemNewSimulation = new JMenuItem();
		JSeparator separator1_menuFile = new JSeparator();
		JMenuItem menuItemOpen = new JMenuItem();
		JSeparator separator2_menuFile = new JSeparator();
		JMenu menuSave = new JMenu();
		JMenuItem menuItemSave = new JMenuItem();
		JMenuItem menuItemSaveAs = new JMenuItem();
		JSeparator separator3_menuFile = new JSeparator();
		JMenuItem menuItemExit = new JMenuItem();
		JMenu menuEdit = new JMenu();
		JMenu menuMove = new JMenu();
		JMenuItem menuItemMakeMove = new JMenuItem();
		JMenuItem menuItemRemakeMove = new JMenuItem();
		JMenu menuTrains = new JMenu();
		JMenuItem menuItemAddTrain = new JMenuItem();
		JMenuItem menuItemDeleteTrain = new JMenuItem();
		JMenu menuRun = new JMenu();
		JMenu menuHelp = new JMenu();
		JMenuItem menuItemInstructions = new JMenuItem();
		JPanel panel = new JPanel();
		
		
		menuFile.setText("File");
		getContentPane().setLayout(new BorderLayout());

		menuItemNewSimulation.setText("New simulation");
		menuItemNewSimulation.addActionListener(controllerCreateBoard);
		menuItemNewSimulation.setActionCommand("createNewSimulation");
		menuFile.add(menuItemNewSimulation);

		menuFile.add(separator1_menuFile);

		menuItemOpen.setText("Open existing simulation");
		menuItemOpen.addActionListener(controllerCreateBoard);
		menuItemOpen.setActionCommand("openSimulation");
		menuFile.add(menuItemOpen);

		menuFile.add(separator2_menuFile);

		menuSave.setText("Save...");
		menuFile.add(menuSave);

		menuItemSave.setText("Save");
		menuItemSave.addActionListener(controllerCreateBoard);
		menuItemSave.setActionCommand("saveGame");
		menuSave.add(menuItemSave);

		menuItemSaveAs.setText("Save As...");
		menuItemSaveAs.addActionListener(controllerCreateBoard);
		menuItemSaveAs.setActionCommand("saveGameAs");
		menuSave.add(menuItemSaveAs);

		menuFile.add(separator3_menuFile);

		menuItemExit.setText("Exit");
		menuItemExit.addActionListener(controllerCreateBoard);
		menuItemExit.setActionCommand("exit");
		menuFile.add(menuItemExit);

		menuBar.add(menuFile);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		menuEdit.setText("Edit");

		menuMove.setText("Move");
		menuEdit.add(menuMove);

		menuItemMakeMove.setText("Make a move");
		menuItemMakeMove.addActionListener(controllerCreateBoard);
		menuItemMakeMove.setActionCommand("makeMove");
		menuMove.add(menuItemMakeMove);

		menuItemRemakeMove.setText("Remake a move");
		menuItemRemakeMove.addActionListener(controllerCreateBoard);
		menuItemRemakeMove.setActionCommand("remakeMove");
		menuMove.add(menuItemRemakeMove);
		
		menuBar.add(menuEdit);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		menuTrains.setText("Trains");

		menuItemAddTrain.setText("Add train");
		menuItemAddTrain.addActionListener(controllerCreateBoard);
		menuItemAddTrain.setActionCommand("addTrain");
		menuTrains.add(menuItemAddTrain);

		menuItemDeleteTrain.setText("Delete train");
		menuItemDeleteTrain.addActionListener(controllerCreateBoard);
		menuItemDeleteTrain.setActionCommand("deleteTrain");
		menuTrains.add(menuItemDeleteTrain);

		menuBar.add(menuTrains);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		menuRun.setText("Run");
		menuBar.add(menuRun);
		
		menuBar.add(menuRun);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		menuHelp.setText("Help");
		menuBar.add(menuHelp);

		menuItemInstructions.setText("Get program instructions");
		menuItemInstructions.addActionListener(controllerCreateBoard);
		menuItemInstructions.setActionCommand("instructions");
		menuHelp.add(menuItemInstructions);
		
		menuBar.add(menuHelp);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		GridLayout grid = new GridLayout(this.rows, this.columns);
		panel.setLayout(grid);
		panel.setSize(500,500);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		this.createBoard(panel);
		scrollPane.setViewportView(panel);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.setTitle("Playing with trains");
		this.setBounds(483, 184, 400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void createBoard(JPanel panel) {
		buttonsBoard = new JButton[this.rows][this.columns];
		String buttonCommand;
		String rowIndex;
		String columnIndex;
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				button = new JButton();
				buttonsBoard[i][j] = button;
				 
				if (controllerCreateBoard.getBoard()[i][j] == 0) {
					button.setBackground(Color.WHITE);
				}
			
				button.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						 for (int row = 0; row < rows; row++) {
							 for (int column = 0; column < columns; column++) {
								 if (buttonsBoard[row][column] == evt.getSource()){
									 button.setText("(" + row + ", " + column + ")");
								 }
							 }
						 }
					}
					
					public void mouseExited(MouseEvent evt) {
						button.setText("");
					}
				});
				
				button.addActionListener(controllerCreateBoard);
				button.setActionCommand("selectedButton");
				button.setPreferredSize(new Dimension(30, 30));
				rowIndex = new Integer(i).toString();
				columnIndex = new Integer(j).toString();
				buttonCommand = rowIndex + "," + columnIndex;
				button.setActionCommand(buttonCommand);
				panel.add(button);
			}
		}
	}
		
	
	/*public void modifyBoard() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (controllerCreateBoard.getBoard()[i][j] == 0) {
					buttonsBoard[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}*/
	
	public void addNewTrain() {
		windowAddTrain = new JFrame();
		
		JLabel labelDirectionAdd = new JLabel();
		labelDirectionAdd.setText("Direction:");
		labelDirectionAdd.setBounds(83, 20, 200, 20);
		windowAddTrain.getContentPane().add(labelDirectionAdd);
		
		textFieldDirection = new JTextField();
		textFieldDirection.setBounds(83, 40, 200, 20);
		windowAddTrain.getContentPane().add(textFieldDirection);
		
		JLabel labelWagonsAdd = new JLabel();
		labelWagonsAdd.setText("Number of wagons:");
		labelWagonsAdd.setBounds(83, 60, 200, 20);
		windowAddTrain.getContentPane().add(labelWagonsAdd);
		
		textFieldWagons = new JTextField();
		textFieldWagons.setBounds(83, 80, 200, 20);
		windowAddTrain.getContentPane().add(textFieldWagons);
		
		JLabel labelRowAdd = new JLabel();
		labelRowAdd.setText("Initial row:");
		labelRowAdd.setBounds(83, 100, 200, 20);
		windowAddTrain.getContentPane().add(labelRowAdd);
		
		textFieldRow = new JTextField();
		textFieldRow.setBounds(83, 120, 200, 20);
		windowAddTrain.getContentPane().add(textFieldRow);
		
		JLabel labelColumnAdd = new JLabel();
		labelColumnAdd.setText("Initial column:");
		labelColumnAdd.setBounds(83, 140, 200, 20);
		windowAddTrain.getContentPane().add(labelColumnAdd);
		
		textFieldColumn = new JTextField();
		textFieldColumn.setBounds(83, 160, 200, 20);
		windowAddTrain.getContentPane().add(textFieldColumn);
		
		buttonAdd = new JButton();
		buttonAdd.setText("Add train");
		buttonAdd.setBounds(133, 190, 100, 50);
		buttonAdd.addActionListener(controllerCreateBoard);
		buttonAdd.setActionCommand("buttonAdd");
		windowAddTrain.getContentPane().add(buttonAdd);
		
		JMenuBar menuBarMenu = new JMenuBar();
		windowAddTrain.getContentPane().setLayout(new BorderLayout());

		windowAddTrain.getContentPane().add(menuBarMenu, BorderLayout.NORTH);
		
		windowAddTrain.setTitle("Add a new train");
		windowAddTrain.setResizable(false);
		windowAddTrain.setBounds(500, 200, 400, 300);
		windowAddTrain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowAddTrain.setVisible(true);
	}
	
	
	public void drawTrain(ArrayList<Train> trains) {
		int x = 0;
		int y = 0;
		Train temp;
		
		for (int i = 0; i < trains.size() ; i++) {
			temp = trains.get(i);
		
			x = temp.getRow();
			y = temp.getColumn();
			
			if(temp.getDirection() == 'B') {
				buttonsBoard[x][y].setBackground(Color.RED);
				
				buttonsBoard[x][y].setText("B");
				int xTail = x - (temp.getWagons()-1);
				int yTail = y;
				buttonsBoard[xTail][yTail].setBackground(Color.RED);
				buttonsBoard[xTail][yTail].setText("B");

				for(int j = 1; j<temp.getWagons(); j++) {
					buttonsBoard[x-temp.getWagons()+j][yTail].setBackground(Color.RED);
					buttonsBoard[x-temp.getWagons()+j][yTail].setText("B");
				}
			}

			if(temp.getDirection() == 'A') {
				buttonsBoard[x][y].setBackground(Color.YELLOW);
				buttonsBoard[x][y].setText("A");
				int xTail = x + (temp.getWagons()-1);
				int yTail = y;
				buttonsBoard[xTail][yTail].setBackground(Color.YELLOW);
				buttonsBoard[xTail][yTail].setText("A");

				for(int j = 1; j<temp.getWagons(); j++) {
					buttonsBoard[x+temp.getWagons()-j][yTail].setBackground(Color.YELLOW);
					buttonsBoard[x+temp.getWagons()-j][yTail].setText("A");
				}
			}

			if(temp.getDirection() == 'I') {
				buttonsBoard[x][y].setBackground(Color.ORANGE);
				buttonsBoard[x][y].setText("I");
				int xTail = x;
				int yTail = y + (temp.getWagons()-1);
				buttonsBoard[xTail][yTail].setBackground(Color.ORANGE);
				buttonsBoard[xTail][yTail].setText("I");

				for(int j = 1; j<temp.getWagons(); j++) {
					buttonsBoard[x][yTail-temp.getWagons()+j].setBackground(Color.ORANGE);
					buttonsBoard[x][yTail-temp.getWagons()+j].setText("I");
				}

			}

			if(temp.getDirection() == 'D') {
				buttonsBoard[x][y].setBackground(Color.GREEN);
				buttonsBoard[x][y].setText("D");
				int xTail = x;
				int yTail = y - (temp.getWagons()-1);
				buttonsBoard[xTail][yTail].setBackground(Color.GREEN);
				buttonsBoard[xTail][yTail].setText("D");

				for(int j = 1; j<temp.getWagons(); j++) {
					buttonsBoard[x][yTail+temp.getWagons()-j].setBackground(Color.GREEN);
					buttonsBoard[x][yTail+temp.getWagons()-j].setText("D");
				}
			}
		}
	}
	
	public void deleteTrain() {
		windowDeleteTrain = new JFrame();
		
		JLabel labelDirection = new JLabel();
		labelDirection.setText("Direction:");
		labelDirection.setBounds(83, 20, 200, 20);
		windowDeleteTrain.getContentPane().add(labelDirection);
		
		textFieldDirectionDelete = new JTextField();
		textFieldDirectionDelete.setBounds(83, 40, 200, 20);
		windowDeleteTrain.getContentPane().add(textFieldDirectionDelete);
		
		JLabel labelWagons = new JLabel();
		labelWagons.setText("Number of wagons:");
		labelWagons.setBounds(83, 60, 200, 20);
		windowDeleteTrain.getContentPane().add(labelWagons);
		
		textFieldWagonsDelete = new JTextField();
		textFieldWagonsDelete.setBounds(83, 80, 200, 20);
		windowDeleteTrain.getContentPane().add(textFieldWagonsDelete);
		
		JLabel labelRow = new JLabel();
		labelRow.setText("Initial row:");
		labelRow.setBounds(83, 100, 200, 20);
		windowDeleteTrain.getContentPane().add(labelRow);
		
		textFieldRowDelete = new JTextField();
		textFieldRowDelete.setBounds(83, 120, 200, 20);
		windowDeleteTrain.getContentPane().add(textFieldRowDelete);
		
		JLabel labelColumn = new JLabel();
		labelColumn.setText("Initial column:");
		labelColumn.setBounds(83, 140, 200, 20);
		windowDeleteTrain.getContentPane().add(labelColumn);
		
		textFieldColumnDelete = new JTextField();
		textFieldColumnDelete.setBounds(83, 160, 200, 20);
		windowDeleteTrain.getContentPane().add(textFieldColumnDelete);
		
		buttonDelete = new JButton();
		buttonDelete.setText("Delete train");
		buttonDelete.setBounds(133, 190, 100, 50);
		buttonDelete.addActionListener(controllerCreateBoard);
		buttonDelete.setActionCommand("buttonDelete");
		windowDeleteTrain.getContentPane().add(buttonDelete);
		
		JMenuBar menuBarMenu = new JMenuBar();
		windowDeleteTrain.getContentPane().setLayout(new BorderLayout());

		windowDeleteTrain.getContentPane().add(menuBarMenu, BorderLayout.NORTH);
		
		windowDeleteTrain.setTitle("Information of the train to delete");
		windowDeleteTrain.setResizable(false);
		windowDeleteTrain.setBounds(500, 200, 400, 300);
		windowDeleteTrain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowDeleteTrain.setVisible(true);
	}
	
	
	public void deleteTrainFromTheBoard(ArrayList<Train> trains) {
		int x = 0;
		int y = 0;
		Train temp;
		
		for (int i = 0; i < trains.size() ; i++) {
			temp = trains.get(i);
			
			if((temp.getDirection() == controllerCreateBoard.getDirectionDelete()) && (temp.getWagons() == controllerCreateBoard.getWagonsDelete())
					&& (temp.getRow() == controllerCreateBoard.getRowDelete()) && (temp.getColumn() == controllerCreateBoard.getColumnDelete())) {
				
				x = temp.getRow();
				y = temp.getColumn();
				
				if(temp.getDirection() == 'B') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x - (temp.getWagons()-1);
					int yTail = y;
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for(int j = 1; j<temp.getWagons(); j++) {
						buttonsBoard[x-temp.getWagons()+j][yTail].setBackground(Color.WHITE);
						buttonsBoard[x-temp.getWagons()+j][yTail].setText("");
					}
					
					trains.remove(temp);
				}

				if(temp.getDirection() == 'A') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x + (temp.getWagons()-1);
					int yTail = y;
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for(int j = 1; j<temp.getWagons(); j++) {
						buttonsBoard[x+temp.getWagons()-j][yTail].setBackground(Color.WHITE);
						buttonsBoard[x+temp.getWagons()-j][yTail].setText("");
					}
					
					trains.remove(temp);
				}

				if(temp.getDirection() == 'I') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x;
					int yTail = y + (temp.getWagons()-1);
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for(int j = 1; j<temp.getWagons(); j++) {
						buttonsBoard[x][yTail-temp.getWagons()+j].setBackground(Color.WHITE);
						buttonsBoard[x][yTail-temp.getWagons()+j].setText("");
					}
					
					trains.remove(temp);
				}

				if(temp.getDirection() == 'D') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x;
					int yTail = y - (temp.getWagons()-1);
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for(int j = 1; j<temp.getWagons(); j++) {
						buttonsBoard[x][yTail+temp.getWagons()-j].setBackground(Color.WHITE);
						buttonsBoard[x][yTail+temp.getWagons()-j].setText("");
					}
					
					trains.remove(temp);
				}
			}		
		}	
	}
	

	public void getInstructions() {
		windowInstructions = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea text = new JTextArea();
		JScrollPane displacement = new JScrollPane(text);
		panel.add(displacement, BorderLayout.CENTER);
		getContentPane().add(panel);

		JMenuBar menuBarMenu = new JMenuBar();
		windowInstructions.getContentPane().setLayout(new BorderLayout());

		windowInstructions.getContentPane().add(menuBarMenu, BorderLayout.NORTH);
		
		windowInstructions.setTitle("Game instructions");
		windowInstructions.setResizable(false);
		windowInstructions.setBounds(500, 200, 400, 300);
		windowInstructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowInstructions.setVisible(true);
	}
	
	public JFrame getGameWindow() {
		return gameWindow;
	}
	
	public JButton[][] getButtonsBoard() {
		return buttonsBoard;
	}

	public JButton getButton() {
		return button;
	}

	public JFrame getWindowAddTrain() {
		return windowAddTrain;
	}
	
	
	
	public JTextField getTextFieldDirection() {
		return textFieldDirection;
	}
	
	public JTextField setTextFieldDirection() {
		this.textFieldDirection.setText("");
		return this.textFieldDirection;
	}
	
	public JTextField getTextFieldWagons() {
		return textFieldWagons;
	}
	
	public JTextField setTextFieldWagons() {
		this.textFieldWagons.setText("");
		return this.textFieldWagons;
	}
	
	public JTextField getTextFieldRow() {
		return textFieldRow;
	}
	
	public JTextField setTextFieldRow() {
		this.textFieldRow.setText("");
		return this.textFieldRow;
	}
	
	public JTextField getTextFieldColumn() {
		return textFieldColumn;
	}
	
	public JTextField setTextFieldColumn() {
		this.textFieldColumn.setText("");
		return this.textFieldColumn;
	}
	
	public JButton getButtonAdd() {
		return buttonAdd;
	}
	
	public JTextField getTextFieldDirectionDelete() {
		return textFieldDirectionDelete;
	}


	public JTextField setTextFieldDirectionDelete() {
		this.textFieldDirectionDelete.setText("");
		return this.textFieldDirectionDelete;
	}


	public JTextField getTextFieldWagonsDelete() {
		return textFieldWagonsDelete;
	}


	public JTextField setTextFieldWagonsDelete() {
		this.textFieldWagonsDelete.setText("");
		return this.textFieldWagonsDelete;
	}


	public JTextField getTextFieldRowDelete() {
		return textFieldRowDelete;
	}


	public JTextField setTextFieldRowDelete() {
		this.textFieldRowDelete.setText("");
		return this.textFieldRowDelete;
	}


	public JTextField getTextFieldColumnDelete() {
		return textFieldColumnDelete;
	}


	public JTextField setTextFieldColumnDelete() {
		this.textFieldColumnDelete.setText("");
		return this.textFieldColumnDelete;
	}
	
	public JButton getButtonDelete() {
		return buttonDelete;
	}
}


