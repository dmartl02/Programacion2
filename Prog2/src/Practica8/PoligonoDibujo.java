package Practica8;

import java.awt.Graphics;
import java.awt.font.GraphicAttribute;

import javax.swing.JPanel;

class PoligonoDibujo extends JPanel{
	
	private Poligono poligono;
	
	public void setPoligono(Poligono p) {
		poligono = p;
	}
	
	public void paintComponent(Graphics g) {
		//Borramos el panel
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		//Nuevo rectangulo
		int[][] vertices = this.calcularVertices(poligono.getNumeroDeLados());
		g.drawPolygon(vertices[0], vertices[1], poligono.getNumeroDeLados());
	}
	
	
	private int[][] calcularVertices(int n) {		
		int VIEW_WIDTH = this.getWidth();		
		int VIEW_HEIGHT = this.getHeight();		
		int centro_x = VIEW_WIDTH/2; 		
		int centro_y = VIEW_HEIGHT/2;		
		int VERTICAL_SPACE = 20;		
		
		//int[0] almacena las x e int[1] las y	
		int[][] result = new int[2][n];		
		result[0][0] = centro_x;		
		result[1][0] = 2*VERTICAL_SPACE;	
		
		//Calculo las coordenadas polares del primer punto del polígono respecto al origen en el centro del panel
		
		//Radio:		
		int r = centro_y - 2*VERTICAL_SPACE;
		
		// Ángulo. 		
		float beta = (float)Math.PI/2;	
		
		//Ángulo entre dos vértices del polígono		
		float alfa = 2*(float)Math.PI/n; 		
		
		//Cálculo del resto de puntos para este polígoono en coordenada S1
		for (int j=1; j < n; j++){			
			result[0][j] = centro_x + (int)(r*Math.cos(beta + j*alfa));			
			result[1][j] = centro_y - (int)(r*Math.sin(beta + j*alfa));		
		}		
		
		return result;	
	
	}
}