package Ejercicio1.recursivo.bueno;

import java.util.Scanner;
import java.util.Arrays;

public class Ejercicio1 {
	
	static Scanner teclado = new Scanner(System.in);
	static String l = "";
	static int intLongitud = 0;
	static String s = ""; 
	static String[] arraySecuencia;
	static int[] intArraySecuencia;
	static boolean[] flags;
	static int longitudMinima = 3;
	static int n = Integer.MAX_VALUE; 
	static int m = Integer.MIN_VALUE;
	static int valorInicio = 0;
	static int valorFinal = 0;
	
	
	public static void main(String[] args) {
		introducirLongitudYSecuencia(l, s);
		compararLogitudes();
		encontrarValoresQueFaltanRecursivo(intArraySecuencia[0], intArraySecuencia[1], 2);
		System.out.println(n);
	}
	
	
	public static void introducirLongitudYSecuencia(String l, String s) {
		l = teclado.nextLine();
		intLongitud = Integer.parseInt(l);
		
		s = teclado.nextLine();
	
		arraySecuencia = s.split(" ");
		intArraySecuencia = new int[arraySecuencia.length];
		
		for(int i = 0; i < arraySecuencia.length; i++) {
			intArraySecuencia[i] = Integer.parseInt(arraySecuencia[i]);
		}
		
		flags = new boolean[arraySecuencia.length];
		//Arrays.sort(intArraySecuencia); 
	}
	
	
	public static void compararLogitudes() {
		if(intLongitud < longitudMinima  || intLongitud != arraySecuencia.length) {
			System.exit(0);
		}
	}
	
	
	public static void encontrarValoresQueFaltanRecursivo(int min, int secondMin, int pointer) {
		if (pointer != intArraySecuencia.length ) {
			if (min >= intArraySecuencia[pointer]) {
				secondMin = min;
				min = intArraySecuencia[pointer];
			}else if( secondMin >= intArraySecuencia[pointer] ) {
				secondMin = intArraySecuencia[pointer];
			}
			pointer++;
			encontrarValoresQueFaltanRecursivo(min, secondMin, pointer);
					
		}
		
		if (min + 1 <= secondMin) {
			n = min+1;
		}else {
			
		}
	}
}


/*

Recursiva (3 valores)
	1. Valor minimo del array
	2. Segundo valor minimo del array
	3. Puntero

	1 - 2 
		Eliminar estos dos valores del array y comenzar de nuevo
		


/**/







