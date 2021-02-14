package Practica2Final;

import java.util.ArrayList;

import Practica2Final.Position;
import Practica2Final.Train;

public class Train {
	private char direction;
	private Position position;
	private int wagons;
	
	private int rows, columns;

	public Train(char direction, Position  position, int wagons) {
		this.direction = direction;
		this.position = position;
		this.wagons = wagons;

	}


	public char getDirection() {
		return direction;
	}


	public void setDirection(char direction) {
		this.direction = direction;
	}


	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
		this.position = position;
	}


	public int getWagons() {
		return wagons;
	}

	
	public void setWagons(int wagons) {
		this.wagons = wagons;
	}

	
	public void move(ViewCreateBoard viewCreateBoard) {
		int row = position.getRow();
		int column = position.getColumn();

		switch (direction) {
		case 'B':
			if (row == viewCreateBoard.getRows()-1) {
				wagons--;
			} else {
				position.setRow(row + 1);
			}

			break;

		case 'A':
			if (row == 1) {
				wagons--;
			} else {
				position.setRow(row - 1);
			}
			break;

		case 'I':
			if (column == 1) {
				wagons--;
			} else {
				position.setColumn(column - 1);
			}

			break;

		case 'D':
			if (column == viewCreateBoard.getColumns()-1) {
				wagons--;
			} else {
				position.setColumn(column + 1);
			}

			break;
		}
	}
	
	
	public void moveWhenCollision(ViewCreateBoard viewCreateBoard) {
		wagons--;
		
		switch (direction) {
		case 'B':
			position.setRow(position.getRow() + wagons);
			
			if(position.getRow() > viewCreateBoard.getRows()) {
				while((position.getRow() > viewCreateBoard.getRows()) && (wagons > 0)) {
					position.setRow(position.getRow() - 1);
					wagons--;
				}
			}
			
			break;
			
		case 'A':
			position.setRow(position.getRow() - wagons);
			
			if(position.getRow() < 0) {
				while((position.getRow() < 0) && (wagons > 0)) {
					position.setRow(position.getRow() + 1);
					wagons--;
				}
			}
			
			break;
			
		case 'I':
			position.setColumn(position.getColumn() - wagons);
			
			if(position.getColumn() < 0) {
				while((position.getColumn() < 0) && (wagons > 0)) {
					position.setColumn(position.getColumn() + 1);
					wagons--;
				}
			}
			
			break;
			
		case 'D':
			position.setColumn(position.getColumn() + wagons);
			
			if(position.getColumn() > viewCreateBoard.getColumns()) {
				while((position.getColumn() > viewCreateBoard.getColumns()) && (wagons > 0)) {
					position.setColumn(position.getColumn() - 1);
					wagons--;
				}
			}
			
			break;
		}
	}

	@Override
	public String toString() {
		return "Train [direction=" + direction + ", position=" + position + ", wagons=" + wagons + "]";
	}

}