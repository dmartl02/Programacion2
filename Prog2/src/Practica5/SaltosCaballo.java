package Practica5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
Escribir un programa que de el número de movimientos mínimo al problema del salto de caballo. 
Este problema consiste en encontrar un camino entre dos casillas dadas de un tablero de ajedrez de 
tamaño n mediante saltos de caballo válidos y sin repetir casillas.

La entrada vendrá en una línea formada por 5 números, el primero será el tamaño±o del tablero n, los dos 
siguientes las coordenadas de la casilla de  salida s1, s2 con 0<=s1,s2<=n-1 y los dos siguientes las 
coordenadas de la casilla de llegada f1, f2  con 0<=f1,f2<=n-1. Las coordenadas empiezan a numerarse 
en la casilla superior izquierda con las coordenadas (0,0).


 * @author David Martínez López
 *
 */
public class SaltosCaballo {
    public SaltosCaballo() {
        super();
    }

    
    
    public static void main (String[] args){
        SaltosCaballo m = new SaltosCaballo();
        
        Scanner scan = new Scanner(System.in);
        
        Casilla.tamTablero = scan.nextInt();
        int fila = scan.nextInt();
        int col = scan.nextInt();
        Casilla casillaInicial = new Casilla(fila,col,0);
        fila = scan.nextInt();
        col = scan.nextInt();
        Casilla casillaFinal = new Casilla(fila,col,0);
        
        scan.close();
            
        casillaInicial.nmovs = 0;
        casillaFinal.nmovs = 0;
        Casilla nssmc = m.numeroMinimoDeSaltos(casillaInicial,casillaFinal);
        
        if(nssmc == null) {
        	System.out.println("");
        }else {
	        ArrayList<Casilla> solucion = new ArrayList<Casilla>();
	        solucion.add(nssmc);
	        while(nssmc.padre != null) {
	        	solucion.add(nssmc.padre);
	        	nssmc = nssmc.padre;
	        }
	        
	        Collections.reverse(solucion);
	        System.out.println(solucion);
	    }
    }
    
    
    
    /**
	 * Devuelve el número máximo de saltos del caballo de ajedrez que hay que hacer en un tablero de nxn casillas
	 * para ir de casInicial a casFinal. 
	 * El metodo sigue un diseño de búsqueda en anchura.
     *
     * @param casillaInicial casilla de la posicón inicial del caballo. No nulo.
     * @param casillaFinal casilla a la que hay que llegar. No nulo.
     * @return numero minimo de saltos o -1 si no hay solucion.
     */
    Casilla numeroMinimoDeSaltos(Casilla casillaInicial, Casilla casillaFinal){
        ArrayList<Casilla> pendientes = new ArrayList<Casilla>();
        if (casillaInicial.equals(casillaFinal)){
        	return casillaInicial;
        }
        pendientes.add(casillaInicial);
        int actual = 0;
        
        do {
            Casilla casActual = pendientes.get(actual);
            ArrayList<Casilla> sucesoras = calcularSucesorasValidasCasilla(casActual,pendientes);
            for (Casilla c:sucesoras){
                if (c.equals(casillaFinal))
                    return c;
                pendientes.add(c);
            } 
        } while (++actual < pendientes.size());    
        return null;
    }


    
    /**
     * Calcula las casillas válidas que se pueden alcanzar  desde casActual y que no están en visitadas.
     * Actualiza nmovs de las casillas a el casActual.nmovs+1.
     * @param casActual
     * @param visitadas
     * @return
     */
    ArrayList<Casilla> calcularSucesorasValidasCasilla(Casilla casActual, ArrayList<Casilla> visitadas) {
        ArrayList<Casilla> result = new ArrayList<Casilla>();
        
        for (int i=1; i<3; i++){
            int j = 3 - i;
            for (int k=-1; k<2; k=k+2)
                for (int l=-1; l<2; l=l+2){
                    int f = casActual.fila + k*i;
                    int c = casActual.col + l*j;
                    if (f>=0 && f<=Casilla.tamTablero-1 && c>=0 && c<=Casilla.tamTablero-1){
                        Casilla cas = new Casilla(f,c,casActual.nmovs+1);
                        if (!perteneceCasilla(cas,visitadas)){
                            result.add(cas);
                        }
                    }
                }
        }
        return result;
            
    }
    

    
    /**
     * Devuelve true si casilla está en lista y false en otro caso.
     * @param casilla
     * @param lista
     * @return
     */
    boolean perteneceCasilla(Casilla casilla, ArrayList<Casilla> lista) {
        for (Casilla c:lista){
            if (c.equals(casilla))
                return true;
        }
        return false;
    }
}



/**
 * Representa una casilla de un tablero de ajedrez de nxn
 * @author adolfo
 *
 */
class Casilla {
    static int tamTablero;
    int fila, col, nmovs; //nmovs: almacena el numero de movimientos de un caballo asociado a esta casilla en la busqueda en anchura.
    Casilla padre = null;
    
    /**
     * @param i fila de la casilla 0 <= j <= tamTablero-1
     * @param j columna de la casilla 0 <= j <= tamTablero-1
     * @param k numero de movimientos hasta llegar aqui. 0<= k
     */
    
    Casilla(int i, int j, int k){
        fila = i; col = j; nmovs = k; 
    }
    
    /**
     * @param i fila de la casilla 0 <= j <= tamTablero-1
     * @param j columna de la casilla 0 <= j <= tamTablero-1
     * @param k numero de movimientos hasta llegar aqui. 0<= k
     * @param p padre de la casilla. NULL si no tiene padr
     */
    Casilla(int i, int j, int k, Casilla p){
        fila = i; col = j; nmovs = k; padre = p;
    }
    


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + fila;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casilla other = (Casilla) obj;
		if (col != other.col)
			return false;
		if (fila != other.fila)
			return false;
		return true;
	}


	@Override
    public String toString(){
    	return "["+ fila + " " + col +"]";
    }
}
