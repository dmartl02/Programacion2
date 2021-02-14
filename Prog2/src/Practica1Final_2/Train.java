package Practica1Final_2;

import Practica1Final_2.Position;

import java.util.ArrayList;
import java.util.List;

import Practica1Final_2.Board;

public class Train {
	private char direction;
	private Position position;
	private int wagons;
	private ArrayList<Train> trains;
	private ArrayList<Train> subtrains;

	private Board board;
	private int rows, columns;

	public Train(char direction, Position  position, int wagons, Board board) {
		this.direction = direction;
		this.position = position;
		this.wagons = wagons;
		this.board = board;
		this.subtrains = new ArrayList<Train>();
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


	public ArrayList<Train> getSubtrains() {
		return subtrains;
	}


	public void setSubtrains(ArrayList<Train> subtrains) {
		this.subtrains = subtrains;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}


	public void checkInitialCollision() {
		int x = position.getX();
		int y = position.getY();

		rows = board.getRows();
		columns = board.getColumns();

		if (wagons == 0 && !subtrains.isEmpty()) {

		} else {
			String currentSituation = hasMyBodyCollidedString();

			if (currentSituation.contains("X") && board.getBoard()[x][y] != 'X') {
				createSubTrains(currentSituation);
			}

			boolean alreadyCollided = false;

			if (hasMyHeadCollidedInitial()) { 
				switch (direction) {
				case 'B':
					if(x < rows-1) {	
						position.setX(x-1);
					}
					break;

				case 'A':
					if(x > 0) {
						position.setX(x+1);
					}
					break;

				case 'I':
					if(y > 0) {
						position.setY(y+1);
					}
					break;

				case 'D':
					if(y < columns-1) {
						position.setY(y-1);
					}
					break;
				}

				wagons--;
				alreadyCollided = true;

			} else if (alreadyCollided) {
				wagons--;	
			}
		}		
	}

	/*
	public boolean checkInitialCollisionHeads(ArrayList<Train> trains) {
		int x = position.getX();
		int y = position.getY();
		
		Train temp = this;
		int x_temp = this.getPosition().getX();
		int y_temp = this.getPosition().getY();


		for(int i = 0; i<trains.size(); i++) {
			if ((x == x_temp) && (y == y_temp)) {
				return true;
			}
		}

		return false;
	}
	*/
	
	public void move() {
		int x = position.getX();
		int y = position.getY();

		rows = board.getRows();
		columns = board.getColumns();

		if (wagons == 0 && !subtrains.isEmpty()) {

		} else {
			String currentSituation = hasMyBodyCollidedString();

			if (currentSituation.contains("X") && board.getBoard()[x][y] != 'X') {
				createSubTrains(currentSituation);
			}

			boolean alreadyCollided = false;

			if (hasMyHeadCollided() && wagons > 1) {  //TODO TODO añadir && wagons > 1
				switch (direction) {
				case 'B':
					if(x < rows-1 && wagons > 1) {	
						position.setX(x-1);
					} 

					break;

				case 'A':
					if(x > 0 && wagons > 1) { //TODO TODO añadir && wagons > 1
						position.setX(x+1);
					}
					break;

				case 'I':
					if(y > 0 && wagons > 1) { //TODO TODO añadir && wagons > 1
						position.setY(y+1);
					}
					break;

				case 'D':
					if(y < columns-1 && wagons > 1) { //TODO TODO añadir && wagons > 1
						position.setY(y-1);
					}
					break;
				}

				wagons--;
				alreadyCollided = true;

			}

			//TODO AÑADIR ESTE IF EN LA MOD
			if(hasMyHeadCollided() && wagons == 1) {
				alreadyCollided = true;
			}

			if(willMyHeadCollide() && !alreadyCollided) {
				Position collision = null;

				switch (direction) {
				case 'B':
					collision = new Position(x+1, y);
					break;

				case 'A':		
					collision = new Position(x-1, y);
					break;

				case 'I':
					collision = new Position(x, y-1);
					break;

				case 'D':
					collision = new Position(x, y+1);
					break;
				}

				board.setCollision(collision);
				wagons--;

			} else if (alreadyCollided) {
				wagons--;	
			} else {
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
		}

		if(!subtrains.isEmpty()) {
			for (Train train : subtrains) {
				train.move();
			}
		}
	}

	private boolean hasMyHeadCollidedInitial() {
		int x = position.getX();
		int y = position.getY();

		switch (direction) {
		case 'B':
			if(x < rows-1) {	
				if (board.getBoard()[x][y] != 'B') {
					position.setX(x-1);
					return true;
				}
			}

			break;

		case 'A':
			if(x > 0) {
				if (board.getBoard()[x][y] != 'A') {
					position.setX(x+1);
					return true;
				}
			}
			break;

		case 'I':
			if(y > 0) {
				if (board.getBoard()[x][y] != 'I') {
					position.setY(y+1);
					return true;
				}
			}

			break;

		case 'D':
			if(y < columns-1) {
				if (board.getBoard()[x][y] != 'D') {
					position.setY(y-1);
					return true;
				}
			}

			break;
		}

		return false;
	}

	private boolean hasMyHeadCollided() {
		int x = position.getX();
		int y = position.getY();
		//TODO AÑADIR IF ELSE EN LA MOD
		if(board.getBoard()[x][y] == 'X' && wagons == 1) {
			return true;

		} else {
			switch (direction) {
			case 'B':
				if(x < rows-1) {
					if (board.getBoard()[x][y] == 'X') {
						position.setX(x-1);
						return true;
					}
				}

				break;

			case 'A':
				if(x > 0) {
					if (board.getBoard()[x][y] == 'X') {
						position.setX(x+1);
						return true;
					}
				}
				break;

			case 'I':
				if(y > 0) {
					if (board.getBoard()[x][y] == 'X') {
						position.setY(y+1);
						return true;
					}
				}

				break;

			case 'D':
				if(y < columns-1) {
					if (board.getBoard()[x][y] == 'X') {
						position.setY(y-1);
						return true;
					}

				}

				break;
			}
		}

		return false;
	}

	/*
	public boolean checkCollisionWithAnotherHead(ArrayList<Train> trains) {
		int x = position.getX();
		int y = position.getY();

		Train temp = this;
		int x_temp = temp.getPosition().getX();
		int y_temp = temp.getPosition().getY();

		
		for(int i = 0; i<trains.size(); i++) {
			if ((x == x_temp) && (y == y_temp)) {
				return true;
			}
		}

		return false;
	}
	*/

	private boolean willMyHeadCollide() {
		int x = position.getX();
		int y = position.getY();

		switch (direction) {
		case 'B':
			if(x < rows-1) {	
				if (board.getBoard()[x][y] == 'X') {
					position.setX(x-1);
					return true;
				}

				if(board.getBoard()[x+1][y] != '.') {
					return true;
				}
			}

			break;

		case 'A':
			if(x > 0) {
				if (board.getBoard()[x][y] == 'X') {
					position.setX(x+1);
					return true;
				}

				if(board.getBoard()[x-1][y] != '.'){
					return true;
				}
			}

			break;

		case 'I':
			if(y > 0) {
				if (board.getBoard()[x][y] == 'X') {
					position.setY(y+1);
					return true;
				}

				if(board.getBoard()[x][y-1] != '.'){
					return true;
				}
			}

			break;

		case 'D':
			if(y < columns-1) {
				if (board.getBoard()[x][y] == 'X') {
					position.setY(y-1);
					return true;
				}

				if(board.getBoard()[x][y+1] != '.'){
					return true;
				}
			}

			break;
		}

		return false;
	}


	private String hasMyBodyCollidedString() {
		StringBuilder output = new StringBuilder();

		Position head = getPosition();
		int x = head.getX();
		int y = head.getY();

		for(int i = 0; i<wagons; i++) {
			output.append(board.getBoard()[x][y]);

			switch(direction) {
			case 'B':
				x--;
				break;

			case 'A':
				x++;
				break;

			case 'I':
				y++;
				break;

			case 'D':
				y--;
				break;
			}
		}

		return output.toString();
	}


	private void createSubTrains(String collisions) {
		String[] subTrains = collisions.split("X");
		Train aux = null;
		Position posAux = null;

		int x = position.getX();
		int y = position.getY();

		for (int i = 0; i < subTrains.length; i++) {
			if (i == 0) {
				posAux = position;
				wagons = subTrains[i].length(); 
			} else {
				switch(direction) {
				case 'B':
					x = x - wagons - 1;
					break;
				case 'A':
					x = x + wagons + 1;

					break;
				case 'I':
					y = y + wagons + 1;

					break;
				case 'D':
					y = y - wagons - 1 ;

					break;
				}

				posAux = new Position(x,y);
				aux = new Train(direction, posAux, subTrains[i].length(), board);
				aux.setColumns(columns);
				aux.setRows(rows);
				subtrains.add(aux);
			}
		}	
	}



	@Override
	public String toString() {
		return "Train [direction=" + direction + ", position=" + position + ", wagons=" + wagons + "]";
	}



}