package Practica6;

/**
* Clase que implementa la tarea Torre que simula el comportamiento de la torre de control
* en el ejercicio del Aeropuerto.
*
*/

public class Torre implements Runnable{

	private int pistaUsada[] = {0,0};

	Torre(){
		
	}

	
	
	@Override
	public void run() {
			try {
				while (true){
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				System.out.println("Torre cerrada.");
			}
	}
	
	
	
	private char pista2char(int i) {
		if (i==0)
			return 'V';
		return 'W';
	}	
	
	
	
	public synchronized char solicitudDespegue(int idAvion) throws InterruptedException {
		// el avión espera permiso de la torre, que además dice qué pista 
		// utilizar; 
		// (Msg: "Torre: Avión número i puede aterrizar en pista j.\n"
		
		int pista = 0;
		while (pistaUsada[0] > 0 && pistaUsada[1] > 0)	wait();
		
		if (pistaUsada[1] == 0)	{
			pista = 1;
		}
		
		pistaUsada[pista] = idAvion;
		System.out.printf("Torre: Avión número %d puede despegar en pista %c.\n", idAvion, pista2char(pista));
		return pista2char(pista);
	}
	
	
	
	public synchronized void finDespegue(int idAvion) {
		liberarPista(idAvion);	
	}
	
	
	
	public void liberarPista (int idAvion){
		for (int i=0; i<2; i++){
			if (pistaUsada[i] == idAvion){
				pistaUsada[i] = 0;
				notifyAll();
				return;
			}		
		}   	
	}
	
	
	
	public synchronized char solicitudAterrizaje(int idAvion) throws InterruptedException{
		int pista = 0;
				
		while (pistaUsada[0] > 0 && pistaUsada[1] > 0)
				wait();
		
		if (pistaUsada[1] == 0){
			pista = 1;
		}
		
		pistaUsada[pista] = idAvion;
		System.out.printf("Torre: Avión número %d puede aterrizar " + "en pista %c.\n",idAvion,pista2char(pista));
		return pista2char(pista);
	}
	
	
	
	public synchronized void finAterrizaje(int idAvion) {
		liberarPista(idAvion);
	}
	
}
