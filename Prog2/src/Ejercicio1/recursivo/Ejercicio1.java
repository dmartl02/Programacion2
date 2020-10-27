package Ejercicio1.recursivo;

import java.util.Scanner;

public class Ejercicio1 {
	
	static Scanner teclado = new Scanner(System.in);
	static String l = "";
	static int intLongitud = 0;
	static String s = ""; 
	static String[] arraySecuencia;
	static int[] intArraySecuencia;
	static int longitudMinima = 3;
	static boolean secuenciaCompleta = false;
	static int n = Integer.MAX_VALUE; 
	static int m = Integer.MIN_VALUE;
	
	
	
	public static void main(String[] args) {
		introducirLongitudYSecuencia(l, s);
		compararLogitudes();
		encontrarValoresQueFaltanRecursivo(0, intArraySecuencia.length - 1);
			
		if ((secuenciaCompleta == false) 
				&& (n != Integer.MAX_VALUE) 
				&& (m != Integer.MIN_VALUE)
				&& (n <= m)) {
			System.out.println(n + " " + m);
		}
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
		
		ordenarSecuencia(intArraySecuencia);
	}
	
	
	public static void ordenarSecuencia(int[] arrayOrdenado) {
	    boolean cambiado = true;
	    int j = 0;
	    int aux;
	    while (cambiado) {
	        cambiado = false;
	        j++;
	        
	        for (int i = 0; i < arrayOrdenado.length - j; i++) {
	            if (arrayOrdenado[i] > arrayOrdenado[i + 1]) {
	                aux = arrayOrdenado[i];
	                arrayOrdenado[i] = arrayOrdenado[i + 1];
	                arrayOrdenado[i + 1] = aux;
	                cambiado = true;
	            }
	        }
	    }
	}
	
    
	public static void compararLogitudes() {
		if(intLongitud < longitudMinima  || intLongitud != arraySecuencia.length) {
			System.exit(0);
		}
	}
	
	
	public static void encontrarValoresQueFaltanRecursivo(int contadorA, int contadorB) {
		
		if((contadorA > intArraySecuencia.length-1) || (contadorB < 1)) {
			secuenciaCompleta=true;
			System.out.println("Secuencia completa");
		} else {
			int valorA = intArraySecuencia[contadorA];
			int valorB= intArraySecuencia[contadorB];
			
			if((valorA+1 == intArraySecuencia[contadorA+1]) && (n == Integer.MAX_VALUE)) {
				contadorA++;
			} else {
				n = valorA+1;
			}
			
			if((valorB-1 == intArraySecuencia[contadorB-1]) && (m == Integer.MIN_VALUE)) {
				contadorB--;
			} else {
				m = valorB-1;
			}
			
			
			if (n == Integer.MAX_VALUE || m == Integer.MIN_VALUE) {
				encontrarValoresQueFaltanRecursivo(contadorA, contadorB);
			}
		}
	}
	
}












