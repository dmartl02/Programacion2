/*package Practica9_1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class TextEditorExample extends JFrame
{
	JPanel pano;
	JTextArea txt;
	JScrollPane despla;
	JMenuBar barra;
	JMenu mnuFichero, mnuEdici�n, mnuSalvaguardar;
	JMenuItem mnuNuevo, mnuAbrir, mnuGuardar, mnuGuardarComo, mnuSalir;
	JMenuItem mnuCopiar, mnuCortar, mnuPegar;
	File fichero;

	public TextEditorExample() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFIle);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setTitle ("Editor de texto");
		setBounds (0,0,300,100);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		// Creacion de los components
		pano = new JPanel();
		pano.setLayout (new BorderLayout());
		txt = new JTextArea();
		despla = new JScrollPane (txt);
		pano.add (despla, BorderLayout.CENTER);
		getContentPane().add(pano);

		// Creacion de los componentes de los men�s
		barra = new JMenuBar ();
		menuFile = new JMenu ("File");
		mnuEdici�n=new JMenu ("Edicion");
		mnuSalvaguardar=new JMenu ("Salvaguardar");
		mnuNuevo=new JMenuItem ("Nuevo");
		mnuAbrir=new JMenuItem ("Abrir");
		mnuGuardar=new JMenuItem ("Guardar");
		mnuGuardar.setEnabled (false);
		mnuGuardarComo=new JMenuItem ("Guardar como");
		mnuCopiar=new JMenuItem ("Copiar");
		mnuCortar=new JMenuItem ("Cortar");
		mnuPegar=new JMenuItem ("Pegar");
		mnuSalir=new JMenuItem ("Salir");
		
		// asociaci�n de los elementos
		barra.add (mnuFichero);
		barra.add (mnuEdici�n);
		mnuFichero.add (mnuNuevo);
		mnuFichero.add (mnuAbrir);
		mnuFichero.add (mnuSalvaguardar);
		mnuSalvaguardar.add (mnuGuardar);
		mnuSalvaguardar.add (mnuGuardarComo);
		mnuFichero.add (new JSeparator());
		mnuFichero.add (mnuSalir);
		mnuEdici�n.add (mnuCopiar);
		mnuEdici�n.add (mnuCortar);
		mnuEdici�n.add (mnuPegar);

		// asociacion del menuS con la JFrame
		setJMenuBar(barra);
		
		// los escuchadores asociados a los diferentes men�s
		mnuNuevo.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0)
			{
				fichero=null;
				txt.setText (" ");
				mnuGuardar.setEnabled (false);
			}
		});

		mnuAbrir.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				JFileChooser dlg;
				dlg=new JFileChooser ( );
				dlg.showDialog (null, "Abrir");
				fichero=dlg.getSelectedFile ();
				
				//leemos el fichero seleccionado
				FileInputStream in;
				try{
					in=new FileInputStream (fichero);
					BufferedReader br;
					br=new BufferedReader (new InputStreamReader (in) );
					String linea;
					txt.setText (" ");
					
					//El contenido que vamos leyendo lo metemos en el JTextArea
					while ((linea=br.readLine() ) !=null)
					{
						//Este \n puede ser diferente (/n/r) en Linux
						txt.append (linea + "\n");
					}
					
					br.close ();
					mnuGuardar.setEnabled (true);
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace ();
				}
				catch (IOException e)
				{
					e.printStackTrace ();
				}
			}
		});

		mnuSalir.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit (0);
			}
		});

		mnuCopiar.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				txt.copy ();
			}
		});

		mnuCortar.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				txt.cut ();
			}
		});

		mnuPegar. addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{	
				txt.paste();
			}
		});

		mnuGuardar.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				try
				{
					PrintWriter pw;
					pw=new PrintWriter (fichero);
					pw.write (txt.getText());
					pw.close ();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace () ;
				}
			}
		});

		mnuGuardarComo.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent arg0)
			{
				try
				{
					JFileChooser dlg;
					dlg=new JFileChooser ();
					dlg.showDialog (null, "guardar como");
					fichero=dlg.getSelectedFile ();
					PrintWriter pw;
					pw=new PrintWriter (fichero);
					pw.write (txt.getText () );
					pw.close ();
				}

				catch (FileNotFoundException e)
				{
					e.printStackTrace ();
				}
			}
		});
	}
	
	public static void main(String args[])
	{
		TextEditor te=new TextEditor();
		te.setBounds(0,0,400,400);
		te.setVisible(true);
	}
}
*/