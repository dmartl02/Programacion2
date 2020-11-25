package Practica6;

/**
* Clase que implementa la tarea Camión que simula el comportamiento de los camiones
* en el ejercicio del Aeropuerto.
*
*/

public class Gasolinera implements Runnable{
	
	private int camionUsado[] = {0,0,0};
	//private int idCamion;
	//private Avion idAvion;
	// private int avionesRepostados = 0;
	
	
	Gasolinera(){
		
	}
	
	
	/*
	//Modificacion 2
	public Gasolinera(int i, Avion hiloAviones) {
		idCamion = i;
		idAvion = hiloAviones;
		
	}
	*/
	
	
	@Override
	public void run() {
			try {
				while (true){
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				System.out.println("Camión fuera de servicio.");
			}
	}
	
	
	
	private char camion2char(int i) {
		if (i==0) {
			return 'a';
		} else if (i==1) {
			return 'b';
		} else {
			return 'c';
		}
	}	
	
	
	
	public synchronized char solicitudRepostaje(int idAvion) throws InterruptedException {
		// El avión espera permiso de la gasolinera, que además dice qué camión utilizar; 
		// (Msg: "Camión: Avión número i puede repostar en camión j.\n"
		
		int camion = 0;
		while ((camionUsado[0] > 0) && (camionUsado[1] > 0) && (camionUsado[2] > 0)) {
			wait();
		}
		
		if (camionUsado[1] == 0)	{
			camion = 1;
		} 
		
		if(camionUsado[2] == 0) {
			camion = 2;
		}
		
		camionUsado[camion] = idAvion;
		System.out.printf("Camión: Avión número %d puede repostar en camión %c.\n", idAvion, camion2char(camion));
		return camion2char(camion);
	}
	
	
	
	public synchronized void finRepostaje(int idAvion) {
		liberarCamion(idAvion);	
	}
	
	
	
	public void liberarCamion (int idAvion){
		for (int i=0; i<3; i++){
			if (camionUsado[i] == idAvion){
				camionUsado[i] = 0;
				notifyAll();
				return;
			}		
		}   	
	}
	
}
