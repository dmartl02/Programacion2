package Practica1Prog;

public class Tablero extends Main {
	final static int FILAS = 30;
	final static int COLUMNAS = 30;
	static String[][] tablero = new String[FILAS][COLUMNAS];
	int coordenadaXFinal;
	int coordenadaYFinal;
	
	public Tablero() {
		
	}
	
	
	public void crearTablero() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tablero[i][j] = "·";
			}
		}
	}
	
	public void mostrarTablero() {
		for (int i = 0; i < tablero.length; i++) { 
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            
            System.out.println();
        }
	}
	
	
	public void aniadirTrenAlTablero(Tren tren) {
		int direccion = tren.getDireccion();
		int longitud = tren.getLongitud();
		int coordenadaXInicio = tren.getCoordenadaX();
		int coordenadaYInicio = tren.getCoordenadaY();
		//int coordenadaXFinal;
		//int coordenadaYFinal;
		
		if(direccion == 18) {
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(0);
			coordenadaXFinal = coordenadaXInicio - (longitud-1);
			coordenadaYFinal = coordenadaYInicio;
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(0);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio-longitud+i][coordenadaYFinal] = Integer.toString(0);;
			}

		} else if(direccion == 17) {
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(1);
			coordenadaXFinal = coordenadaXInicio + (longitud-1);
			coordenadaYFinal = coordenadaYInicio;
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(1);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio+longitud-i][coordenadaYFinal] = Integer.toString(1);;
			}

		} else if(direccion == 25) {
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(2);
			coordenadaXFinal = coordenadaXInicio;
			coordenadaYFinal = coordenadaYInicio + (longitud-1);
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(2);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio][coordenadaYFinal-longitud+i] = Integer.toString(2);;
			}

		} else if(direccion == 20) {
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(3);
			coordenadaXFinal = coordenadaXInicio;
			coordenadaYFinal = coordenadaYInicio - (longitud-1);
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(3);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio][coordenadaYFinal+longitud-i] = Integer.toString(3);;
			}
		}	
		
		//mostrarTablero();
		//System.out.println("\n");
	}
	
	
	public void moverTren(Tren tren) {
		int direccion = tren.getDireccion();
		int longitud = tren.getLongitud();
		int coordenadaXInicio = tren.getCoordenadaX();
		int coordenadaYInicio = tren.getCoordenadaY();
		//int coordenadaXFinal;
		//int coordenadaYFinal;
		
		tablero[coordenadaXFinal][coordenadaYFinal] = "·";
		
		if(direccion == 18) {
			coordenadaXInicio = tren.setCoordenadaX(coordenadaXInicio+1);
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(0);
			coordenadaXFinal = coordenadaXInicio - (longitud-1);
			coordenadaYFinal = coordenadaYInicio;
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(0);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio-longitud+i][coordenadaYFinal] = Integer.toString(0);;
			}
	
		} else if(direccion == 17) {
			coordenadaXInicio = tren.setCoordenadaX(coordenadaXInicio-1);
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(1);
			coordenadaXFinal = coordenadaXInicio + (longitud-1);
			coordenadaYFinal = coordenadaYInicio;
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(1);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio+longitud-i][coordenadaYFinal] = Integer.toString(1);;
			}

		} else if(direccion == 25) {
			coordenadaYInicio = tren.setCoordenadaY(coordenadaYInicio-1);
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(2);
			coordenadaXFinal = coordenadaXInicio;
			coordenadaYFinal = coordenadaYInicio + (longitud-1);
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(2);
			
			for(int i = 1; i<longitud; i++) { 
				tablero[coordenadaXInicio][coordenadaYFinal-longitud+i] = Integer.toString(2);;
			}

		} else if(direccion == 20) {
			coordenadaYInicio = tren.setCoordenadaY(coordenadaYInicio+1);
			tablero[coordenadaXInicio][coordenadaYInicio] = Integer.toString(3);
			coordenadaXFinal = coordenadaXInicio;
			coordenadaYFinal = coordenadaYInicio - (longitud-1);
			tablero[coordenadaXFinal][coordenadaYFinal] = Integer.toString(3);
			
			for(int i = 1; i<longitud; i++) {
				tablero[coordenadaXInicio][coordenadaYFinal+longitud-i] = Integer.toString(3);;
			}
		}	
		
		mostrarTablero();
	}
	

}
