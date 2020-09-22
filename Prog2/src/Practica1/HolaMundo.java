package Practica1;

import java.util.Scanner;

/**
 * 
 * @author David Martinez Lopez
 *
 */

public class HolaMundo {
	
	static Scanner teclado = new Scanner(System.in);
	String nombre = teclado.next();
	
	public static void main(String[] args) {
		introducirNombre();
	}
	
	public static void introducirNombre() {
		System.out.println("Introduzca su nombre: ");
		String nombre = teclado.next();
		System.out.println("Buenas tardes " +nombre+ ", bienvenido a Programación II");
	}
	
}
