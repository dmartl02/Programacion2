package Ejercicio2;

import java.util.*;

import javax.swing.plaf.synth.SynthScrollPaneUI;

public class JuegoCuadrados {
	
	static Scanner teclado = new Scanner(System.in);
	static int numeroDePuntos = 0;
	static int numeroDeLineas = 0;
	static int maximoLineas = 144; //Rejila de 9x9 con todas las lineas
	static int rejilla[][];;
	static String linea = "";
	static String[] palabrasLinea;
	static int lineaAModificar;
	static int columnaAModificar;
	
	public static void main(String[] args) {
		introducirValoresIniciales();
		crearRejilla();
		aniadirLineasALaRejilla();
	}
	
	
	public static void introducirValoresIniciales() {
		numeroDePuntos = teclado.nextInt();
		numeroDeLineas = teclado.nextInt();
		
		if((numeroDePuntos < 2) || (numeroDePuntos > 9) || (numeroDeLineas > maximoLineas)){
			introducirValoresIniciales();
		}
	}
	
	
	public static void crearRejilla() {
		int rejilla[][] = new int[numeroDePuntos][numeroDePuntos];
		for (int i=0; i < rejilla.length; i++) {
			System.out.println();
			System.out.println();
			for (int j=0; j < rejilla[i].length; j++) {
				System.out.print (rejilla[i][j]);
				if (j!=rejilla[i].length-1) {
					System.out.print("\t");
				}
			}
		}
		
		System.out.println();
	}
	
	
	public static void aniadirLineasALaRejilla() {
		linea = teclado.next();
		palabrasLinea = linea.split("");
		
		/*
		System.out.println(palabrasLinea[0]);
		System.out.println(palabrasLinea[1]);
		System.out.println(palabrasLinea[2]);
		*/
	}
	
}




