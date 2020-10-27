package Practica2;

/*
 * Segunda sesión de prácticas
 * 
 * @author David Martínez López
 * 
 */

import java.util.*;

public class Recursividad {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int numeroTeclado;
		double parametroAcumulador = 0.0;
		
		do {
			numeroTeclado = teclado.nextInt();
		} while(numeroTeclado < 0);
		
		System.out.println("Calculado mediante funcion recursiva directa: " + recursivaDirecta(numeroTeclado));
		//System.out.println("Calculado mediante función recursiva sin bucles: " +recursivaSinBucle(numeroTeclado, parametroAcumulador));
		//System.out.println("Calculado mediante funcion iterativa directa: " + iterativaDirecta(numeroTeclado));
		//System.out.println("Calculado mediante funcion iterativa directa: " + iterativaLineal(numeroTeclado));
		
	}

	/*	
	 * 	Trato el caso base, que es cuando n=0
	 *	Trato el caso general, que es cuando n>0
	 *	Calcular el sumatorio de la definicion de la funcion C
	 *	Devolver el valor de C(n)
	 */
	
	public static double recursivaDirecta(int numeroTeclado) {
		double resultado = 0;
		
		if(numeroTeclado == 0) {
			return 1;
		} else {
			for(int i=0; i<numeroTeclado; i++) {
				resultado = resultado + recursivaDirecta(i);
			}
			
			return numeroTeclado+(2.0/numeroTeclado) * resultado;
		}
	}	
	
	private static double recursivaSinBucle(int numeroTeclado, double parametroAcumulador) {
		if(numeroTeclado == 0) {
			return 1;
		} else {
			return recursivaSinBucle(numeroTeclado-1, parametroAcumulador+recursivaDirecta(numeroTeclado));
		}
	}
	
	public static double iterativaDirecta(int numeroTeclado) {
		double arrayDeResultados[] = new double[numeroTeclado+1];
		arrayDeResultados[0] = 1;
		
		for(int i=0; i<=numeroTeclado; i++) {
			double resultado = 0;
			
			for(int j=0; j<i; j++) {
				resultado = resultado + arrayDeResultados[j];
			}
			arrayDeResultados[i] = (2.0/i) * resultado + i;
		}
		
		return arrayDeResultados[numeroTeclado];
	}
	
	public static double iterativaLineal(int numeroTeclado) {
		double resultado = 0;
		
		if(numeroTeclado == 0) {
			return 1;
		}
		
		for(int i=1; i<numeroTeclado;i++) {
			resultado = resultado + (2.0/i)*resultado + i;
		}
		
		return (2.0/numeroTeclado)*resultado + numeroTeclado;
			
	}
	
	
	/*public static double recursivaLineal(int numeroTeclado) {
		
	}*/
	
	
}
