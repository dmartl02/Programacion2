package Practica8;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoPoligono {

	private JFrame frame;
	private JTextField textoNumeroLados;
	private JButton botonIncrementar;
	private JButton botonDecrementar;
	private JLabel labelNumeroLados;
	private JPanel panelAbajo;
	private JLabel labelNombreDelPoligono;
	private JPanel panelCentro;
	

	Poligono poligono = new Poligono();
	
	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPoligono window = new NuevoPoligono();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Create the application.
	public NuevoPoligono() {
		initialize();
	}

	
	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelArriba = new JPanel();
		frame.getContentPane().add(panelArriba, BorderLayout.NORTH);
		
		
		botonIncrementar = new JButton("Incrementar");
		botonIncrementar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poligono.setNumeroDeLados(poligono.getNumeroDeLados()+1);
				textoNumeroLados.setText(Integer.toString(poligono.getNumeroDeLados()));
				labelNombreDelPoligono.setText(poligono.nombreDelPoligono());
				
				if(poligono.getNumeroDeLados() == poligono.getMaximoDeLados()) {
					botonIncrementar.setEnabled(false);
				}
				
				if(!botonDecrementar.isEnabled()) {
					botonDecrementar.setEnabled(true);
				}
				
				panelCentro.repaint();
			}
		});
		panelArriba.add(botonIncrementar);
		
		
		botonDecrementar = new JButton("Decrementar");
		botonDecrementar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poligono.setNumeroDeLados(poligono.getNumeroDeLados()-1);
				textoNumeroLados.setText(Integer.toString(poligono.getNumeroDeLados()));
				labelNombreDelPoligono.setText(poligono.nombreDelPoligono());
				
				if(poligono.getNumeroDeLados() == poligono.getMinimoDeLados()) {
					botonDecrementar.setEnabled(false);
				}
				
				if(!botonIncrementar.isEnabled()) {
					botonIncrementar.setEnabled(true);
				}
				
				panelCentro.repaint();
				
			}
		});
		panelArriba.add(botonDecrementar);
		
		
		labelNumeroLados = new JLabel("Numero de lados");
		labelNumeroLados.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(labelNumeroLados);
		
		
		textoNumeroLados = new JTextField();
		textoNumeroLados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valorViejo = poligono.getNumeroDeLados();
				int valorNuevo;
				
				try {
					valorNuevo = Integer.valueOf(textoNumeroLados.getText());
					if((valorNuevo >= poligono.getMinimoDeLados()) && (valorNuevo <= poligono.getMaximoDeLados())) {
						poligono.setNumeroDeLados(valorNuevo);
						labelNombreDelPoligono.setText(poligono.nombreDelPoligono());
						botonIncrementar.setEnabled(poligono.getNumeroDeLados() != poligono.getMaximoDeLados());
						botonDecrementar.setEnabled(poligono.getNumeroDeLados() != poligono.getMinimoDeLados());
						panelCentro.repaint();
					} else {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "El número de lados del polígono debe estar entre " 
							+ poligono.getMinimoDeLados() + " y " + poligono.getMaximoDeLados());
					textoNumeroLados.setText(""+valorViejo);
				}
			}
		});
		
		textoNumeroLados.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(textoNumeroLados);
		textoNumeroLados.setColumns(3);
		
		panelAbajo = new JPanel();
		frame.getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		
		labelNombreDelPoligono = new JLabel(poligono.nombreDelPoligono());
		labelNombreDelPoligono.setHorizontalAlignment(SwingConstants.CENTER);
		panelAbajo.add(labelNombreDelPoligono);
		
		
		panelCentro = new PoligonoDibujo();
		((PoligonoDibujo) panelCentro).setPoligono(poligono);
		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		
	}
	
}
