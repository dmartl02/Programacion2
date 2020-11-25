package Practica4;

import java.util.ArrayList;

public class NReinas {
	/**
	 * Almacena el tamaño del tablero. Eso determina el número de reinas a colocar.
	 */
	int tamanioTablero;
	//private boolean haySolucion;
	 
 	NReinas(int numeroReinas) {
		 tamanioTablero = numeroReinas;
 	}
	
	 
	 /**
	  * Devuelve verdadero si y sólo si hay conflicto por columna o por diagonal entre la reina colocada en la 
	  * fila k de tablero con cualquier otra reina colocada en las filas 0 a k-1.
	  * 
	  * Precondición: No hay conflicto ni por columna ni por diagonal en las posiciones 0 a k-1 entre sí.
	  * 
	  * @param posicionReinaColumna la posición de las reinas en columnas hasta la fila k
	  * @param filaReina fila de la reina a evaluar con las reinas anteriores
	  * @return true hay conflicto entre el valor tab[k] y cualquier tab[i] con i < k por fila o por diagonal, 
	  * false si no lo hay.
	  */
 	
	 public boolean hayConflicto (int[] posicionReinaColumna, int filaReina){
	    for (int i = 0; i < filaReina ; i++){
	        if (posicionReinaColumna[i] == posicionReinaColumna[filaReina] 
	        		|| Math.abs(posicionReinaColumna[i]-posicionReinaColumna[filaReina]) == filaReina-i) {
	            return true;
	        }
	    }
	    
	    return false;            
	 }
	 
	 
	 /**
	  * Resuelve el problema de las n reinas con tam=n. Puede encontrar la primera solución llamando a reinasVueltaAtras() o todas
	  * llamando a reinasVueltaAtras_TodasLasSoluciones()
	  * 
	  * @param solucionesEncontradas Almacena las soluciones encontradas.
	  */
	 
	 public void reinas (ArrayList<int[]> solucionesEncontradas){
	     int[] solucion = new int[tamanioTablero];
	     boolean[] haySolucion = {false};
	     
	     for (int i=0; i<tamanioTablero;i++) {
	         solucion[i] = -1;
	     }
	     
	     //reinasVueltaAtras_TodasLasSoluciones(solucion,0,solucionesEncontradas);
	     reinasVueltaAtras(solucion,0,solucionesEncontradas,haySolucion);
     }	
	 
	 
	 /**
	  * Añade a soluciones todas las soluciones encontradas al problema de las n-reinas a partir de la solución parcial 
	  * dada en solucion hasta la posición fila-1. 
	  * 
	  * Precondición: !hayConflcito(solucion,i) para 0 <= i < fila y solucion.length == tamanioTablero y 0 <= fila < solucion.length
	  * 
	  * @param solucionParcial solución parcial construida hasta el momento. 
	  * @param fila fila que toca poner una nueva reina. Precondición: 0 <= fila < tam 
	  * @param soluciones
	  */
	 
	 private void reinasVueltaAtras_TodasLasSoluciones(int[] solucionParcial, int fila, ArrayList<int[]> soluciones){
	     for (int i=0; i<tamanioTablero; i++){
	         solucionParcial[fila] = i;
	         
	         if (!hayConflicto(solucionParcial,fila)) {
	   	 
	             if (fila == tamanioTablero-1){ 
	                 int[] nuevaSol = new int[tamanioTablero];
	            
	                 for (int j=0; j<tamanioTablero;j++) {
	                     nuevaSol[j] = solucionParcial[j];
	                 }
	                 soluciones.add(nuevaSol);
	             } else {
	            	 reinasVueltaAtras_TodasLasSoluciones(solucionParcial,fila+1,soluciones);
	             }
	         }
	     }
	 }
	 
	 
	 /**
	  * Devuelve true si encuentra una solución al problema de las n-reinas a partir de la solución parcial dada en solucionParcial hasta la 
	  * posición fila-1.  La solución la añade a soluciones.
	  * 
	  * La implementación se realiza usando el parámetro de retorno del método.
	  * Ejercicios: programar este mismo caso usando una variable booleana que sea un atributo de la clase, o usando un parámetro (debe ser 
	  * un vector booleano)
	  * 
	  * Precondición: !hayConflcito(solucionParcial,i) para 0 <= i < fila y solucionParcial not nulo  y 0 <= fila < solucionParcial.length
	  * 
	  * @param solucionParcial : solución parcial construida hasta el momento. 
	  * @param fila : fila que toca poner una nueva reina. Precondición: 0 <= fila < tam 
	  * @param soluciones
	  * @param haySolucion : referencia a un objeto, nunca cambia
	  */
	 
	 private void reinasVueltaAtras(int[] solucionParcial, int fila, ArrayList<int[]> soluciones, boolean[] haySolucion){		 
		 for (int i=0; i<tamanioTablero && !haySolucion[0]; i++){
	         solucionParcial[fila] = i;
	         
	         if (!hayConflicto(solucionParcial,fila)) {
	        	 
	             if (fila==tamanioTablero-1){
	                 int[] nuevaSol = new int[tamanioTablero];
	                 
	                 for (int j=0; j<tamanioTablero;j++) {
	                     nuevaSol[j] = solucionParcial[j];
	                 }
	                 
	                 soluciones.add(nuevaSol); 
	                 haySolucion[0] = true;
	                 
	             } else { //Entra cuando no hay solucion
	                	reinasVueltaAtras(solucionParcial,fila+1,soluciones,haySolucion);
	              }	
	                //Nota: ¿por qué no funciona simplemente return reinasVueltaAtras(solucion, fila+1, soluciones)?
	         }        
	     }
	 }
	 
	 
	 public static void main (String[] args){
	     int n = 6; //Probamos con 4 por defecto
	     
	     if (args.length > 0) {
	         n = Integer.parseInt(args[0]);
	     }
	     
	     NReinas nr = new NReinas(n);
	     ArrayList<int[]> soluciones = new ArrayList<int[]>();
	     
	     nr.reinas(soluciones);
	     System.out.print(nr.toStringSoluciones(soluciones));
	 }
	
	 
	 public String toStringSoluciones(ArrayList<int[]> arrayList) {
	    StringBuffer sal = new StringBuffer();
	    
	    for (int[] sol:arrayList){
	        sal.append("(");
	    for (int i=0; i<tamanioTablero-1; i++){
	        sal.append(sol[i]);
	        sal.append(",");
	    }
	    sal.append(sol[tamanioTablero-1]);
	    sal.append(")\n");
	    }
	    
	    return sal.toString();
	 }
}


