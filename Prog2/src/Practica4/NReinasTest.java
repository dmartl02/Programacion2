package Practica4;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NReinasTest {

private static InputStream in;
private static PrintStream out;

	/**
	* Este m�todo se ejecuta antes de la ejecuci�n de cada test.
	* Permite almacenar los flujos que tiene la m�quina java
	* para la entrada y salida est�ndar
	* @throws Exception
	*/
	
	@Before
	public void ejecutaAntesDeCadaTest() throws Exception {
		in = System.in;
		out = System.out;
	}
	
	
	/**
	* Este m�todo se ejecuta despu�s de cada test. Permite restaurar
	* los flujos de entrada y salida est�ndar
	* @throws Exception
	*/
	
	@After
	public void ejecutaDespu�sDeCadaTest() throws Exception {
		System.setIn(in);
		System.setOut(out);
	}
	
	@Test
	public void testMain1() {
		//Cadenas que definen el test a realizar
		//Observar que hay un final de l�nea al final de la cadena
		//String entradaTest = "java";
		//String salidaEsperadaTest = "hola java\n";
		
		//Redirigimos la entrada est�ndar
		//InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		//System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "(1,3,0,2)\n";
		
		//Redirigimos la salida est�ndar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); //Almacena la salida que genera el programa
		PrintStream nuevo_out = new PrintStream(salidaRealTest); //PrintStream se usa para codificar por caracteres
		System.setOut(nuevo_out);
		
		// Llamo a main con su argumento igual a un array de cadenas vac�o
		// Gracias a la redirecci�n de la entrada/salida est�ndar main leer� de la cadena entradaTest 
		// y escribir� al array de bytes salidaRealTest
		String[] args = {"4"};
		NReinas.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	
	}
	
	
	@Test
	public void testMain2() {
		//Cadenas que definen el test a realizar
		//Observar que hay un final de l�nea al final de la cadena
		//String entradaTest = "java";
		//String salidaEsperadaTest = "hola java\n";
		
		//Redirigimos la entrada est�ndar
		//InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		//System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "(1,3,5,0,2,4)\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); //Almacena la salida que genera el programa
		PrintStream nuevo_out = new PrintStream(salidaRealTest); //PrintStream se usa para codificar por caracteres
		System.setOut(nuevo_out);
		
		String[] args = {"6"};
		NReinas.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	
	}
	
	@Test
	public void testMain3() {
		//Cadenas que definen el test a realizar
		//Observar que hay un final de l�nea al final de la cadena
		//String entradaTest = "java";
		//String salidaEsperadaTest = "hola java\n";
		
		//Redirigimos la entrada est�ndar
		//InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		//System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "(0,4,7,5,2,6,1,3)\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); //Almacena la salida que genera el programa
		PrintStream nuevo_out = new PrintStream(salidaRealTest); //PrintStream se usa para codificar por caracteres
		System.setOut(nuevo_out);
		
		String[] args = {"8"};
		NReinas.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	
	}
}
