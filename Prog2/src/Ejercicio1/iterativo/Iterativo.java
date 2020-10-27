package Ejercicio1.iterativo;

import java.util.*;

public class Iterativo {
	
	static Scanner teclado = new Scanner(System.in);
	static String longitud = ""; //Cambiar a l
	static int longitudMinima = 3;
	static int longitudIntroducidaEnNumero = 0;
	static String secuencia = ""; //Cambiar a s
	static String[] arraySecuencia;
	static int[] intArraySecuencia;
	static int longitudSecuencia = 0;
	static int n = Integer.MAX_VALUE; 
	static int m = Integer.MIN_VALUE; 
	
	
	public static void main(String[] args) {	
		introducirLongitudYSecuencia(longitud,secuencia);
		compararLongitudIntroducidaYLongitudSecuencia();
		encontrarValoresQueFaltanRecursivo();
	}
		
	public static void introducirLongitudYSecuencia(String longitud, String secuencia) {
		//System.out.println("Longitud de la secuencia: ");
		longitud = teclado.nextLine();
		longitudIntroducidaEnNumero = Integer.parseInt(longitud);
		
		//System.out.println("Secuencia: ");
		secuencia = teclado.nextLine();
	
		arraySecuencia = secuencia.split(" ");
		intArraySecuencia = new int[arraySecuencia.length];
		
		//O(n)
		for(int i = 0; i < arraySecuencia.length; i++) {
			intArraySecuencia[i] = Integer.parseInt(arraySecuencia[i]);
		}
		
		Arrays.sort(intArraySecuencia); 	//O(n * log n )
		//System.out.println("Secuencia pasada a array: " + Arrays.toString(intArraySecuencia));
		//System.out.println("Longitud secuencia introducida: " +arraySecuencia.length);
		
	}
	
	
	public static void compararLongitudIntroducidaYLongitudSecuencia() {
		if(longitudIntroducidaEnNumero < longitudMinima  
				|| longitudIntroducidaEnNumero != arraySecuencia.length) {
			//System.out.println("La longitud minima es 3. Introduza de nuevo la longitud.");
			System.exit(0);
		}
	}
	
	//quitarlo 
	/*
	public static void encontrarMaximoYMinimo() {
		//O(n)
		for(int i=0; i<intArraySecuencia.length; i++) {
			
			if(intArraySecuencia[i] < minimoSecuencia) {
				minimoSecuencia = intArraySecuencia[i];
			} 
			
			if(intArraySecuencia[i] > maximoSecuencia) {
				maximoSecuencia = intArraySecuencia[i];
			}
		}
		
		//System.out.println("Minimo de la secuencia: " +minimoSecuencia);
		//System.out.println("Máximo de la secuencia: " +maximoSecuencia);
	}	
	/**/

	public static void encontrarValoresQueFaltanRecursivo() {
		int valorActual = 0;
		int valorRecibido = 0;
		int siguienteEsperado = 0;
		int anteriorEsperado = 0;
		
		
		//O(n)
		for(int i=0; i<intArraySecuencia.length-1; i++) {
			valorActual = intArraySecuencia[i];
			siguienteEsperado = valorActual + 1;
			valorRecibido = intArraySecuencia[i+1];
		
			if(n == Integer.MAX_VALUE) { 
				if((intArraySecuencia[i] == valorActual) && (siguienteEsperado != valorRecibido)) {
					n = siguienteEsperado;
				}
			}
		}
		
		//O(n)
		//while con un contador n desde abajo, m desde arriba, si chocan mirar casos
		for(int i=intArraySecuencia.length-1; i>0; i--) {
			
			valorActual = intArraySecuencia[i];
			anteriorEsperado = valorActual-1;
			valorRecibido = intArraySecuencia[i-1];
			
			if(m == Integer.MIN_VALUE) { 
				if((intArraySecuencia[i] == valorActual) && (anteriorEsperado != valorRecibido)) {
					m = anteriorEsperado;
				}
			}
		}	
		
		if (n ==Integer.MAX_VALUE && m == Integer.MIN_VALUE) {
			System.out.println("La secuencia esta completa.");
		} else {
			System.out.println(n+ " " +m);
		}
	}
}
	

