package Practica9_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class CalculadoraExample extends JFrame{

//JTextField jtf;
SpecialJTextField jtf;
JButton[] botones;

//Es la memoria interna que se maneja con los botones M+, M-, MC y MR
double internalMemory = 0.0;
boolean internalMemoryCleared = true;

//Nos dice si se ha pulsado '=' o una operacion y se ha calculado un resultado temporal
boolean resultadoFinal = false;

//Este double almaena el ultimo calculo temporal realizado
double ultimoResultado = 0;

//Se pone a true si ya se ha pulsado el '.' (no puede haber dos puntos en el screen)
boolean yaHayUnPunto = false;

//Operacion es el codigo de la ultima operacion (si no hay ninguna, es 0).
char operacion ='0';

public CalculadoraExample(){
	//Cremos los Listener del teclado y los botones.
	EscuchadorTeclado ET = new EscuchadorTeclado();
	EscuchadorBotones EB = new EscuchadorBotones();
	
	//Vamos a usar un GraidBagLayout
	GridBagLayout gbd = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	//Nuestro panel es un panel personalizado con fondo en gradiente
	GradientPanel pano = new GradientPanel();
	pano.setLayout(gbd);
	
    //Creamos los 22 botones
	botones = new JButton[22];
    
	for (int i=0; i<10; i++)
    {
        botones[i] = new JButton(new Integer(i).toString());
    }

    botones[10] = new JButton("+");
    botones[11] = new JButton("-");
    botones[12] = new JButton("=");
    botones[12].setActionCommand("=");
    botones[13] = new JButton("MC");
    botones[13].setActionCommand("MC");
    botones[14] = new JButton("M+");
    botones[14].setActionCommand("M+");
    botones[15] = new JButton("M-");
    botones[15].setActionCommand("M-");
    botones[16] = new JButton("MR");
    botones[16].setActionCommand("MR");
    botones[17] = new JButton("C");
    botones[17].setActionCommand("C");
    botones[18] = new JButton("+/-");
    botones[18].setActionCommand("+/-");
    botones[19] = new JButton("/");
    botones[20] = new JButton("x");
    botones[21] = new JButton(".");

    Font fuente = new Font("Dialog", Font.BOLD, 13	);
    
    for (int i=0; i<22; i++){
    	botones[i].setBorder(new RoundedBorder(10));
    	botones[i].setFont(fuente);
    	
    	//A�adimos los listeners
    	botones[i].addKeyListener(ET);
    	botones[i].addActionListener(EB);
    	
        //MouseListener para cuando el raton pasa por encima de los botones 
        botones[i].addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent ev){
                JButton b = (JButton) ev.getSource();
                b.setBackground(Color.GREEN);
            }

            public void mouseExited(MouseEvent ev){
                JButton b = (JButton) ev.getSource();
                b.setBackground(null);
            }
        });
    }

    //JTextField
    //jtf=new JTextField("0",15);
  	jtf = new SpecialJTextField();
    jtf.addKeyListener(ET);
        
    
    //Los colocamos en el GridBaglayout 
    gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridheight = 1;
	gbc.gridwidth = 4;
	gbc.weightx = 90; //Si no lo ponemos solo ocupamos el espacio del texto
	gbc.weighty = 20;
	gbc.fill = GridBagConstraints.NONE;
	pano.add(new JLabel(" "),gbc);
    
	/////////////////////////
	//Position 0,0
	gbc.gridy = 1;
	gbc.weighty = 100;
	gbc.insets = new Insets(5,5,5,5); //Dejamos 5 pixels hasta los extremos de la celda
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.CENTER;

	jtf.setBorder(new RoundedBorder(10));
	jtf.setFont(fuente);
	pano.add(jtf,gbc);
    
    /////////////////////////
    //Position 0,1
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(3,5,3,5);
    pano.add(botones[13], gbc);
    
    /////////////////////////
    //Position 1,1
    gbc.gridx = 1;
    pano.add(botones[14], gbc);
    
    /////////////////////////
    //Position 2,1
    gbc.gridx = 2;
    gbc.ipadx = 10;
    pano.add(botones[15], gbc);

    /////////////////////////
    //Position 3,1
    gbc.gridx = 3;
    gbc.ipadx = 10;
    pano.add(botones[16], gbc);

    /////////////////////////
    //Position 0,2
    gbc.insets=new Insets(3,5,3,5);
    gbc.gridx = 0;
    gbc.ipadx = 10;
    gbc.gridy = 3;
    pano.add(botones[17], gbc);

    /////////////////////////
    //Position 1,2
    gbc.ipadx = 4;
    gbc.gridx = 1;
    pano.add(botones[18], gbc);

    /////////////////////////
    //Position 2,2
    gbc.ipadx = 16;
    gbc.gridx = 2;
    pano.add(botones[19], gbc);
    
    /////////////////////////
    //Position 3,2
    gbc.ipadx = 10;
    gbc.gridx = 3;
    pano.add(botones[20], gbc);
    
    /////////////////////////
    //Position 0,3
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.ipadx = 10;
    pano.add(botones[7], gbc);
    
    /////////////////////////
    //Position 1,3
    gbc.gridx = 1;
    gbc.ipadx = 10;
    pano.add(botones[8], gbc);

    /////////////////////////
    //Position 2,3
    gbc.gridx = 2;
    gbc.ipadx = 10;
    pano.add(botones[9], gbc);

    /////////////////////////
    //Position 3,3  (-)
    gbc.gridx = 3;
    gbc.ipadx = 10;
    pano.add(botones[11], gbc);

    /////////////////////////
    //Position 0,4  (4)
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.ipadx = 10;
    pano.add(botones[4], gbc);

    /////////////////////////
    //Position 1,4  (5)
    gbc.gridx = 1;
    gbc.ipadx = 10;
    pano.add(botones[5], gbc);

    /////////////////////////
    //Position 2,4  (4)
    gbc.gridx = 2;
    gbc.ipadx = 10;
    pano.add(botones[6], gbc);

    /////////////////////////
    //Position 3,4  (+)
    gbc.ipadx = 10;
    gbc.gridx = 3;
    pano.add(botones[10], gbc);

    /////////////////////////
    //Position 0,5  (1)
    gbc.gridx = 0;
    gbc.ipadx = 10;
    gbc.gridy = 6;
    pano.add(botones[1], gbc);

    /////////////////////////
    //Position 1,5  (2)
    gbc.gridx = 1;
    gbc.ipadx = 10;
    pano.add(botones[2], gbc);

    /////////////////////////
    //Position 2,5  (3)
    gbc.ipadx = 10;
    gbc.gridx = 2;
    pano.add(botones[3], gbc);
    
    /////////////////////////
    //Position 3,5  (=)
    gbc.gridx = 3;
    gbc.ipadx = 10;
    gbc.gridheight = 2;
    gbc.fill = GridBagConstraints.BOTH;
    pano.add(botones[12], gbc);

    /////////////////////////
    //Position 0,6  (0)
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    pano.add(botones[0], gbc);

    /////////////////////////
    //Position 2,6  (.)
    gbc.gridx = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    pano.add(botones[21], gbc);
    
    /////////////////////////
    //Position 0,7  (-------)
    gbc.gridx = 0;
   	gbc.gridy = 8;
   	gbc.gridheight = 1;
   	gbc.gridwidth = 4;
   	gbc.weightx = 90; //Si no lo ponemos solo ocupamos el espacio del texto
   	gbc.weighty = 20;
   	gbc.fill = GridBagConstraints.BOTH;
   	pano.add(new JLabel(" "),gbc);
    
    //a�adido del contenedor intermediario en el ContentPane
    getContentPane().add(pano);

    // referenciaci�n de esta instancia de clase
    //     como escuchador de evento para la ventana
    addWindowListener (new WindowAdapter ()
    {
        public void windowClosing (WindowEvent arg0)
        {
            System.exit (0);
        }
    });
}

