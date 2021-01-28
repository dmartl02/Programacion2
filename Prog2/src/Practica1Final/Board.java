package Practica1Final;

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
			} else {
				System.out.print(counter + " ");
			}
			
			counter--;

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
		
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if(board[i][j] != 'X') {
					board[i][j] = '�';
				}
			}
		}
		
		for (int i = 0; i < trains.size() ; i++) {
			temp = trains.get(i);
			
			x = temp.getPosition().getX();
			y = temp.getPosition().getY();
			
			int z = 0;
		
			if ((temp.getDirection() == 'B') && (temp.getWagons() > 0)) {
				while (z < temp.getWagons()) {
					board[x-z][y] = 'B';
					z++;
				}
			}
			
			if((temp.getDirection() == 'A') && (temp.getWagons() > 0)) {
				while (z < temp.getWagons()) {
					board[x+z][y] = 'A';
					z++;
				}
			}

			if(temp.getDirection() == 'I') {
				while (z < temp.getWagons()) {
					board[x][y+z] = 'I';
					z++;
				}
			}
			
			if(temp.getDirection() == 'D') {
				while (z < temp.getWagons()) {
					board[x][y-z] = 'D';
					z++;
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
	
	public void setCataclismo(Position position) {
		int x = position.getX();
		int y = position.getY();
		
		board[x][y] = 'C';
	}
	
	
	private String getHeader() {
		StringBuilder output = new StringBuilder();
		output.append("   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\r\n");
		output.append("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
		return output.toString();
	}
}