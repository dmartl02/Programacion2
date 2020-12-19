package Practica1Prog;

import java.util.*;


public class Main {
	static Scanner teclado = new Scanner(System.in);
	static int numeroDeTrenes = 0;
	static String trenTeclado;
	static String[] tren;
	static String direccionTeclado, longitudTeclado, coordenadaXTeclado, coordenadaYTeclado;
	static char direccionCaracter;
	public static int direccion, longitud, coordenadaX, coordenadaY;
	public static ArrayList<Tren> trenes = new ArrayList<Tren>();
	static Tablero tablero = new Tablero();
	
	
	public static void main(String[] args) {
		tablero.crearTablero();
		introducirDatosDelTren();
		crearNuevoTren();
	}
	
	
	public static void introducirDatosDelTren() {
		try {
			numeroDeTrenes = teclado.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
		
		teclado.nextLine();
		comprobarNumeroDeTrenes();
		
		for(int i=0; i<=numeroDeTrenes-1; i++) {
			trenTeclado = teclado.nextLine();
			tren = trenTeclado.split(" ");
			
			direccionTeclado = tren[0];
			direccionCaracter = direccionTeclado.charAt(0);
			direccion = direccionCaracter - 48;
			
			longitudTeclado = tren[1];
			longitud = Integer.parseInt(longitudTeclado);

			coordenadaXTeclado = tren[2];
			coordenadaX = Integer.parseInt(coordenadaXTeclado);
			
			coordenadaYTeclado = tren[3];
			coordenadaY = Integer.parseInt(coordenadaYTeclado);
			
			comprobarDireccion();
			comprobarLongitud();
			comprobarCoordenadas();
			comprobarDentroDelTablero();
		}
	}
	
	
	public static void comprobarNumeroDeTrenes() {	
		if((numeroDeTrenes > 10) || (numeroDeTrenes < 1)) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}
	
	
	public static void comprobarDireccion() {
		if((direccion != 17) && (direccion != 18) && (direccion != 20) && (direccion != 25)) {
			System.out.println("Conjunto de trenes incorrecto.");
	 		System.exit(0);
		}
	}
	
	
	public static void comprobarLongitud() {
		if((longitud < 1) || (longitud > 30)) {
			System.out.println("Conjunto de trenes incorrecto.");
	 		System.exit(0);
		}
	}
	
	
	public static void comprobarCoordenadas() {
		if((coordenadaX < 0) || (coordenadaX > 29) || (coordenadaY < 0) || (coordenadaY > 29)) {
			System.out.println("Conjunto de trenes incorrecto.");
	 		System.exit(0);
		}
	}
		
	
	public static void comprobarDentroDelTablero() {
		if(direccion == 17){
			if((coordenadaX + longitud) > 29) {
				System.out.println("Conjunto de trenes incorrecto.");
		 		System.exit(0);
			}
			
		} else if(direccion == 18){
			if((coordenadaX - longitud) < 0) {
				System.out.println("Conjunto de trenes incorrecto.");
		 		System.exit(0);
			}
		
		} else if(direccion == 20){
			if((coordenadaY - longitud) < 0 ) {
				System.out.println("Conjunto de trenes incorrecto.");
		 		System.exit(0);
			}
			
		} else if(direccion == 25){
			if((coordenadaY + longitud) > 29) {
				System.out.println("Conjunto de trenes incorrecto.");
		 		System.exit(0);
			}
		}
	}
	
	
	public static void crearNuevoTren() {
		for(int i=0; i<=numeroDeTrenes-1; i++) {
			Tren tren = new Tren(direccion, longitud, coordenadaX, coordenadaY);
			tren.aniadirTrenALaLista(tren);
			tablero.aniadirTrenAlTablero(tren);
			tablero.moverTren(tren);
		}
	}
	
	
	
	
}
