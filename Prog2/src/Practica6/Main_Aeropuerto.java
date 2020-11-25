package Practica6;

/**
 * Clase principal para el ejercicio de programaci�n 
   del Aeropuerto.
 */

/**
En un aeropuerto existen dos pistas de aterrizaje/despegue (denominadas V y W) 
y una torre de control (donde s�lo hay un controlador). Hay un cierto n�mero de 
aviones que realizan vuelos de reconocimiento y que tienen este aeropuerto como 
base de operaciones. El ciclo de vida de un avi�n es:

hacer
   - permanece en el hangar (entre 12 y 24 horas). (Mensaje: "Avi�n i en el hangar.")
   - indica a la torre que quiere despegar (Msg: "Avi�n n�mero i desea despegar.");
   - espera permiso de la torre, que adem�s le indica qu� pista utilizar; (Msg: "Torre: 
   							Avi�n n�mero i puede despegar en pista j.")
   - rueda por la pista indicada y despega en unos 10 minutos;(Msg: "Avi�n n�mero i 
   							despegando en pista j.")
   - indica a la torre que ya despeg�; (Msg: "Avi�n i ya despeg�.")
   - hace un vuelo de reconocimiento de entre 1 y 2 horas; (Msg: "Avi�n i en 
   															reconocimiento.")
   - indica a la torre que quiere aterrizar; (Msg: "Avi�n i desea aterrizar.")
   - espera permiso de la torre, que adem�s dice qu� pista utilizar; (Msg: "Torre: 
   									Avi�n n�mero i puede aterrizar en pista j."
   - aterriza en la pista indicada en unos 10 minutos y para; (Msg: "Avi�n n�mero i 
   															aterrizando en pista j.")
   - indica a la torre que ya aterriz�;(Msg: "Avi�n i ya aterriz�.")
hasta N�mero_de_horas;

Se pide simular el comportamiento de los aviones y de la torre evitando que cualquiera 
de las pistas sea utilizada simult�neamente por dos aviones pero permitiendo que las dos 
pistas pueden ser usadas a la vez.

El n�mero de aviones y el n�mero de horas de simulaci�n se leer� de la entrada est�ndar 
como dos enteros separados por un blanco. Para la simulaci�n se tendr� en cuenta lo 
siguiente:
	- Cada minuto durar� 1 mil�sima de segundo. La simulaci�n durar� por tanto 
	aproximadamente Numero_de_horas*60 mil�simas de segundo.
	La tarea Torre emitir� un mensaje al terminar: "Torre cerrada." y cada avi�n 
	"Avi�n i terminado."
	- Todos los mensajes se enviar�n a la salida est�ndar y aparecer�n tal como se 
	indican en la parte entrecomillada,
	sustituyendo i por el n�mero de avi�n y j por la pista V o W y cada uno en una 
	l�nea completa (terminada en fin de l�nea).
	No deber� aparecer ning�n otro mensaje en la salida.
	- Se implementar�n las clases Avion y Torre como m�nimo y se usar� la clase Main 
	dada para arrancar el programa. 
	La clase Main no podr� ser cambiada.
	- Los n�meros aleatorios necesarios para simular el comportamiento de los aviones
	 se crear�n realizando llamadas a los m�todos apropiados de la instancia 
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
		System.out.print("Introduzca el numero de horas de simulaci�n: ");
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
