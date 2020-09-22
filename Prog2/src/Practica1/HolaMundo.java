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
		String nombre = teclado.next();
		System.out.println("Hola " +nombre+ ", bienvenido a Programación II y al mundo Java");
	}
	
}
