package Practica6;

/**
 * Clase que implementa la tarea Avion que simula el 
 * comportamiento de un avion en el ejercicio del 
 * Aeropuerto.
 *
 */

public class Avion implements Runnable {
	private int idAvion;
	private Torre idTorre;
	private Gasolinera idCamion;
	private int numeroDeVuelos = 0;

	
	public Avion(int i, Torre hiloTorre, Gasolinera hiloCamion) {
		idAvion = i;
		idTorre = hiloTorre;
		idCamion = hiloCamion;
	}

	
	
	public int getIdAvion(){
		return idAvion;
	}
		
	
	
	public void run() {
		try {
			do {
				enElHangar();
				char pistaD = solicitarDespegar();
				despegando(pistaD);
				finDespegue();
				
				enReconocimiento();
				char pistaA = solicitarAterrizar();	
				aterrizando(pistaA);
				finAterrizaje();
				idTorre.finAterrizaje(idAvion);
				
				if(numeroDeVuelos % 5 == 0) {
					char camionA = solicitarRepostar();
					repostando(camionA);
					finRepostaje();
					idCamion.finRepostaje(idAvion);
				}
				
				
			} while (true); 
			
		} catch (InterruptedException e) {
			System.out.printf("Avión %d terminado.\n", idAvion);
		}	
	}

	
	
	private void enReconocimiento() throws InterruptedException {
	// Hace un vuelo de reconocimiento de entre 1 y 2 horas; (Msg: "Avión i en 
	// reconocimiento.\n")
		System.out.printf("Avión %d en reconocimiento.\n",this.idAvion);
		Thread.sleep(Main_Aeropuerto.aleatorio.nextInt(2)+1);	
	}	

	
	
	private char solicitarAterrizar() throws InterruptedException {
	// Indica a la torre que quiere aterrizar (Msg: "Avión número i desea aterrizar.\n");
		System.out.printf("Avión número %d desea aterrizar.\n",this.idAvion);
		return this.idTorre.solicitudAterrizaje(this.idAvion);
	}
	
	
	
	private void aterrizando(char pistaA) throws InterruptedException {
	// Aterriza en la pista indicada en unos 10 minutos y para; 
	// (Msg: "Avión número i Aterrizando en pista j.\n")
		System.out.printf("Avión número %d aterrizando en pista %c.\n",this.idAvion, pistaA);
		Thread.sleep((long)Main_Aeropuerto.aleatorio.nextGaussian()+10);
	}

	
	
	private void finAterrizaje() {
	// Indica a la torre que ya está en el hangar;(Msg: "Avión i ya aterrizó.\n")
		idTorre.finDespegue(idAvion);
		System.out.printf("Avión %d ya aterrizó.\n", idAvion);	
	}

	
	
	private void enElHangar() throws InterruptedException {
	// Permanece en el hangar (entre 12 y 24 horas) (Mensaje: "Avión i en el hangar.\n")
		System.out.printf("Avión %d en el hangar.\n",this.idAvion);
		Thread.sleep(Main_Aeropuerto.aleatorio.nextInt(24)+12);	
	}
	
	
	
	private char solicitarDespegar() throws InterruptedException {
	// Indica a la torre que quiere despegar (Msg: "Avión número i desea despegar.\n");
	// Espera permiso de la torre, que además le indica qué pista utilizar; (Msg: "Torre: 
	// Avión número i puede despegar en pista j.\n")
		System.out.printf("Avión número %d desea despegar.\n",this.idAvion);
		return this.idTorre.solicitudDespegue(this.idAvion);
	}

	
	
	private void despegando(char pista) throws InterruptedException {
	// Rueda por la pista indicada y despega en unos 10 minutos;(Msg: "Avión número i 
	// despegando en pista j.\n")
		System.out.printf("Avión número %d despegando en pista %c.\n",this.idAvion, pista);
		Thread.sleep(10);
	}

	
	
	private void finDespegue() {
	// indica a la torre que ya está en el hangar;(Msg: "Avión i ya despegó.\n")
		idTorre.finDespegue(idAvion);
		System.out.printf("Avión %d ya despegó.\n", idAvion);
		numeroDeVuelos++;
	}
	
	
	
	private char solicitarRepostar() throws InterruptedException {
	// Indica al camión que quiere repostar (Msg: "Avión número i desea repostar.\n");
		System.out.printf("Avión número %d solicita repostar.\n",this.idAvion);
		return this.idCamion.solicitudRepostaje(this.idAvion);
	}
	
	
	
	private void repostando(char camionA) throws InterruptedException {
	// Reposta en el camión indicado en 2 horas y para; (Msg: "Avión número i 
	// se encuentra repostando con el camión j.\n")
		System.out.printf("Avión número %d se encuentra repostando con el camión %c.\n",this.idAvion, camionA);
		Thread.sleep((long)Main_Aeropuerto.aleatorio.nextGaussian()+120);
	}
	
	
	private void finRepostaje() {
	// Indica al camion que ya está en el hangar;(Msg: "Avión i ha terminado de repostar.\n")
		idCamion.finRepostaje(idAvion);
		System.out.printf("Avión %d ha terminado de repostar.\n", idAvion);	
	}
}