/////////////////////////////////////////////////////////
//Clase creada especificamente para dotar de border redondeados a los botones.
private static class RoundedBorder implements Border {

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}


////////////////////////////////////////////////////////
// Clase creada para poder disponer de un JPanel con Gradiente de colores.
public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(226,226,226);
        Color color2 = Color.LIGHT_GRAY;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}

//////////////////////////////////////////////////////
public class SpecialJTextField extends JTextField{
	 public SpecialJTextField()
	  {
	    setOpaque(false);
	    setHorizontalAlignment(JTextField.LEFT);
	    setText("0");
	    setCursor(null);
	    this.setEditable(false);
	  }

	 @Override
	 public void setText(String s)
	 {
		 super.setText(s);
	 }
	 
	 @Override
	  protected void paintComponent(Graphics g)
	  {
	    if (isEnabled()) {
	      Graphics2D g2 = (Graphics2D)g.create();
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	      Color dark = new Color(200,220,200);
	      Color light = new Color(200,255,200);
	      GradientPaint paint = new GradientPaint(0.0F, 0.0F, light, 0.0F, getHeight() / 2, dark);
	      g2.setPaint(paint);
	      g2.fillRoundRect(0, 0, getWidth(), getHeight(), 3, 3);
	      g2.setColor(Color.BLACK);
	 
	      g2.dispose();
	    }	     
	    super.paintComponent(g);
	  }
	}



