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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class ViewCreateBoard extends JFrame {
	private ControllerCreateBoard controllerCreateBoard;
	private int rows, columns;
	private JButton[][] buttonsBoard;
	JButton button;
	JFrame gameWindow;
	private JTextField textFieldDirection, textFieldWagons, textFieldRow, textFieldColumn;
	private JTextField textFieldDirectionDelete, textFieldWagonsDelete, textFieldRowDelete, textFieldColumnDelete;
	JButton buttonAdd, buttonDelete;
	int defaultDismissTimeout;

	public ViewCreateBoard(ControllerCreateBoard controller, int rows, int columns) {
		this.rows = rows+1;
		this.columns = columns+1;
		this.controllerCreateBoard = controller;

		this.gameWindow = new JFrame();
		this.createWindowCreateBoard();
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
		JMenuItem menuItemUnDoMove = new JMenuItem();
		JMenuItem menuItemReDoMove = new JMenuItem();
		JMenu menuTrains = new JMenu();
		JMenuItem menuItemAddTrain = new JMenuItem();
		JMenuItem menuItemDeleteTrain = new JMenuItem();
		JMenu menuRun = new JMenu();
		JMenuItem menuItemRunProgram = new JMenuItem();
		JMenu menuHelp = new JMenu();
		JMenuItem menuItemInstructions = new JMenuItem();
		JPanel panel = new JPanel();
		JMenuItem menuItemModificacion= new JMenuItem();

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

		menuItemUnDoMove.setText("Undo a move");
		menuItemUnDoMove.addActionListener(controllerCreateBoard);
		menuItemUnDoMove.setActionCommand("unDoMove");
		menuMove.add(menuItemUnDoMove);

		menuItemReDoMove.setText("Redo a move");
		menuItemReDoMove.addActionListener(controllerCreateBoard);
		menuItemReDoMove.setActionCommand("reDoMove");
		menuMove.add(menuItemReDoMove);

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

		menuItemRunProgram.setText("Run program automatically");
		menuItemRunProgram.addActionListener(controllerCreateBoard);
		menuItemRunProgram.setActionCommand("run");
		menuRun.add(menuItemRunProgram);

		getContentPane().add(menuBar, BorderLayout.NORTH);

		menuHelp.setText("Help");
		menuBar.add(menuHelp);

		menuItemInstructions.setText("Get program instructions");
		menuItemInstructions.addActionListener(controllerCreateBoard);
		menuItemInstructions.setActionCommand("instructions");
		menuHelp.add(menuItemInstructions);
		
		menuItemModificacion.setText("Modificacion práctica");
		menuItemModificacion.addActionListener(controllerCreateBoard);
		menuItemModificacion.setActionCommand("modificacionPractica");
		menuHelp.add(menuItemModificacion);

		menuBar.add(menuHelp);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		GridLayout grid = new GridLayout(this.rows, this.columns);
		panel.setLayout(grid);
		panel.setSize(500, 500);

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
		String buttonPosition;
		String rowIndex;
		String columnIndex;

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				button = new JButton();
				buttonsBoard[i][j] = button;


				if (controllerCreateBoard.getBoard()[i][j] == 'W') {
					if((i==0) && (j==0)) {
						button.setBackground(Color.WHITE);
					} 

					if((i!=0) && (j == 0)) {
						int goodRow = (rows-1)-i;
						button.setBackground(Color.WHITE);
						button.setText(""+ (goodRow) +"");
					}

					if((i == 0) && (j != 0)) {
						button.setBackground(Color.WHITE);
						button.setText(""+ (j-1) +"");
					}
				}

				if (controllerCreateBoard.getBoard()[i][j] == '·') {
					button.setBackground(Color.WHITE);
				}


				button.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						for (int row = 1; row < rows; row++) {
							for (int column = 1; column < columns; column++) {
								if (buttonsBoard[row][column] == evt.getSource()) {
									int goodRow = (rows-1)-row;
									defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
									ToolTipManager.sharedInstance().setDismissDelay(60000);
									buttonsBoard[row][column].setToolTipText("(" + (column-1) + ", " + goodRow + ")");
								}
							}
						}
					}

					public void mouseExited(MouseEvent evt) {
						for (int row = 1; row < rows; row++) {
							for (int column = 1; column < columns; column++) {
								if (buttonsBoard[row][column] == evt.getSource()) {
									ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
								}
							}
						}
					}
				});

				button.addActionListener(controllerCreateBoard);
				button.setPreferredSize(new Dimension(30, 30));

				rowIndex = new Integer(i).toString();
				columnIndex = new Integer(j).toString();
				buttonPosition = rowIndex + "," + columnIndex;
				button.setActionCommand(buttonPosition);
				panel.add(button);

			}
		}
	}


	public void modifyBoard() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (controllerCreateBoard.getBoard()[i][j] == '0') {
					buttonsBoard[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}


	public void addNewTrain() {
		JFrame windowAddTrain = new JFrame();

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

		JLabel labelColumnAdd = new JLabel();
		labelColumnAdd.setText("Initial column:");
		labelColumnAdd.setBounds(83, 140, 200, 20);
		windowAddTrain.getContentPane().add(labelColumnAdd);

		textFieldColumn = new JTextField();
		textFieldColumn.setBounds(83, 160, 200, 20);
		windowAddTrain.getContentPane().add(textFieldColumn);

		JLabel labelRowAdd = new JLabel();
		labelRowAdd.setText("Initial row:");
		labelRowAdd.setBounds(83, 100, 200, 20);
		windowAddTrain.getContentPane().add(labelRowAdd);

		textFieldRow = new JTextField();
		textFieldRow.setBounds(83, 120, 200, 20);
		windowAddTrain.getContentPane().add(textFieldRow);

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


	public void drawTrain() {
		int x;
		int y;

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {

				if((buttonsBoard[i][j].getBackground() != Color.BLACK) && (controllerCreateBoard.getBoard()[i][j] != 'W')) {
					buttonsBoard[i][j].setBackground(Color.WHITE);	
					buttonsBoard[i][j].setText("");
				}
			}
		}

		for (int i = 0; i < controllerCreateBoard.getTrains().size(); i++) {
			x = controllerCreateBoard.getTrains().get(i).getPosition().getRow();
			y = controllerCreateBoard.getTrains().get(i).getPosition().getColumn();

			int z = 0;

			if ((controllerCreateBoard.getTrains().get(i).getDirection() == 'B') && (controllerCreateBoard.getTrains().get(i).getWagons() != 0)) {
				while (z < controllerCreateBoard.getTrains().get(i).getWagons()) {
					buttonsBoard[x-z][y].setBackground(Color.RED);
					buttonsBoard[x-z][y].setText("B");
					z++;
				}
			}


			if ((controllerCreateBoard.getTrains().get(i).getDirection() == 'A') && (controllerCreateBoard.getTrains().get(i).getWagons() != 0)) {
				while (z < controllerCreateBoard.getTrains().get(i).getWagons()) {
					buttonsBoard[x+z][y].setBackground(Color.YELLOW);
					buttonsBoard[x+z][y].setText("A");
					z++;
				}
			}

			if ((controllerCreateBoard.getTrains().get(i).getDirection() == 'I') && (controllerCreateBoard.getTrains().get(i).getWagons() != 0)) {
				while (z < controllerCreateBoard.getTrains().get(i).getWagons()) {
					buttonsBoard[x][y+z].setBackground(Color.ORANGE);
					buttonsBoard[x][y+z].setText("I");
					z++;
				}
			}

			if ((controllerCreateBoard.getTrains().get(i).getDirection() == 'D') && (controllerCreateBoard.getTrains().get(i).getWagons() != 0)) {
				while (z < controllerCreateBoard.getTrains().get(i).getWagons()) {
					buttonsBoard[x][y-z].setBackground(Color.GREEN);
					buttonsBoard[x][y-z].setText("D");
					z++;
				}
			}
		}
	}


	public void deleteTrain() {
		JFrame windowDeleteTrain = new JFrame();

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


	public void deleteTrainFromTheBoard() {
		for (int i = 0; i < controllerCreateBoard.getTrains().size(); i++) {
			int x = controllerCreateBoard.getTrains().get(i).getPosition().getRow();
			int y = controllerCreateBoard.getTrains().get(i).getPosition().getColumn();

			if ((controllerCreateBoard.getTrains().get(i).getDirection() == controllerCreateBoard.getDirectionDelete())
					&& (controllerCreateBoard.getTrains().get(i).getWagons() == controllerCreateBoard.getWagonsDelete())
					&& (controllerCreateBoard.getTrains().get(i).getPosition().getRow() == controllerCreateBoard.getRowDelete())
					&& (controllerCreateBoard.getTrains().get(i).getPosition().getColumn() == controllerCreateBoard.getColumnDelete())) {

				if (controllerCreateBoard.getTrains().get(i).getDirection() == 'B') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x - (controllerCreateBoard.getTrains().get(i).getWagons() - 1);
					int yTail = y;
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for (int j = 1; j < controllerCreateBoard.getTrains().get(i).getWagons(); j++) {
						buttonsBoard[x - controllerCreateBoard.getTrains().get(i).getWagons() + j][yTail].setBackground(Color.WHITE);
						buttonsBoard[x - controllerCreateBoard.getTrains().get(i).getWagons() + j][yTail].setText("");
					}
				}

				if (controllerCreateBoard.getTrains().get(i).getDirection() == 'A') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x + (controllerCreateBoard.getTrains().get(i).getWagons() - 1);
					int yTail = y;
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for (int j = 1; j < controllerCreateBoard.getTrains().get(i).getWagons(); j++) {
						buttonsBoard[x + controllerCreateBoard.getTrains().get(i).getWagons() - j][yTail].setBackground(Color.WHITE);
						buttonsBoard[x + controllerCreateBoard.getTrains().get(i).getWagons() - j][yTail].setText("");
					}
				}

				if (controllerCreateBoard.getTrains().get(i).getDirection() == 'I') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x;
					int yTail = y + (controllerCreateBoard.getTrains().get(i).getWagons() - 1);
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for (int j = 1; j < controllerCreateBoard.getTrains().get(i).getWagons(); j++) {
						buttonsBoard[x][yTail - controllerCreateBoard.getTrains().get(i).getWagons() + j].setBackground(Color.WHITE);
						buttonsBoard[x][yTail - controllerCreateBoard.getTrains().get(i).getWagons() + j].setText("");
					}
				}

				if (controllerCreateBoard.getTrains().get(i).getDirection() == 'D') {
					buttonsBoard[x][y].setBackground(Color.WHITE);
					buttonsBoard[x][y].setText("");
					int xTail = x;
					int yTail = y - (controllerCreateBoard.getTrains().get(i).getWagons() - 1);
					buttonsBoard[xTail][yTail].setBackground(Color.WHITE);
					buttonsBoard[xTail][yTail].setText("");

					for (int j = 1; j < controllerCreateBoard.getTrains().get(i).getWagons(); j++) {
						buttonsBoard[x][yTail + controllerCreateBoard.getTrains().get(i).getWagons() - j].setBackground(Color.WHITE);
						buttonsBoard[x][yTail + controllerCreateBoard.getTrains().get(i).getWagons() - j].setText("");
					}
				}

				controllerCreateBoard.getTrains().remove(controllerCreateBoard.getTrains().get(i));
			}
		}
	}


	public void paintCollision(Position position) {
		int row = position.getRow();
		int column = position.getColumn();

		buttonsBoard[row][column].setBackground(Color.BLACK);
		buttonsBoard[row][column].setText("X");
		buttonsBoard[row][column].setForeground(Color.WHITE);
	}


	public void moveWhenClicked(int row, int column) {
		int chosenTrain = -1;

		for (int i = 0; i < controllerCreateBoard.getTrains().size(); i++) {
			if ((row == controllerCreateBoard.getTrains().get(i).getPosition().getRow()) && (column == controllerCreateBoard.getTrains().get(i).getPosition().getColumn())) {
				chosenTrain = i;
			}
		}

		if (chosenTrain != -1) {
			switch (controllerCreateBoard.getTrains().get(chosenTrain).getDirection()) {
			case 'B':
				if (row == rows-1) {
					controllerCreateBoard.getTrains().get(chosenTrain).setWagons(controllerCreateBoard.getTrains().get(chosenTrain).getWagons()-1);
				} else {
					controllerCreateBoard.getTrains().get(chosenTrain).getPosition().setRow(row + 1);
					buttonsBoard[row][column].setBackground(Color.RED);
				}

				break;

			case 'A': 
				if (row == 1) { 
					controllerCreateBoard.getTrains().get(chosenTrain).setWagons(controllerCreateBoard.getTrains().get(chosenTrain).getWagons()-1);
				} else {
					controllerCreateBoard.getTrains().get(chosenTrain).getPosition().setRow(row - 1);
					buttonsBoard[row][column].setBackground(Color.YELLOW);
				}

				break;

			case 'I': 
				if (column == 1) { 
					controllerCreateBoard.getTrains().get(chosenTrain).setWagons(controllerCreateBoard.getTrains().get(chosenTrain).getWagons()-1);
				} else {
					controllerCreateBoard.getTrains().get(chosenTrain).getPosition().setColumn(column -1);
					buttonsBoard[row][column].setBackground(Color.ORANGE);
				}

				break;

			case 'D': 
				if (column == columns-1) { 
					controllerCreateBoard.getTrains().get(chosenTrain).setWagons(controllerCreateBoard.getTrains().get(chosenTrain).getWagons()-1);
				} else {
					controllerCreateBoard.getTrains().get(chosenTrain).getPosition().setColumn(column + 1);
					buttonsBoard[row][column].setBackground(Color.GREEN);
				}

				break;

			}
			
			drawTrain();
		}
	}


	public void getInstructions() {
		JFrame windowInstructions = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea text = new JTextArea("INSTRUCCIONES PARA JUGAR\n");
		text.append("\n");
		text.append("CREACIÓN DE UN NUEVO TABLERO\n");
		text.append("Para crear un nuevo tablero, seleccione New Simulation, dentro del menú File.\n");
		text.append("Recordatorio: el número de trenes ha de ser un número entre 1 y 10, ambos inclusive.\n");
		text.append("Recordatorio: el número de filas y columnas ha de ser un número entre 10 y 30, ambos inclusive.\n");
		text.append("\n");
		text.append("CARGAR UN TABLERO EXISTENTE\n");
		text.append("Para cargar un tablero, seleccione Open existing simulation, dentro del menú File, y busque el archivo que quiera abrir.\n");
		text.append("Una vez seleccionado, se mostrará el tablero que contenía el archivo.\n");
		text.append("\n");
		text.append("AÑADIR UN TREN AL TABLERO\n");
		text.append("Para añadir un tren al tablero, seleccione el menú Trains, y dentro de este, el menú Add train.\n");
		text.append("El programa le informará en caso de que exista algún error en el momento de introducir la información del tren.\n");
		text.append("Cuando pulse el botón Add train, el tren creado se mostrará automáticamente en el tablero.\n");
		text.append("Los trenes tendrán un color en función de su direccón: ROJO - ABAJO , AMARILLO - ARRIBA , NARANJA - IZQUIERDA , VERDE - DERECHA\n");
		text.append("\n");
		text.append("ELIMINAR UN TREN DEL TABLERO\n");
		text.append("Para eliminar un tren del tablero, seleccione el menú Trains, y dentro de este, el menú Delete train.\n");
		text.append("Solo se eliminará un tren si se introduce su información de manera correcta.");
		text.append("Cuando pulse el botón Delete train, el tren seleccionado se eliminará automáticamente del tablero.\n");
		text.append("\n");
		text.append("MOVER UN TREN EN EL TABLERO\n");
		text.append("Para mover un tren en el tablero, seleccione el menú Edit, y dentro de este, el menú Move train.\n");
		text.append("Una vez pulsado, haga click en el primer vagón del tren que quiera mover y este se moverá una unidad en la dirección correspondiente.\n");
		text.append("\n");
		text.append("EJECUTAR EL JUEGO AUTOMÁTICAMENTE\n");
		text.append("Para realizar una simulación automática del juego, haga click en el menú Run y dentro de él, en el submenú Run program automatically.\n");
		text.append("Cada vez que pulse ese menú, se moverá el tren correspondiente.");
		text.append("Cuando todos los trenes estén fuera del tablero, no se permitirá seguir pulsando el submenú.");
		text.append("\n");
		text.append("SALIR DEL PROGRAMA\n");
		text.append("Para salir del programa, seleccione la opción Exit dentro del menú File.\n");

		text.setEditable(false);
		JScrollPane displacement = new JScrollPane(text);
		panel.add(displacement, BorderLayout.CENTER);
		getContentPane().add(panel);

		JMenuBar menuBarMenu = new JMenuBar();
		windowInstructions.getContentPane().setLayout(new BorderLayout());

		windowInstructions.getContentPane().add(menuBarMenu, BorderLayout.NORTH);

		windowInstructions.setTitle("Game instructions");
		windowInstructions.setBounds(500, 200, 400, 300);
		windowInstructions.setSize(600, 600);
		windowInstructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowInstructions.setContentPane(panel);
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

	public int getRows(){
		return rows;
	}

	public int getColumns(){
		return columns;
	}
}