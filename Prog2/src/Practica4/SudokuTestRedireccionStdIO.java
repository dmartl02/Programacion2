package Practica4;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTestRedireccionStdIO {

private static InputStream in;
private static PrintStream out;

	/**
	* Este método se ejecuta antes de la ejecución de cada test.
	* Permite almacenar los flujos que tiene la máquina java
	* para la entrada y salida estándar
	* @throws Exception
	*/
	@Before
	public void realizaAntesDeCadaTest() throws Exception {
	in = System.in;
	out = System.out;
	}
	
	/**
	* Este método se ejecuta después de cada test. Permite restaurar
	* los flujos de entrada y salida estándar
	* @throws Exception
	*/
	@After
	public void realizaDespuésDeCadaTest() throws Exception {
	System.setIn(in);
	System.setOut(out);
	}
	
	@Test
	public void testMain1() {
	
	//Cadenas que definen el test a realizar
	//Observar que hay un final de línea al final de la cadena
	String entradaTest = "0 0 0 0 0 0 8 0 7\n" + 
			"3 4 0 6 0 0 9 0 0\n" + 
			"8 9 0 2 0 5 0 0 0\n" + 
			"6 7 2 4 0 8 3 0 0\n" + 
			"0 3 0 0 6 0 0 0 4\n" + 
			"0 0 0 3 0 0 0 0 0\n" + 
			"0 0 0 0 0 0 5 0 0\n" + 
			"5 0 0 7 0 9 6 8 0\n" + 
			"0 2 6 8 5 0 7 0 0";
	String salidaEsperadaTest = "2 6 5 9 3 4 8 1 7 \n" + 
			"3 4 7 6 8 1 9 2 5 \n" + 
			"8 9 1 2 7 5 4 3 6 \n" + 
			"6 7 2 4 1 8 3 5 9 \n" + 
			"9 3 8 5 6 2 1 7 4 \n" + 
			"1 5 4 3 9 7 2 6 8 \n" + 
			"7 8 9 1 2 6 5 4 3 \n" + 
			"5 1 3 7 4 9 6 8 2 \n" + 
			"4 2 6 8 5 3 7 9 1 \n" + 
			"\n";
	
	//Redirigimos la entrada estándar
	InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
	System.setIn(nuevo_in);
	
	//Redirigimos la salida estándar
	ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
	PrintStream nuevo_out = new PrintStream(salidaRealTest);
	System.setOut(nuevo_out);
	
	// Llamo a main con su argumento igual a un array de cadenas vacío
	// Gracias a la redirección de la entrada/salida estándar
	// main leerá de la cadena entradaTest y escribirá al
	// array de bytes salidaRealTest
	Sudoku.main(new String[0]);
	
	// El test tendrá éxito si la salida real es igual a la esperada
	assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	
	}
	// Un segundo test se realiza fácilmente cambiando el valor
	// de las cadenas de entrada y esperadas
	@Test
	public void testMain2() {
	
	//Cadenas que definen el test a realizar
	//Observar que hay un final de línea al final de la cadena
	String entradaTest = "0 0 8 5 0 9 4 0 0\n" + 
			"6 0 4 0 0 0 5 0 0\n" + 
			"0 2 0 0 0 0 7 3 8\n" + 
			"0 4 7 2 0 6 0 5 0\n" + 
			"0 0 5 0 0 7 0 0 0\n" + 
			"9 0 0 8 4 0 0 0 7\n" + 
			"0 0 0 0 7 4 3 8 0\n" + 
			"0 5 0 6 8 0 0 0 0\n" + 
			"0 8 0 0 0 0 0 2 0";
	String salidaEsperadaTest = "3 7 8 5 2 9 4 1 6 \n" + 
			"6 1 4 7 3 8 5 9 2 \n" + 
			"5 2 9 4 6 1 7 3 8 \n" + 
			"1 4 7 2 9 6 8 5 3 \n" + 
			"8 6 5 3 1 7 2 4 9 \n" + 
			"9 3 2 8 4 5 1 6 7 \n" + 
			"2 9 6 1 7 4 3 8 5 \n" + 
			"4 5 3 6 8 2 9 7 1 \n" + 
			"7 8 1 9 5 3 6 2 4 \n" + 
			"\n";
	
	//Redirigimos la entrada estándar
	InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
	System.setIn(nuevo_in);
	//Redirigimos la salida estándar
	ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
	PrintStream nuevo_out = new PrintStream(salidaRealTest);
	System.setOut(nuevo_out);
	
	// Llamo a main con su argumento igual a un array de cadenas vacío
	// Gracias a la redirección de la entrada/salida estándar
	// main leerá de la cadena entradaTest y escribirá al
	// array de bytes salidaRealTest
	Sudoku.main(new String[0]);
	
	// El test tendrá éxito si la salida real es igual a la esperada
	   assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
}
