package Ejercicio2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> listaSoluciones = new ArrayList<String>();
		
		Scanner lee = new Scanner(System.in);
		
		while(lee.hasNext()) {
			int numMaxUnidades = 100;
			int tamTablero = 30;
			ArrayList<Tren> trenes = new ArrayList<Tren>();
			
			try {
				int nTrenes = lee.nextInt();
				
				if(nTrenes<1 || nTrenes>10) {
					lee.close();
					imprimeSoluciones(listaSoluciones);
					System.out.print("Conjunto de trenes incorrecto.\n");
					return;
				}
				
				int aux = 0;
				int contadorNumUnidades = 0;
				
				while(aux<nTrenes) {
					lee.nextLine();
					String sentidoCadena = lee.next();
					char sentidoLetra = sentidoCadena.charAt(0);
					int sentidoNum = -1;
					switch(sentidoLetra) {
						case 'A': sentidoNum = 1;
						break;
						case 'B': sentidoNum = 0;
						break;
						case 'I': sentidoNum = 2;
						break;
						case 'D': sentidoNum = 3;
						break;
					}
					
					int longitud = lee.nextInt();
					int coordX = lee.nextInt();
					int tmp = lee.nextInt();
					int coordY = Math.abs(tmp - (tamTablero-1));
					if(longitud<1 || longitud>tamTablero || coordX<0 || coordX>(tamTablero-1) || coordY<0 || coordY>(tamTablero-1)) {
						lee.close();
						imprimeSoluciones(listaSoluciones);
						System.out.print("Conjunto de trenes incorrecto.\n");
						return;
					}
					
					contadorNumUnidades = contadorNumUnidades + longitud;
					if(contadorNumUnidades > numMaxUnidades) {
						lee.close();
						imprimeSoluciones(listaSoluciones);
						System.out.print("Conjunto de trenes incorrecto.\n");
						return;
					}
					
					trenes.add(new Tren(sentidoNum, longitud, coordY, coordX));
					aux++;
				}
				
			} catch(Exception e) {
				imprimeSoluciones(listaSoluciones);
				System.out.print("Conjunto de trenes incorrecto.\n");
				return;
			}
			
			Tablero tablero = new Tablero(tamTablero, tamTablero);
			Simulacion simulacion = new Simulacion(tablero, trenes);
			
			boolean podemosComenzar = simulacion.inicializaJuego();
			if(podemosComenzar) {
				String sol = simulacion.comienzaMovimientos();
				listaSoluciones.add(sol);
			} else {
				lee.close();
				imprimeSoluciones(listaSoluciones);
				System.out.print("Conjunto de trenes incorrecto.\n");
				return;
			}
		}
		
		lee.close();
		imprimeSoluciones(listaSoluciones);
		return;
	}
	
	
	/**
	 * Método auxiliar que se encarga de imprimir la lista de soluciones.
	 */
	public static void imprimeSoluciones(ArrayList<String> listaSoluciones) {
		if(listaSoluciones.size() > 0) {
			for(int i=0 ; i<listaSoluciones.size() ; i++) {
				System.out.println(listaSoluciones.get(i));
			}
		}
	}
}
