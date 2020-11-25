package Practica1Prog;

public class Trenecitos {
	
	public static void main(String[] args) {
		crearTablero();
	}
	
	
	
	public static void crearTablero() {
		String[][] tablero = new String[30][30];
		for (int i=0; i < 30; i++) {
			System.out.println();
			System.out.println();
			for (int j=0; j < 30; j++) {
				tablero[i][j] = "·";
				System.out.print (tablero[i][j]);
				if (j!=tablero[i].length-1) {
					System.out.print("\t");
				}
			}
		}
		
		System.out.println();
	}

}
