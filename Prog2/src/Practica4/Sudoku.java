package Practica4;

import java.util.Scanner;

public class Sudoku {
	public static final int NFILAS = 3 ; //Tamaño bloque
	public static final int NCOLS = 3 ;
	public static final int TAMSUDOKU = NFILAS * NCOLS; 
	
	static int nodosVisitados = 0;
	static int nodosTotales = 0;
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] sudoku = new int[TAMSUDOKU][TAMSUDOKU];
		
		// Inicializo el sudoku
		for (int i=0; i < TAMSUDOKU; i++) {
			for (int j=0; j < TAMSUDOKU; j++) {
				sudoku[i][j] = sc.nextInt();
			}
		}
			
		sc.close();
		
		boolean[] esFinal = { false };
		
		//Validamos el sudoku antes de resolverlo porque ResuelveSudoku requiere
		//un sudoku consistente
		
		for (int i=0; i < TAMSUDOKU; i++) {
			for (int j=0; j < TAMSUDOKU; j++) {
				if (sudoku[i][j] != 0 && !spSudoku(i, j, sudoku)){
				    System.out.println("Sudoku sin solución.");
				    return;
				}
			}
		}
		
		if (ResuelveSudoku(0, 0, sudoku, esFinal)){
			System.out.println(toString(sudoku));
			System.out.println(nodosVisitados + " nodos visitados de " + nodosTotales + "nodos totales.\n");
		} else
			System.out.println("Sudoku sin solución");
	
		sc.close();
	}

	
	/**
	 * Convierte un sudoku a una cadena, para darlo por la salida estándar.
	 * Sigue el convenio de denominar toString a este método (ver class Object)
	 * @param sudoku
	 * @return
	 */
	
	public static String toString(int[][] sudoku) {
		StringBuffer result = new StringBuffer((2*sudoku[0].length+1)*sudoku.length+1);
		
		for (int i=0; i<sudoku.length; i++){
			for (int j=0; j<sudoku[0].length; j++) {
				result.append(""+sudoku[i][j]+" ");
			}
			
		    result.append('\n');
		}
		
		return result.toString();
	}

	
	/**
	 * Resuelve un sudoku comenzando a rellenar en la casilla fila x col . Sigue un diseño por backtraking 
	 * 
	 * @param fila: la fila de la casilla que toca resolver. 0 <= fila < TAMSUDOKU 
	 * @param columna:  la columna  de la casilla que toca resolver.  0 <= col < TAMSUDOKU
	 * @param sudoku: el sudoku a resolver. sudoku es consistente
	 * @param esFinal: si ya he encontrado una solución a sudoku. esFinal[0] == esSolucionFinal(sudoku). Observar que
	 * se requiere el array para poder cambiar el valor de la variable booleana.
	 * @return  devuelve true si y sólo si se encuentra una solución para sudoku a partir de la posición (fila,col)
	 */
	
	public static boolean ResuelveSudoku(int fila, int columna, int[][] sudoku, boolean[] esFinal) { 
		int numeroColumna = columna; 
		int numeroFila = fila;
	
		
		if (sudoku[fila][columna] != 0){
				if (fila == TAMSUDOKU-1 && columna == TAMSUDOKU-1){
					esFinal[0] = true;
					return true;
				} 
				
				if (columna == TAMSUDOKU-1){
					numeroColumna = 0;
					numeroFila = numeroFila+1;
				} else {
					numeroColumna=numeroColumna+1;
				}
				
				return ResuelveSudoku(numeroFila,numeroColumna, sudoku,esFinal);
				
		} else {
			for (int i=1; i <= 9 && !esFinal[0]; i++){
				sudoku[fila][columna] = i;
				
				
				if (spSudoku(fila, columna, sudoku)) {
					if (fila == TAMSUDOKU-1 && columna == TAMSUDOKU-1){
						esFinal[0] = true;
						return true;
					}
					
					if (columna == TAMSUDOKU-1){
						numeroColumna = 0;
						numeroFila = fila+1;
					} else {
						numeroColumna = columna+1;
					}
					
					if (ResuelveSudoku(numeroFila, numeroColumna, sudoku, esFinal)){
						esFinal[0] = true;
						return true;
					}	
				}
			}
			
			sudoku[fila][columna] = 0;
		}
		
		return false;
	}

	
	/** Método que indica si un sudoku es consistente (no tiene conflicto) en una casilla dada. El sudoku
	 * debe ser consistente en todas las casillas exceptuando la que queremos comprobar.
	 * 
	 * Precondición: sudoku consistente salvo quizá en sudoku[fila][col] y sudoku[fila][col] != 0
	 * 
	 * @param fila. fila de la casilla donde puede haber una inconsistencia. 0 <= fila < NFILAS*NCOLS 
	 * @param columna columna de la casilla donde puede haber una inconsistencia.  0 <= col < NFILAS*NCOLS
	 * @param sudoku es consistente sin considerar la casilla (fila, col). sudoku[fila][col] != 0
	 * @return true si y sólo si sudoku es consistente
	 */
	
	public static boolean spSudoku(int fila, int columna, int[][] sudoku) {
	
	// Conflicto por fila o columna
		for (int i=0; i<TAMSUDOKU;i++){
			if(sudoku[fila][i] == sudoku[fila][columna] && i != columna ) {
				return false;
			}
			
			if(sudoku[i][columna] == sudoku[fila][columna] && i != fila ) {
				return false;
			}
		}
	
		// Conflicto por bloque
		int fb = fila / NFILAS;
		int cb = columna / NCOLS;
		
		for (int i=0; i < NFILAS; i++) {
			for (int j=0; j<NCOLS; j++){
				int f = NFILAS*fb + i;
				int c = NCOLS*cb + j;
				
				if(sudoku[f][c] == sudoku[fila][columna] && f != fila && c != columna ) {
					return false;
				}
			}
		}
	
	return true;
	}

}
