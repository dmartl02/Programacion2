package Practica2Final;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.SliderUI;

public class ControllerCreateBoard implements ActionListener {
	private ViewCreateBoard viewCreateBoard; 
	private static ArrayList<Train> trains;
	
	private Integer[][] board; 
	private Integer[][] initialBoard; 
	private int numberOfTrains, rows, columns;
	private static char direction, directionDelete, directionButton;
	private static int wagons, row, column, wagonsDelete, rowDelete, columnDelete, rowButton, columnButton;
	private static Train train;
	private final int MIN_TRAINS = 1;
	private static int counterTrains = 0;
	private File file;

	
	public ControllerCreateBoard(int numberOfTrains, int rows, int columns) {
		this.numberOfTrains = numberOfTrains;
		this.rows = rows;
		this.columns = columns;
		this.initializeBoard();
		this.initializeInitialBoard();
		
		this.viewCreateBoard = new ViewCreateBoard(this, rows, columns);
	
		trains = new ArrayList<Train>(numberOfTrains);
	}
	
	
	public void initializeBoard() {
		board = new Integer[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	public void initializeInitialBoard() {
		initialBoard = new Integer[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				initialBoard[i][j] = 0;
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("createNewSimulation")) {
			new ControllerMenuCreateBoard();
		}

		
		if(e.getActionCommand().equals("openSimulation")) {
			JFileChooser explorer = new JFileChooser();
			explorer.showOpenDialog(explorer);
			file = explorer.getSelectedFile();
			//entra = false;
			//this.escribirFichero(file, true);
		}
		
		
		if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
		
		
		if (e.getActionCommand().equals("makeMove")) {
			if(counterTrains < MIN_TRAINS) {
				JFrame noTrainsToMove= new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(noTrainsToMove, "There are no trains to move", 
						"Minimum Trains Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JFrame chooseATrainToMove= new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(chooseATrainToMove, "Click on the head of a train to move it", 
						"Moving Trains", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		if (e.getActionCommand().equals("addTrain")) {
			this.viewCreateBoard.addNewTrain();
			
			if(counterTrains == numberOfTrains) {
				JFrame frameNoMoreTrainsToAdd = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameNoMoreTrainsToAdd, "You can not add more trains.", 
						"Maximum Trains Warning", JOptionPane.INFORMATION_MESSAGE);
				
				viewCreateBoard.getTextFieldDirection().setEnabled(false);
				viewCreateBoard.getTextFieldWagons().setEnabled(false);
				viewCreateBoard.getTextFieldRow().setEnabled(false);
				viewCreateBoard.getTextFieldColumn().setEnabled(false);
				viewCreateBoard.getButtonAdd().setEnabled(false);
			}
		}
		
		
		if (e.getActionCommand().equals("buttonAdd")) {
			direction = viewCreateBoard.getTextFieldDirection().getText().charAt(0);
			wagons = Integer.parseInt(viewCreateBoard.getTextFieldWagons().getText());
			row = rows - (Integer.parseInt(viewCreateBoard.getTextFieldRow().getText())) -1;
			column = Integer.parseInt(viewCreateBoard.getTextFieldColumn().getText());
			
			if((direction != 'B') && (direction != 'A') && (direction != 'D') && (direction != 'I')) {
				JFrame frameDirection = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameDirection, "The direction must be 'B' , 'A' , 'I' or 'D'", 
						"Direction Warning", JOptionPane.INFORMATION_MESSAGE);
			} 
			
			if((wagons < 0) || (wagons > rows) || (wagons >  columns)){
				JFrame frameWagons = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameWagons, "The number of wagons must be greater than 0 and smaller or equal to the number of rows / columns.", 
						"Wagons Warning", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(row > rows) {
				JFrame frameRow = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameRow, "The initial row must be equal or smaller than the number of rows (" + rows + ")", 
						"Row Warning", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(column > columns) {
				JFrame frameColumn = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameColumn, "The initial column must be equal or smaller than the number of columns (" + columns + ")", 
						"Column Warning", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(direction == 'B') {
				if((row - wagons) > rows) {
					JFrame frameOutDown = new JFrame();
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(frameOutDown, "One or more wagons are created outside the board.", 
							"Column Warning", JOptionPane.INFORMATION_MESSAGE);
				} else {
					train = new Train(direction, wagons, row, column);
					trains.add(train);
					orderTrains();
					counterTrains++;
				}
			} 
			
			if(direction == 'A') {
				if((row + wagons) < 0) {
					JFrame frameOutUp = new JFrame();
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(frameOutUp, "One or more wagons are created outside the board.", 
							"Column Warning", JOptionPane.INFORMATION_MESSAGE);
				} else {
					train = new Train(direction, wagons, row, column);
					trains.add(train);
					orderTrains();
					counterTrains++;
				}
			} 
			
			if(direction == 'I') {
				if((column + wagons) < 0) {
					JFrame frameOutLeft = new JFrame();
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(frameOutLeft, "One or more wagons are created outside the board.", 
							"Row Warning", JOptionPane.INFORMATION_MESSAGE);
				} else {
					train = new Train(direction, wagons, row, column);
					trains.add(train);
					orderTrains();
					counterTrains++;
				}
			} 
			
			if(direction == 'D') {
				if((column - wagons) > 29) {
					JFrame frameOutRight = new JFrame();
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(frameOutRight, "One or more wagons are created outside the board.", 
							"Row Warning", JOptionPane.INFORMATION_MESSAGE);
				} else {
					train = new Train(direction, wagons, row, column);
					trains.add(train);
					orderTrains();
					counterTrains++;
				}
			} 
			
			viewCreateBoard.setTextFieldDirection();
			viewCreateBoard.setTextFieldWagons();
			viewCreateBoard.setTextFieldRow();
			viewCreateBoard.setTextFieldColumn();
			
			viewCreateBoard.drawTrain(trains);
			
			if(counterTrains == numberOfTrains) {
				JFrame frameMaximumTrains= new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameMaximumTrains, "You have reached the maximum amount of trains (" + numberOfTrains + ")", 
						"Maximum Trains Warning", JOptionPane.INFORMATION_MESSAGE);
				
				viewCreateBoard.getTextFieldDirection().setEnabled(false);
				viewCreateBoard.getTextFieldWagons().setEnabled(false);
				viewCreateBoard.getTextFieldRow().setEnabled(false);
				viewCreateBoard.getTextFieldColumn().setEnabled(false);
				viewCreateBoard.getButtonAdd().setEnabled(false);
			}
		}
		
		
		if (e.getActionCommand().equals("deleteTrain")) {
			this.viewCreateBoard.deleteTrain();
			
			if(counterTrains < MIN_TRAINS) {
				JFrame frameMaximumTrains= new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameMaximumTrains, "There are no trains to delete", 
						"Minimum Trains Warning", JOptionPane.INFORMATION_MESSAGE);
				
				viewCreateBoard.getTextFieldDirectionDelete().setEnabled(false);
				viewCreateBoard.getTextFieldWagonsDelete().setEnabled(false);
				viewCreateBoard.getTextFieldRowDelete().setEnabled(false);
				viewCreateBoard.getTextFieldColumnDelete().setEnabled(false);
				viewCreateBoard.getButtonDelete().setEnabled(false);
			}
		}
		
		
		if (e.getActionCommand().equals("buttonDelete")) {
			directionDelete = viewCreateBoard.getTextFieldDirectionDelete().getText().charAt(0);
			wagonsDelete = Integer.parseInt(viewCreateBoard.getTextFieldWagonsDelete().getText());
			rowDelete = rows - (Integer.parseInt(viewCreateBoard.getTextFieldRowDelete().getText())) -1;
			columnDelete = Integer.parseInt(viewCreateBoard.getTextFieldColumnDelete().getText());
			
			viewCreateBoard.deleteTrainFromTheBoard(trains);
			
			counterTrains--;

			viewCreateBoard.setTextFieldDirectionDelete();
			viewCreateBoard.setTextFieldWagonsDelete();
			viewCreateBoard.setTextFieldRowDelete();
			viewCreateBoard.setTextFieldColumnDelete();
			
			
			if(counterTrains < MIN_TRAINS) {
				JFrame frameNoMoreTrainsToDelete= new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameNoMoreTrainsToDelete, "There are no more trains to delete", 
						"Minimum Trains Warning", JOptionPane.INFORMATION_MESSAGE);
				
				viewCreateBoard.getTextFieldDirectionDelete().setEnabled(false);
				viewCreateBoard.getTextFieldWagonsDelete().setEnabled(false);
				viewCreateBoard.getTextFieldRowDelete().setEnabled(false);
				viewCreateBoard.getTextFieldColumnDelete().setEnabled(false);
				viewCreateBoard.getButtonDelete().setEnabled(false);
			}
		}
		
		
		if (e.getActionCommand().equals("instructions")) {
			viewCreateBoard.getInstructions();
		}
	}


	public void readFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			String[] row = new String[this.columns];
			int i = 0;
			while ((line = br.readLine()) != null) {  //mientras no sea nulo leemos
				if (i < this.rows) {  //tantas lineas como filas
					row = line.split(" ");
					for (int j = 0; j < this.columns; j++) {
						board[i][j] = Integer.parseInt(row[j]);
					}
					
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public Integer[][] getBoard() {
		return board;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public int getWagons() {
		return wagons;
	}
	
	public static void setWagons(int wagons) {
		ControllerCreateBoard.wagons = wagons;
	}
	
	public int getRow() {
		return row;
	}
	
	public static void setRow(int row) {
		ControllerCreateBoard.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public static void setColumn(int column) {
		ControllerCreateBoard.column = column;
	}
	public char getButtonDirection() {
		return directionButton;
	}
	
	public int getButtonRow() {
		return rowButton;
	}
	
	public int getButtonColumn() {
		return columnButton;
	}
	
	public char getDirectionDelete() {
		return directionDelete;
	}
	
	public int getWagonsDelete() {
		return wagonsDelete;
	}
	
	public int getRowDelete() {
		return rowDelete;
	}
	
	public int getColumnDelete() {
		return columnDelete;
	}
	
	private void orderTrains() {
		ArrayList<Train> orderedTrains = new ArrayList<Train>();
		
		for(int i = 0; i<trains.size(); i++) {
			if(trains.get(i).getDirection() == 'B') {
				orderedTrains.add(trains.get(i));
			}
		}

		for(int i = 0; i<trains.size(); i++) {
			if(trains.get(i).getDirection() == 'A') {
				orderedTrains.add(trains.get(i));
			}
		}

		for(int i = 0; i<trains.size(); i++) {
			if(trains.get(i).getDirection() == 'I') {
				orderedTrains.add(trains.get(i));
			}
		}

		for(int i = 0; i<trains.size(); i++) {
			if(trains.get(i).getDirection() == 'D') {
				orderedTrains.add(trains.get(i));
			}
		}

		trains = orderedTrains; 
	}
	
}
