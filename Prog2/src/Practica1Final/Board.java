package Practica1Final;


public class Board {
	
	private final int FILAS = 30;
	private final int COLUMNAS = 30;
	private char[][] tablero = new char[FILAS][COLUMNAS];
	
	public Board() {
		create();
	}
		


	public void create() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tablero[i][j] = '.';
			}
		}
	}

	public void print() {
		for (int i = 0; i < FILAS; i++) { 
	        for (int j = 0; j < COLUMNAS ; j++) {
	            System.out.print(tablero[i][j] + " ");
	        }
	        
	        System.out.println();
	    }
	}

}
