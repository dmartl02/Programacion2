package Practica8;

public class Poligono {
	private static final int MINIMO_DE_LADOS = 3;
	private static final int MAXIMO_DE_LADOS = 12;
	private int numeroDeLados;
	private int minimoLados = MINIMO_DE_LADOS;
	private int maximoLados = MAXIMO_DE_LADOS;
	

	public String nombreDelPoligono() {
		switch (numeroDeLados) {
			case 3:
				return "Triángulo";
			case 4:
				return "Cuadrado";
			case 5:
				return "Pentágono";
			case 6:
				return "Hexágono";
			case 7:
				return "Heptágono";
			case 8:
				return "Octógono";
			case 9:
				return "Eneágono";
			case 10:
				return "Decágono";
			case 11:
				return "Endecágono";
			case 12:
				return "Dodecágono";
			} 
		
		return "No reconocido";
	}
	
	
	public int getNumeroDeLados() {
		return numeroDeLados;
	}
	
	
	public void setNumeroDeLados(int numeroDeLados) {
		this.numeroDeLados = numeroDeLados;
	}
	
	
	public static int getMinimoDeLados() {
		return MINIMO_DE_LADOS;
	}
	
	
	public static int getMaximoDeLados() {
		return MAXIMO_DE_LADOS;
	}
}