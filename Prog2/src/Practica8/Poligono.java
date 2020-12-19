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
				return "Tri�ngulo";
			case 4:
				return "Cuadrado";
			case 5:
				return "Pent�gono";
			case 6:
				return "Hex�gono";
			case 7:
				return "Hept�gono";
			case 8:
				return "Oct�gono";
			case 9:
				return "Ene�gono";
			case 10:
				return "Dec�gono";
			case 11:
				return "Endec�gono";
			case 12:
				return "Dodec�gono";
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