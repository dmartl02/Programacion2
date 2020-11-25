package Practica6;

/**
 * Clase principal para el ejercicio de programación 
   del Aeropuerto.
 */

/**
En un aeropuerto existen dos pistas de aterrizaje/despegue (denominadas V y W) 
y una torre de control (donde sólo hay un controlador). Hay un cierto número de 
aviones que realizan vuelos de reconocimiento y que tienen este aeropuerto como 
base de operaciones. El ciclo de vida de un avión es:

hacer
   - permanece en el hangar (entre 12 y 24 horas). (Mensaje: "Avión i en el hangar.")
   - indica a la torre que quiere despegar (Msg: "Avión número i desea despegar.");
   - espera permiso de la torre, que además le indica qué pista utilizar; (Msg: "Torre: 
   							Avión número i puede despegar en pista j.")
   - rueda por la pista indicada y despega en unos 10 minutos;(Msg: "Avión número i 
   							despegando en pista j.")
   - indica a la torre que ya despegó; (Msg: "Avión i ya despegó.")
   - hace un vuelo de reconocimiento de entre 1 y 2 horas; (Msg: "Avión i en 
   															reconocimiento.")
   - indica a la torre que quiere aterrizar; (Msg: "Avión i desea aterrizar.")
   - espera permiso de la torre, que además dice qué pista utilizar; (Msg: "Torre: 
   									Avión número i puede aterrizar en pista j."
   - aterriza en la pista indicada en unos 10 minutos y para; (Msg: "Avión número i 
   															aterrizando en pista j.")
   - indica a la torre que ya aterrizó;(Msg: "Avión i ya aterrizó.")
hasta Número_de_horas;

Se pide simular el comportamiento de los aviones y de la torre evitando que cualquiera 
de las pistas sea utilizada simultáneamente por dos aviones pero permitiendo que las dos 
pistas pueden ser usadas a la vez.

El número de aviones y el número de horas de simulación se leerá de la entrada estándar 
como dos enteros separados por un blanco. Para la simulación se tendrá en cuenta lo 
siguiente:
	- Cada minuto durará 1 milésima de segundo. La simulación durará por tanto 
	aproximadamente Numero_de_horas*60 milésimas de segundo.
	La tarea Torre emitirá un mensaje al terminar: "Torre cerrada." y cada avión 
	"Avión i terminado."
	- Todos los mensajes se enviarán a la salida estándar y aparecerán tal como se 
	indican en la parte entrecomillada,
	sustituyendo i por el número de avión y j por la pista V o W y cada uno en una 
	línea completa (terminada en fin de línea).
	No deberá aparecer ningún otro mensaje en la salida.
	- Se implementarán las clases Avion y Torre como mínimo y se usará la clase Main 
	dada para arrancar el programa. 
	La clase Main no podrá ser cambiada.
	- Los números aleatorios necesarios para simular el comportamiento de los aviones
	 se crearán realizando llamadas a los métodos apropiados de la instancia 
	 Main.aleatorio de la clase java.util.Random.
 */

import java.util.Random;
import java.util.Scanner;

public class Main_Aeropuerto {
	
	public static Random aleatorio = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		System.out.print("Introduzca el numero de aviones: ");
		Scanner sc = new Scanner(System.in);
		int numAviones = sc.nextInt();
		System.out.print("Introduzca el numero de horas de simulación: ");
		int numHorasSimulacion = sc.nextInt();
		sc.close();
		System.out.println("================================");

		Torre torre = new Torre();
		Thread hiloTorre = new Thread(torre);
		hiloTorre.start();
		
		
		Gasolinera camion = new Gasolinera();
		Thread hiloCamion = new Thread(camion);
		hiloCamion.start();
		
		
		Thread[] hilosAviones = new Thread[numAviones];		
		for (int i=0; i<numAviones; i++){
			Avion a = new Avion(i+1, torre, camion);
			hilosAviones[i] = new Thread(a);
			hilosAviones[i].start();
		}

		Thread.sleep(numHorasSimulacion*60);

		for (int i=0; i<numAviones; i++) {
			hilosAviones[i].interrupt();
		}
		
		for (int i=0; i<numAviones; i++) {
			hilosAviones[i].join();
		}
		
		hiloTorre.interrupt();
		hiloTorre.join();
		hiloCamion.interrupt();
		hiloCamion.join();
	}
}
