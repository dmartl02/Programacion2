package Practica1Prog;

import java.util.ArrayList;

public class Tren extends Main{
	int direccion;
	int longitud;
	int coordenadaX;
	int coordenadaY;

	
	public Tren(int direccion, int longitud, int coordenadaX, int coordenadaY) {
		this.direccion = direccion;
		this.longitud = longitud;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}
	
	
	public int getDireccion() {
		return direccion;
	}
	
	
	public int getLongitud() {
		return longitud;
	}
	
	
	public int getCoordenadaX() {
		return coordenadaX;
	}
	
	
	public int getCoordenadaY() {
		return coordenadaY;
	}
	
	
	public int setCoordenadaX(int coordenadaX) {
		return this.coordenadaX = coordenadaX;
	}


	public int setCoordenadaY(int coordenadaY) {
		return this.coordenadaY = coordenadaY;
	}
	
	public void aniadirTrenALaLista(Tren tren){
		tren.toString();
		trenes.add(tren);
	}
	
	
	public void moverTren(Tren tren) {
		int coordenadaXInicial;
		int coordenadaYInicial;
		int coordenadaXFinal;
		int coordenadaYFinal;
		
		if(tren.getDireccion() == 17) {
			coordenadaXInicial = setCoordenadaX(getCoordenadaX()+1);
			coordenadaYInicial = setCoordenadaY(getCoordenadaY());
			coordenadaXFinal = setCoordenadaX(getCoordenadaX()-getLongitud());
			coordenadaYFinal = setCoordenadaY(getCoordenadaY());
			
		} else if(tren.getDireccion() == 18) {
			coordenadaXInicial = setCoordenadaX(getCoordenadaX()-1);
			coordenadaYInicial = setCoordenadaY(getCoordenadaY());
			coordenadaXFinal = setCoordenadaX(getCoordenadaX()+getLongitud());
			coordenadaYFinal = setCoordenadaY(getCoordenadaY());
			
		} else if(tren.getDireccion() == 25) {
			coordenadaXInicial = setCoordenadaX(getCoordenadaX());
			coordenadaYInicial = setCoordenadaY(getCoordenadaY()-1);
			coordenadaXFinal = setCoordenadaX(getCoordenadaX());
			coordenadaYFinal = setCoordenadaY(getCoordenadaY()+getLongitud());
			
		} else if(tren.getDireccion() == 20) {
			coordenadaXInicial = setCoordenadaX(getCoordenadaX());
			coordenadaYInicial = setCoordenadaY(getCoordenadaY()+1);
			coordenadaXFinal = setCoordenadaX(getCoordenadaX());
			coordenadaYFinal = setCoordenadaY(getCoordenadaY()-getLongitud());
			
		}
	}
	
	@Override
    public String toString(){
    	return "("+ direccion + "," + longitud + "," + coordenadaX + "," + coordenadaY +")";
    }


	

	
}