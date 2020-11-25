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
			System.out.printf("Avi�n %d terminado.\n", idAvion);
		}	
	}

	
	
	private void enReconocimiento() throws InterruptedException {
	// Hace un vuelo de reconocimiento de entre 1 y 2 horas; (Msg: "Avi�n i en 
	// reconocimiento.\n")
		System.out.printf("Avi�n %d en reconocimiento.\n",this.idAvion);
		Thread.sleep(Main_Aeropuerto.aleatorio.nextInt(2)+1);	
	}	

	
	
	private char solicitarAterrizar() throws InterruptedException {
	// Indica a la torre que quiere aterrizar (Msg: "Avi�n n�mero i desea aterrizar.\n");
		System.out.printf("Avi�n n�mero %d desea aterrizar.\n",this.idAvion);
		return this.idTorre.solicitudAterrizaje(this.idAvion);
	}
	
	
	
	private void aterrizando(char pistaA) throws InterruptedException {
	// Aterriza en la pista indicada en unos 10 minutos y para; 
	// (Msg: "Avi�n n�mero i Aterrizando en pista j.\n")
		System.out.printf("Avi�n n�mero %d aterrizando en pista %c.\n",this.idAvion, pistaA);
		Thread.sleep((long)Main_Aeropuerto.aleatorio.nextGaussian()+10);
	}

	
	
	private void finAterrizaje() {
	// Indica a la torre que ya est� en el hangar;(Msg: "Avi�n i ya aterriz�.\n")
		idTorre.finDespegue(idAvion);
		System.out.printf("Avi�n %d ya aterriz�.\n", idAvion);	
	}

	
	
	private void enElHangar() throws InterruptedException {
	// Permanece en el hangar (entre 12 y 24 horas) (Mensaje: "Avi�n i en el hangar.\n")
		System.out.printf("Avi�n %d en el hangar.\n",this.idAvion);
		Thread.sleep(Main_Aeropuerto.aleatorio.nextInt(24)+12);	
	}
	
	
	
	private char solicitarDespegar() throws InterruptedException {
	// Indica a la torre que quiere despegar (Msg: "Avi�n n�mero i desea despegar.\n");
	// Espera permiso de la torre, que adem�s le indica qu� pista utilizar; (Msg: "Torre: 
	// Avi�n n�mero i puede despegar en pista j.\n")
		System.out.printf("Avi�n n�mero %d desea despegar.\n",this.idAvion);
		return this.idTorre.solicitudDespegue(this.idAvion);
	}

	
	
	private void despegando(char pista) throws InterruptedException {
	// Rueda por la pista indicada y despega en unos 10 minutos;(Msg: "Avi�n n�mero i 
	// despegando en pista j.\n")
		System.out.printf("Avi�n n�mero %d despegando en pista %c.\n",this.idAvion, pista);
		Thread.sleep(10);
	}

	
	
	private void finDespegue() {
	// indica a la torre que ya est� en el hangar;(Msg: "Avi�n i ya despeg�.\n")
		idTorre.finDespegue(idAvion);
		System.out.printf("Avi�n %d ya despeg�.\n", idAvion);
		numeroDeVuelos++;
	}
	
	
	
	private char solicitarRepostar() throws InterruptedException {
	// Indica al cami�n que quiere repostar (Msg: "Avi�n n�mero i desea repostar.\n");
		System.out.printf("Avi�n n�mero %d solicita repostar.\n",this.idAvion);
		return this.idCamion.solicitudRepostaje(this.idAvion);
	}
	
	
	
	private void repostando(char camionA) throws InterruptedException {
	// Reposta en el cami�n indicado en 2 horas y para; (Msg: "Avi�n n�mero i 
	// se encuentra repostando con el cami�n j.\n")
		System.out.printf("Avi�n n�mero %d se encuentra repostando con el cami�n %c.\n",this.idAvion, camionA);
		Thread.sleep((long)Main_Aeropuerto.aleatorio.nextGaussian()+120);
	}
	
	
	private void finRepostaje() {
	// Indica al camion que ya est� en el hangar;(Msg: "Avi�n i ha terminado de repostar.\n")
		idCamion.finRepostaje(idAvion);
		System.out.printf("Avi�n %d ha terminado de repostar.\n", idAvion);	
	}
}