////////////////////////////////////////
//ACTION LISTENER - BOTONES
///////////////////////////////////////
class EscuchadorBotones implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton)e.getSource();
		char c = b.getText().charAt(0);
		
		if (c == 'x') c = '*';
		
		boolean ok=false;

		if ((b.getActionCommand() == "MC")||(b.getActionCommand() =="M-"))
		{
			internalMemoryCleared = true;
		}
		
		if (b.getActionCommand() == "MR")
		{
			if (!internalMemoryCleared)
			{
				//Se almacena como double, pero no siempre lo es...
				if (internalMemory == Math.floor(internalMemory))
					jtf.setText(jtf.getText()+new Integer((int)internalMemory).toString());	
				else jtf.setText(jtf.getText()+new Double(internalMemory).toString());
			}
		}
		
		//Para el M+ primero, si estamos en medio de una operacion, lo calculamos
		//y el resultado es el que metemos en memoria.
		if (b.getActionCommand() == "M+")
		{
			if (!resultadoFinal)
			{
				c='=';
			}
		    internalMemory = new Double(jtf.getText()).doubleValue();
			internalMemoryCleared = false;
		}
		
		if (b.getActionCommand() == "+/-")
		{
			if ((!resultadoFinal) && 
			(!(jtf.getText().startsWith("0") && (jtf.getText().length()==1))))
			{
				if (jtf.getText().startsWith("-"))
				{
					jtf.setText(jtf.getText().substring(1, jtf.getText().length()));
				}
				else
				{
					StringBuilder sb = new StringBuilder("-").append(jtf.getText());
					jtf.setText(sb.toString());
					
				}
			}
			c ='U';//Para que luego no entre en el '+'
		}

	      if (c == '=') 
	      {
	    	  //Puede ocurrir que jtf este vacio
	    	try{
	    	  double tmp = new Double(jtf.getText()).doubleValue();
	    	  ok = true;
	    	  
	    	  if (operacion=='0')
	          {
	          }

	          if (operacion=='+')
	          {
	        		  tmp=tmp+ultimoResultado;
	          }

	          if (operacion=='-')
	          {
	        		  double tmp2=ultimoResultado-tmp;
	        		  tmp=tmp2;
	          }          

	          if (operacion=='*')
	          {
	        	  tmp=tmp*ultimoResultado;
	          }          
	          
	          if (operacion=='/')
	          {
	        	  double tmp2=ultimoResultado/tmp;
	        	  tmp=tmp2;
	          }
	          
	    	  Double d=new Double(tmp);
	    	  String s;
	    	  if (d.intValue()==d.doubleValue()){
	    		  s=new Integer((int)tmp).toString();
	    	  }
	    	  else
	    	  {
	    		  s=new Double(tmp).toString();
	    	  }
	    	  jtf.setText(s);
	          
	          
	    	  resultadoFinal=true;
	    	  operacion='0';
	    	  yaHayUnPunto=false;
	    	  ultimoResultado=tmp;
	    	}
	    	catch(Exception exc)
	    	{
	    	}
	    
	      }
	     
	      

			if (b.getActionCommand()== "M+")
			{
				if (!resultadoFinal)
				{
					c='=';
				}
				
			    internalMemory=new Double(jtf.getText()).doubleValue();
			    
				internalMemoryCleared=false;
			}

			
	///////////////////////////////////////////////
	      if ((c=='+')||(c=='-')||(c=='*')||(c=='/'))
	      { 
	    	  
	        //Puede ocurrir que no lea algo correcto (vacio)
	    	try{  
	    	 double tmp=new Double(jtf.getText()).doubleValue();
	 	 	 ok=true;
	 	 	 
	    	 if (!resultadoFinal)
	    	 {
	    	  if (operacion=='+')
	          {
	    			  tmp=tmp+ultimoResultado;
	    			  resultadoFinal=true;
	          }

	          if (operacion=='-')
	          {
	        	  double tmp2=ultimoResultado-tmp;
	        	  tmp=tmp2;
	        	  resultadoFinal=true;
	          }          

	          if (operacion=='*')
	          {
	        	  tmp=tmp*ultimoResultado;
	        	  resultadoFinal=true;
	          }          
	          
	          if (operacion=='/')
	          {
	        	  double tmp2=ultimoResultado/tmp;
	        	  tmp=tmp2;
	        	  resultadoFinal=true;
	          }
	          
	    	  Double d=new Double(tmp);
	    	  String s;
	    	  if (d.intValue()==d.doubleValue()){
	    		  s=new Integer((int)tmp).toString();
	    	  }
	    	  else
	    	  {
	    		  s=new Double(tmp).toString();
	    	  }
	    	  
	    	  jtf.setText(s);

	    	  if (operacion=='0')
	    	  {
	    		  jtf.setText("");
	    		  yaHayUnPunto=false;
	    	  }

	    	  ultimoResultado=tmp;
	    	 }
	    	 	
	    	// e.consume();
	    	 operacion=c;
	    	}
	    	catch (Exception exc)
	    	{
	    		
	    	}
	      }
	   
	/////////////////////////////////////////////////
	      if ((c=='.') || (c==','))
	      {
	    	  ok=true;
	    	
	    	if (jtf.getText().startsWith("0") && jtf.getText().length()==1) jtf.setText("0");
	        
	    	  
	    	  //no podemos poner dos puntos
	    	  if (!yaHayUnPunto) 
	    	  {
	    		  if (resultadoFinal)
	        	  {  
	    			  jtf.setText("0.");
	    			  resultadoFinal=false;
	        	  }
	    		  else
	    		  {
	    			  jtf.setText(jtf.getText()+'.');
	    			  
	    		  }
	    		  yaHayUnPunto=true;
	    	  }
	      }

	///////////////////////////////////////////////
	      if ((c>='0')&&(c<='9'))
	      {
	    	//Si en el JTextField habia un cero incialmente, lo sustituye por lo que pongamos
	          if (jtf.getText().startsWith("0") && jtf.getText().length()==1) jtf.setText("");
	          
	    	  ok=true;
	    	  
	   		  if (resultadoFinal)
	    	  {
	     			jtf.setText(new Character(c).toString());
	  	            yaHayUnPunto=false;
	    	  }
	   		  else
	   		  {
	   			jtf.setText(jtf.getText()+c);
	   		  }
	    	  resultadoFinal=false;
	      }
	      
	///////////////////////////////////////////////
	      if ((c=='C')||(c=='c'))
	      {
	    	  ok=true;
	    	  jtf.setText("0");
	    	  yaHayUnPunto=false;
	    	  operacion='0';
	    	  resultadoFinal=false;
	    	  //e.consume();
	      }
	      
	      //if (!ok) e.consume();
	}
}


