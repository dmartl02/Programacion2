package Practica1Final_2;

import Practica1Final_2.Position;
import Practica1Final_2.Board;

public class Train {
	private char direction;
	private Position position;
	private int wagons;
	
	private Board board;
	private int rows, columns;
	
	public Train(char direction, Position  position, int wagons, Board board) {
		this.direction = direction;
		this.position = position;
		this.wagons = wagons;
		this.board = board;
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

	
	public void move() {
		int x = position.getX();
		int y = position.getY();
		
		rows = board.getRows();
		columns = board.getColumns();
		
		switch (direction) {
		case 'B':
			if (x == rows-1) {
				wagons--;
			} else {
				position.setX(x + 1);
			}

			break;

		case 'A':
			if (x == 0) {
				wagons--;
			} else {
				position.setX(x - 1);
			}
			break;

		case 'I':
			if (y == 0) {
				wagons--;
			} else {
				position.setY(y - 1);
			}

			break;

		case 'D':
			if (y == columns-1) {
				wagons--;
			} else {
				position.setY(y + 1);
			}

			break;
		}
	}

	
	public void moveWhenCollision() { 
		wagons--;
		
		rows = board.getRows();
		columns = board.getColumns();
		
		switch (direction) {
		case 'B':
			position.setX(position.getX() + wagons);

			if(position.getX() > rows-1) {
				while((position.getX() > rows-1) && (wagons > 0)) {
					position.setX(position.getX() - 1);
					wagons--;
				}
			}

			break;

		case 'A':
			position.setX(position.getX() - wagons);

			if(position.getX() < 0) {
				while((position.getX() < 0) && (wagons > 0)) {
					position.setX(position.getX() + 1);
					wagons--;
				}
			}

			break;

		case 'I':
			position.setY(position.getY() - wagons);

			if(position.getY() < 0) {
				while((position.getY() < 0) && (wagons > 0)) {
					position.setY(position.getY() + 1);
					wagons--;
				}
			}

			break;

		case 'D':
			position.setY(position.getY() + wagons);

			if(position.getY() > columns-1) {
				while((position.getY() > columns-1) && (wagons > 0)) {
					position.setY(position.getY() - 1);
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