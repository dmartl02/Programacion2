package EntregaExamen1;

import java.util.ArrayList;

public class Board {
	private final int FILAS = 30;
	private final int COLUMNAS = 30;
	private char[][] board = new char[FILAS][COLUMNAS];
	
	public Board() {
		create();
	}
		

	public void create() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				board[i][j] = '.';
			}
		}
	}

	
	public void print() {
		System.out.println(getHeader());
		int counter = 29;

		for (int i = 0; i < FILAS; i++) { 
			if(counter < 10) {
				System.out.print("0" + counter + " ");
			}else {
				System.out.print(counter + " ");
			}
			counter--;

			//System.out.print(i + " ");
	        for (int j = 0; j < COLUMNAS ; j++) {
	            System.out.print(board[i][j] + " ");
	        }
	        
	        System.out.println();
	    }
		
		System.out.println();
	}
	
	
	public void printWithTrains(ArrayList<Train> trains) {
		int x = 0;
		int y = 0;
		Train temp;
		
		for (int i = 0; i < trains.size() ; i++) {
			temp = trains.get(i);
			
			x = temp.getPosition().getX();
			y = temp.getPosition().getY();
			
			if(temp.getDirection() == 'B') {
				board[x][y] = 'B';
				int xTail = x - (temp.getWagons()-1);
				int yTail = y;
				board[xTail][yTail] = 'B';
				
				for(int j = 1; j<temp.getWagons(); j++) {
					board[x-temp.getWagons()+j][yTail] = 'B';
				}

			} else if(temp.getDirection() == 'A') {
				board[x][y] = 'A';
				int xTail = x + (temp.getWagons()-1);
				int yTail = y;
				board[xTail][yTail] = 'A';
				
				for(int j = 1; j<temp.getWagons(); j++) {
					board[x+temp.getWagons()-j][yTail] = 'A';
				}

			} else if(temp.getDirection() == 'I') {
				board[x][y] = 'I';
				int xTail = x;
				int yTail = y + (temp.getWagons()-1);
				board[xTail][yTail] = 'I';
				
				for(int j = 1; j<temp.getWagons(); j++) {
					board[x][yTail-temp.getWagons()+j] = 'I';
				}

			} else if(temp.getDirection() == 'D') {
				board[x][y] = 'D';
				int xTail = x;
				int yTail = y - (temp.getWagons()-1);
				board[xTail][yTail] = 'D';
				
				for(int j = 1; j<temp.getWagons(); j++) {
					board[x][yTail+temp.getWagons()-j] = 'D';
				}
			}		
		}
	}
	
	
	public char[][] getBoard() {
		return board;
	}


	public void setBoard(char[][] board) {
		this.board = board;
	}


	public void setCollision(Position position) {
		int x = position.getX();
		int y = position.getY();
		
		board[x][y] = 'X';
		
	}
	
	
	private String getHeader() {
		StringBuilder output = new StringBuilder();
		output.append("   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\r\n");
		output.append("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
		return output.toString();
	}
	
	

}