///////////////////////////////////////////
//   KEYLISTENER
///////////////////////////////////////////
class EscuchadorTeclado extends KeyAdapter
{
    public void keyTyped(KeyEvent e) {
      char c = e.getKeyChar();
      boolean ok=false;
      
      if (c == '=') {
    	  
    	try{  
    	  double tmp=new Double(jtf.getText()).doubleValue();
    	  ok=true;
    	  
    	  if (operacion=='0')
          {
          }

          if (operacion=='+')
          {
        		  tmp=tmp+ultimoResultado;
          }

          if (operacion=='-')
          {
        		  double tmp2=ultimoResultado-tmp;
        		  tmp=tmp2;
          }          

          if (operacion=='*')
          {
        	  tmp=tmp*ultimoResultado;
          }          
          
          if (operacion=='/')
          {
        	  double tmp2=ultimoResultado/tmp;
        	  tmp=tmp2;
          }
          
    	  Double d=new Double(tmp);
    	  String s;
    	  if (d.intValue()==d.doubleValue()){
    		  s=new Integer((int)tmp).toString();
    	  }
    	  else
    	  {
    		  s=new Double(tmp).toString();
    	  }
    	  jtf.setText(s);
          
          
    	  resultadoFinal=true;
    	  operacion='0';
    	  yaHayUnPunto=false;
    	  ultimoResultado=tmp;
          e.consume();
    	}
    	catch (Exception exc)
    	{
    		
    	}
      }
      
///////////////////////////////////////////////
      if ((c=='+')||(c=='-')||(c=='*')||(c=='/'))
      { 
    	try{
    	 double tmp=new Double(jtf.getText()).doubleValue();
 	 	 ok=true;
 	 	 
    	 if (!resultadoFinal)
    	 {
    	  if (operacion=='+')
          {
    			  tmp=tmp+ultimoResultado;
    			  resultadoFinal=true;
          }

          if (operacion=='-')
          {
        	  double tmp2=ultimoResultado-tmp;
        	  tmp=tmp2;
        	  resultadoFinal=true;
          }          

          if (operacion=='*')
          {
        	  tmp=tmp*ultimoResultado;
        	  resultadoFinal=true;
          }          
          
          if (operacion=='/')
          {
        	  double tmp2=ultimoResultado/tmp;
        	  tmp=tmp2;
        	  resultadoFinal=true;
          }
          
    	  Double d=new Double(tmp);
    	  String s;
    	  if (d.intValue()==d.doubleValue()){
    		  s=new Integer((int)tmp).toString();
    	  }
    	  else
    	  {
    		  s=new Double(tmp).toString();
    	  }
    	  
    	  
    	  jtf.setText(s);

    	  if (operacion=='0')
    	  {
    		  jtf.setText("");
    		  yaHayUnPunto=false;
    	  }

    	  ultimoResultado=tmp;
    	 }
    	 	
    	 e.consume();
    	 operacion=c;
    	}
    	catch (Exception exc)
    	{
    		
    	}
      }
   
/////////////////////////////////////////////////
      if ((c=='.') || (c==','))
      {
    	  ok=true;
    	
    	if (jtf.getText().startsWith("0") && jtf.getText().length()==1) jtf.setText("0");
        
    	//JSpecialTextField
        jtf.setText(jtf.getText()+c);
        
    	  
    	  //no podemos poner dos puntos
    	  if (yaHayUnPunto) 
    		  e.consume();
    	  else {
    		  if (resultadoFinal)
        	  {  
    			  jtf.setText("0");
    			  
    			  //JSpecialTextField
    	          jtf.setText(jtf.getText()+'.');
    	          
    			  resultadoFinal=false;
        	  }
    		  
    		  yaHayUnPunto=true;
    	  }
    	  
    	  if (c==',')
    	  {
    		  e.consume();
    		  jtf.setText(jtf.getText()+'.');
    	  }
      }

///////////////////////////////////////////////
      if ((c>='0')&&(c<='9'))
      {
    	//Si en el JTextField habia un cero incialmente, lo sustituye por lo que pongamos
          if (jtf.getText().startsWith("0") && jtf.getText().length()==1) jtf.setText("");
          
          //JSpecialTextField
          jtf.setText(jtf.getText()+c);
          
    	  ok=true;
    	  
   		  if (resultadoFinal)
    		  {
     			  jtf.setText("");
     			  
     			 //JSpecialTextField
     	          jtf.setText(jtf.getText()+c);
     	          
  	            yaHayUnPunto=false;
    		  }
    	  resultadoFinal=false;
      }
      
///////////////////////////////////////////////
      if ((c=='C')||(c=='c'))
      {
    	  ok=true;
    	  jtf.setText("0");
    	  yaHayUnPunto=false;
    	  operacion='0';
    	  resultadoFinal=false;
    	  e.consume();
      }
      
      if (!ok) e.consume();
      
      
     }
};

public static void main(String args[])
{
    CalculadoraExample e=new CalculadoraExample();
    e.setBounds (0, 0, 250, 350);
    e.setVisible (true);
}

}