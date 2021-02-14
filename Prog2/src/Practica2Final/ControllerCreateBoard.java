package Practica2Final;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private ArrayList<Train> trains;

	private char[][] board;
	private int numberOfTrains, rows, columns;
	private char directionDelete;
	private int wagonsDelete, rowDelete, columnDelete;
	private static char directionButton;
	private static Train train;
	private final int MIN_TRAINS = 1;
	private static int counterTrains = 0;;
	private boolean makeMove;
	private int counterWagons = 0;

	public ControllerCreateBoard(int numberOfTrains, int rows, int columns) {
		this.numberOfTrains = numberOfTrains;
		this.rows = rows+1;
		this.columns = columns+1;
		this.initializeBoard();

		this.viewCreateBoard = new ViewCreateBoard(this, rows, columns);

		trains = new ArrayList<Train>(numberOfTrains);
	}


	public void initializeBoard() {
		board = new char[this.rows][this.columns];

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				board[i][j] = 'W';
			}
		}

		for (int i = 1; i < this.rows; i++) {
			for (int j = 1; j < this.columns; j++) {
				board[i][j] = '·';
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("createNewSimulation")) {
			counterTrains = 0;
			new ControllerMenuCreateBoard();
		}

		if (e.getActionCommand().equals("openSimulation")) {
			JFileChooser explorer = new JFileChooser();
			explorer.showOpenDialog(explorer);
			File file = explorer.getSelectedFile();
			this.readFile(file);
			viewCreateBoard.modifyBoard();
		}

		if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}

		if (makeMove) {
			String[] indexes = new String[2];
			indexes = e.getActionCommand().split(",");
			int rowIndex = Integer.parseInt(indexes[0]);
			int columnIndex = Integer.parseInt(indexes[1]);

			if (e.getActionCommand().equals(indexes[0] + "," + indexes[1])) {
				runManual(rowIndex, columnIndex);
			}

			makeMove = false;
		}

		if (e.getActionCommand().equals("makeMove")) {

			if (counterTrains < MIN_TRAINS) {
				JFrame noTrainsToMove = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(noTrainsToMove, "There are no trains to move", "Minimum Trains Warning",
						JOptionPane.INFORMATION_MESSAGE);
			} else if(trains.isEmpty()) {
				JFrame noTrainsToMove = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(noTrainsToMove, "There are no trains to move", "Minimum Trains Warning",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				makeMove = true;
			}
		}

		if (e.getActionCommand().equals("addTrain")) {
			this.viewCreateBoard.addNewTrain();

			if (counterTrains == numberOfTrains) {
				JFrame frameNoMoreTrainsToAdd = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameNoMoreTrainsToAdd, "You can not add more trains.",
						"Maximum Trains Warning", JOptionPane.INFORMATION_MESSAGE);

				viewCreateBoard.getTextFieldDirection().setEnabled(false);
				viewCreateBoard.getTextFieldWagons().setEnabled(false);
				viewCreateBoard.getTextFieldColumn().setEnabled(false);
				viewCreateBoard.getTextFieldRow().setEnabled(false);
				viewCreateBoard.getButtonAdd().setEnabled(false);
			}
		}

		if (e.getActionCommand().equals("buttonAdd")) {
			addTrain();
		}

		if (e.getActionCommand().equals("deleteTrain")) {
			this.viewCreateBoard.deleteTrain();

			if (counterTrains < MIN_TRAINS) {
				JFrame frameMaximumTrains = new JFrame();
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
			rowDelete = rows - (Integer.parseInt(viewCreateBoard.getTextFieldRowDelete().getText())) - 1;
			columnDelete = Integer.parseInt(viewCreateBoard.getTextFieldColumnDelete().getText())  +1 ;

			viewCreateBoard.deleteTrainFromTheBoard();

			counterTrains--;

			viewCreateBoard.setTextFieldDirectionDelete();
			viewCreateBoard.setTextFieldWagonsDelete();
			viewCreateBoard.setTextFieldRowDelete();
			viewCreateBoard.setTextFieldColumnDelete();

			if (counterTrains < MIN_TRAINS) {
				JFrame frameNoMoreTrainsToDelete = new JFrame();
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

		if (e.getActionCommand().equals("run")) {
			runAutomatic();
		}

		if (e.getActionCommand().equals("instructions")) {
			viewCreateBoard.getInstructions();
		}
		
		if (e.getActionCommand().equals("modificacionPractica")) {
			JFrame frameVerticalWagons = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameVerticalWagons, "There are " +counterWagons+ " vertical wagons",
					"Vertical Wagons Counter", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	public void addTrain() {		
		char direction = viewCreateBoard.getTextFieldDirection().getText().charAt(0);
		int wagons = Integer.parseInt(viewCreateBoard.getTextFieldWagons().getText());
		int column = Integer.parseInt(viewCreateBoard.getTextFieldColumn().getText()) + 1;
		int row = rows - (Integer.parseInt(viewCreateBoard.getTextFieldRow().getText())) - 1;
		
		Position position = new Position(row, column);

		if ((direction != 'B') && (direction != 'A') && (direction != 'D') && (direction != 'I')) {
			JFrame frameDirection = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameDirection, "The direction must be 'B' , 'A' , 'I' or 'D'",
					"Direction Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if ((wagons < 0) || (wagons > rows) || (wagons > columns)) {
			JFrame frameWagons = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameWagons,
					"The number of wagons must be greater than 0 and smaller or equal to the number of rows / columns.",
					"Wagons Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if (row > rows) {
			JFrame frameRow = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameRow,
					"The initial row must be equal or smaller than the number of rows (" + rows + ")", "Row Warning",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (column > columns) {
			JFrame frameColumn = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameColumn,
					"The initial column must be equal or smaller than the number of columns (" + columns + ")",
					"Column Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if (direction == 'B') {
			if ((row - wagons) > rows) {
				JFrame frameOutDown = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutDown, "One or more wagons are created outside the board.",
						"Column Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
				counterWagons = counterWagons + wagons;
			}
		}

		if (direction == 'A') {
			if ((row + wagons) < 0) {
				JFrame frameOutUp = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutUp, "One or more wagons are created outside the board.",
						"Column Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
				counterWagons = counterWagons + wagons;
			}
		}

		if (direction == 'I') {
			if ((column + wagons) < 0) {
				JFrame frameOutLeft = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutLeft, "One or more wagons are created outside the board.",
						"Row Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		if (direction == 'D') {
			if ((column - wagons) > 29) {
				JFrame frameOutRight = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutRight, "One or more wagons are created outside the board.",
						"Row Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		viewCreateBoard.setTextFieldDirection();
		viewCreateBoard.setTextFieldWagons();
		viewCreateBoard.setTextFieldColumn();
		viewCreateBoard.setTextFieldRow();
		
		viewCreateBoard.drawTrain();

		if (counterTrains == numberOfTrains) {
			JFrame frameMaximumTrains = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameMaximumTrains,
					"You have reached the maximum amount of trains (" + numberOfTrains + ")", "Maximum Trains Warning",
					JOptionPane.INFORMATION_MESSAGE);

			viewCreateBoard.getTextFieldDirection().setEnabled(false);
			viewCreateBoard.getTextFieldWagons().setEnabled(false);
			viewCreateBoard.getTextFieldRow().setEnabled(false);
			viewCreateBoard.getTextFieldColumn().setEnabled(false);
			viewCreateBoard.getButtonAdd().setEnabled(false);
		}

	}


	public void addTrainFromFile(char direction_prmt, Position position_prmt, int wagons_prmt) {
		char direction = direction_prmt;
		int wagons = wagons_prmt;
		int row = rows - (position_prmt.getRow()) -1;
		int column = position_prmt.getColumn();
		Position position = new Position(row, column);

		if ((direction != 'B') && (direction != 'A') && (direction != 'D') && (direction != 'I')) {
			JFrame frameDirection = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameDirection, "The direction must be 'B' , 'A' , 'I' or 'D'",
					"Direction Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if ((wagons < 0) || (wagons > rows) || (wagons > columns)) {
			JFrame frameWagons = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameWagons,
					"The number of wagons must be greater than 0 and smaller or equal to the number of rows / columns.",
					"Wagons Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if (row > rows) {
			JFrame frameRow = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameRow,
					"The initial row must be equal or smaller than the number of rows (" + rows + ")", "Row Warning",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (column > columns) {
			JFrame frameColumn = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameColumn,
					"The initial column must be equal or smaller than the number of columns (" + columns + ")",
					"Column Warning", JOptionPane.INFORMATION_MESSAGE);
		}

		if (direction == 'B') {
			if ((row - wagons) > rows) {
				JFrame frameOutDown = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutDown, "One or more wagons are created outside the board.",
						"Column Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		if (direction == 'A') {
			if ((row + wagons) < 0) {
				JFrame frameOutUp = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutUp, "One or more wagons are created outside the board.",
						"Column Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		if (direction == 'I') {
			if ((column + wagons) < 0) {
				JFrame frameOutLeft = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutLeft, "One or more wagons are created outside the board.",
						"Row Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		if (direction == 'D') {
			if ((column - wagons) > 29) {
				JFrame frameOutRight = new JFrame();
				UIManager.put("OptionPane.okButtonText", "OK");
				JOptionPane.showMessageDialog(frameOutRight, "One or more wagons are created outside the board.",
						"Row Warning", JOptionPane.INFORMATION_MESSAGE);
			} else {
				train = new Train(direction, position, wagons);
				trains.add(train);
				orderTrains();
				counterTrains++;
			}
		}

		viewCreateBoard.drawTrain();

		if (counterTrains == numberOfTrains) {
			JFrame frameMaximumTrains = new JFrame();
			UIManager.put("OptionPane.okButtonText", "OK");
			JOptionPane.showMessageDialog(frameMaximumTrains,
					"You have reached the maximum amount of trains (" + numberOfTrains + ")", "Maximum Trains Warning",
					JOptionPane.INFORMATION_MESSAGE);

			/*viewCreateBoard.getTextFieldDirection().setEnabled(false);
				viewCreateBoard.getTextFieldWagons().setEnabled(false);
				viewCreateBoard.getTextFieldRow().setEnabled(false);
				viewCreateBoard.getTextFieldColumn().setEnabled(false);
				viewCreateBoard.getButtonAdd().setEnabled(false);*/
		}

	}


	public void readFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;

			int rows = 0, columns = 0, counter = 1;
			String[] boardDimension = new String[2];

			String[] trains = new String[1];
			int numberOfTrains = 0;

			ControllerCreateBoard cb = null;

			String[] trainParametres = new String[4];
			char direction = 0;
			int wagons = 0;
			Position position = null;

			String[] collisions = new String[1];
			int numberOfCollisions = 0;

			String[] collisionPosition = new String[2];
			int collisionRow = 0, collisionColumn = 0;


			while ((line = br.readLine()) != null) { //Read while not null
				if (counter == 1) { // Board size
					boardDimension = line.split(" "); 
					rows = Integer.parseInt(boardDimension[0]);
					columns = Integer.parseInt(boardDimension[1]);

				} else if (counter == 2) { // Number of trains
					trains = line.split(" ");
					numberOfTrains = Integer.parseInt(trains[0]);
					cb = new ControllerCreateBoard(numberOfTrains, rows, columns);

				} else { 
					trainParametres = line.split(" "); 
					direction = trainParametres[0].charAt(0);
					wagons = Integer.parseInt(trainParametres[1]);
					int column = Integer.parseInt(trainParametres[2]) + 1;
					int row = Integer.parseInt(trainParametres[3]);
					//position = new Position(Integer.parseInt(trainParametres[3]), Integer.parseInt(trainParametres[2]));
					position = new Position(row, column);
					cb.addTrainFromFile(direction, position, wagons);
				}


				/*
					if(counter == numberOfTrains + 2) {
						collisions = line.split(" ");
						numberOfCollisions = Integer.parseInt(collisions[0]);

						for(int i = counter; i<=counter+numberOfCollisions; i++) {
							collisionPosition = line.split(" ");
							collisionRow = Integer.parseInt(collisions[0]);
							collisionColumn = Integer.parseInt(collisions[0]);
						}
					} */

				counter++;
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


	private void orderTrains() {
		ArrayList<Train> orderedTrains = new ArrayList<Train>();

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'B') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'A') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'I') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'D') {
				orderedTrains.add(trains.get(i));
			}
		}

		trains = orderedTrains;
	}


	public void runAutomatic() {
		int index = 0;
		boolean crashed = false;
		int maximumTrains = 0;
		Position collision;
		Train temp;

		orderTrains();
		maximumTrains = trains.size();

		while (!trains.isEmpty()) {
			temp = trains.get(index);

			collision = new Position(temp.getPosition().getRow(), temp.getPosition().getColumn());
			crashed = detectCollision(index);

			if (crashed) {
				viewCreateBoard.paintCollision(collision);
			}

			temp.move(viewCreateBoard);

			if (temp.getWagons() <= 0) {
				trains.remove(temp);
				maximumTrains = trains.size();

			} else {
				index++;
			}

			if (index == maximumTrains) {
				index = 0;
			}

			viewCreateBoard.drawTrain();

			try {
				Thread.sleep(100);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void runManual(int row, int column) {
		int index = 0;
		boolean crashed = false;
		int maximumTrains = 0;
		Position collision;

		orderTrains();
		maximumTrains = trains.size();

		viewCreateBoard.moveWhenClicked(row, column);

		for (int i = 0; i < trains.size(); i++) {
			if ((row == trains.get(i).getPosition().getRow()) && (column == trains.get(i).getPosition().getColumn())) {
				index = i;
			}
		}

		collision = new Position(getTrains().get(index).getPosition().getRow(), getTrains().get(index).getPosition().getColumn());
		crashed = detectCollision(index);

		if (crashed) {
			viewCreateBoard.paintCollision(collision);
		}

		if (trains.get(index).getWagons() <= 0) {
			trains.remove(trains.get(index));
			maximumTrains = trains.size();

		} else {
			index++;
		}

		if (index == maximumTrains) {
			index = 0;
		}
	}


	public boolean detectCollisionNoMoving(int index) {
		Train temp;
		Position movingTrain = trains.get(index-1).getPosition();

		for(int i = 0; i < trains.size(); i++) {
			if(i != index) {
				temp = trains.get(i);

				if(thereIsATrain(movingTrain, temp)) {
					return true;
				}
			}
		}

		return false;
	}


	public boolean detectCollision(int index) {
		Train temp;
		Train movingTrain = trains.get(index);

		for (int i = 0; i < trains.size(); i++) {
			if (i != index) {
				temp = trains.get(i);

				if (thereIsATrain(movingTrain.getPosition(), temp)) {

					movingTrain.moveWhenCollision(viewCreateBoard);
					temp.moveWhenCollision(viewCreateBoard);

					return true;
				}
			}
		}

		/*
			if (thereIsCollision(movingTrain.getPosition())) {
				movingTrain.moveWhenCollision(viewCreateBoard);
				return true;
			}
			/**/

		return false;
	}


	private boolean thereIsATrain(Position movingTrain, Train current) {
		char currentDirection = current.getDirection();
		int currentWagons = current.getWagons();
		Position currentPosition = current.getPosition();

		int currentX = currentPosition.getRow();
		int currentY = currentPosition.getColumn();

		int movingTrainX = movingTrain.getRow();
		int movingTrainY = movingTrain.getColumn();

		for (int i = 0; i < currentWagons; i++) {
			if ((currentX == movingTrainX) && (currentY == movingTrainY)) {
				currentPosition.setRow(movingTrainX);
				currentPosition.setColumn(movingTrainY);
				return true;
			}

			switch (currentDirection) {
			case 'B':
				currentX--;
				break;

			case 'A':
				currentX++;
				break;

			case 'I':
				currentY++;
				break;

			case 'D':
				currentY--;
				break;
			}
		}

		return false;
	}


	private boolean thereIsCollision(Position position) {
		if (viewCreateBoard.getButtonsBoard()[position.getRow()][position.getColumn()].getText().charAt(0) == 'X') {
			return true;
		}

		return false;
	}


	public ArrayList<Train> getTrains() {
		return trains;
	}

	public void setTrains(ArrayList<Train> trains) {
		this.trains = trains;
	}

	public char[][] getBoard() {
		return board;
	}

	public char getButtonDirection() {
		return directionButton;
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
}