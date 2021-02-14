package Practica1Final_2;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;


public class MainTest {

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

	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMainSimpleSeSaleTablero() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"1\n" + 
								"B 8 26 15\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";



		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); 
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un tren que va hacia izq se choca con un tren que va hacia abajo.
	@Test
	public void testMain2() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"2\n" + 
								"B 6 2 20\n" +
								"I 7 4 18\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . X . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";


		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);

		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test que nos pasó adolfo
	@Test
	public void testMainAdolf() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +	 
								"10\n" + 
								"D 9 11 23\n" +
								"A 8 11 17\n" +
								"A 5 15 15\n" +
								"A 5 15 8\n" +
								"B 9 23 13\n" +
								"A 6 23 6\n" +
								"D 9 8 9\n" +
								"I 13 17 0\n" +
								"A 12 13 11\n" +
								"I 5 20 9\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = "   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . X . . . X . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . X . X . . . . . . . X . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";
	


		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); 
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testMainAdolfDosVeces() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"10\n" + 
								"D 9 11 23\n" +
								"A 8 11 17\n" +
								"A 5 15 15\n" +
								"A 5 15 8\n" +
								"B 9 23 13\n" +
								"A 6 23 6\n" +
								"D 9 8 9\n" +
								"I 13 17 0\n" +
								"A 12 13 11\n" +
								"I 5 20 9\n" +
								"30 30\n" +
								"10\n" + 
								"D 9 11 23\n" +
								"A 8 11 17\n" +
								"A 5 15 15\n" +
								"A 5 15 8\n" +
								"B 9 23 13\n" +
								"A 6 23 6\n" +
								"D 9 8 9\n" +
								"I 13 17 0\n" +
								"A 12 13 11\n" +
								"I 5 20 9\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . X . . . X . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . X . X . . . . . . . X . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n"															   +
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . X . . . X . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . X . X . . . . . . . X . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";


		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia arriba que se sale del tablero.
	@Test
	public void testMainSimpleSeSaleTablero1() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" + 
								"1\n" + 
								"A 8 27 10\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = "   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia izquierda que se sale del tablero.
	@Test
	public void testMainSimpleSeSaleTablero2() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +	
								"1\n" + 
								"I 8 10 10\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia derecha que se sale del tablero.
	@Test
	public void testMainSimpleSeSaleTablero3() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"1\n" + 
								"D 8 10 10\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMainEjemploSimpleAgora() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"5\n" + 
								"B 4 1 3\n" +
								"A 6 9 5\n" +
								"A 5 5 4\n" +
								"I 3 6 0\n" +
								"D 5 4 7\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . X . . . X . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . X . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testMain0Con0() {	
		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +	
								"2\n" + 
								"B 4 3 26\n" +
								"B 3 3 23\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testMain0Con1() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"2\n" + 
								"B 4 3 26\n" +
								"A 3 3 25\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMain0Con2() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"2\n" + 
								"B 4 3 26\n" +
								"I 6 1 25\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMain0Con3() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"2\n" + 
								"B 4 3 26\n" +
								"D 5 4 25\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMain0Con3ConRemanente() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"3\n" + 
								"B 4 3 26\n" +
								"D 5 4 25\n"+
								"I 10 5 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	//Test basico. Un solo tren hacia abajo que se sale del tablero.
	@Test
	public void testMainMultiplesRemanentes() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"8\n" + 
								"B 6 1 22\n" +
								"B 3 4 27\n"+
								"A 2 1 20\n" +
								"A 5 9 28\n"+
								"I 4 4 24\n" +
								"D 5 4 21\n"+
								"D 2 7 28\n" +
								"D 2 7 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
				"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
				"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"28 . . . . . . . . . X . . . . . . . . . . . . . . . . . . . .\n" +
				"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"26 . . . . . . . . . X . . . . . . . . . . . . . . . . . . . .\n" +
				"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"24 . X . . X . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"21 . X . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
				"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testMainTrenColaFueraDelTablero() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"1\n" + 
								"I 10 21 29\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest =  	"Conjunto de trenes incorrecto."+
										"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}



	@Test
	public void testConjuntoCorrectoPeroUnTrenMas() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"9\n" + 
								"I 10 0 27\n"+
								"B 1 1 29\n" +
								"B 1 4 29\n" +
								"B 1 8 29\n" +
								"D 1 1 25\n"+
								"D 1 4 26\n" +
								"A 5 0 26\n"+
								"A 5 3 26\n" +
								"A 5 7 26\n" +
								"A 5 7 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 X X . X X . . X X . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . X . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n"+
						"Conjunto de trenes incorrecto."+
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testEntradaSinSentido() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"A\n" + 
								"I 30 0 29\n" +
								"I 30 0 28\n" +
								"I 30 0 27\n" +
								"I 11 0 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest =  	"Conjunto de trenes incorrecto."+
										"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testSupera100Unidades() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"4\n" + 
								"I 30 0 29\n" +
								"I 30 0 28\n" +
								"I 30 0 27\n" +
								"I 11 0 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest =  	"Conjunto de trenes incorrecto."+
										"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testSimulacionAMedias() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"9\n" + 
								"I 30 0 29\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest =  	"Conjunto de trenes incorrecto."+
										"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testEntradaUnaSolaLinea() {

		//ENTRADA TEST
		String entradaTest = "30 30 2 I 1 2 2 D 1 27 27\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest =  	"Conjunto de trenes incorrecto."+
										"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}


	@Test
	public void testJusto100Unidades() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"4\n" + 
								"I 30 0 29\n" +
								"I 30 0 28\n" +
								"I 30 0 27\n" +
								"I 10 0 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void test3SimulacionesUltimaIncorrecta() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"4\n" + 
								"I 30 0 29\n" +
								"I 30 0 28\n" +
								"I 30 0 27\n" +
								"I 10 0 26\n" + 
								"30 30\n" +
								"10\n" + 
								"A 30 5 29\n" +
								"I 1 11 27\n"+
								"I 1 8 24\n" +
								"I 1 6 22\n"+
								"I 1 11 18\n" +
								"D 1 0 28\n"+
								"D 1 1 26\n" +
								"D 1 3 23\n"+
								"D 1 3 21\n" +
								"D 1 1 19\n" +
								"30 30\n" +
								"2\n" + 
								"I 10 1 20\n" +
								"A 10 5 21\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
				"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n"+
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n"+
						"Conjunto de trenes incorrecto."+
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}


	@Test
	public void testFinalRemanentes() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"9\n" + 
								"I 10 0 27\n"+
								"B 1 1 29\n" +
								"B 1 4 29\n" +
								"B 1 8 29\n" +
								"D 1 1 25\n"+
								"D 1 4 26\n" +
								"A 5 0 26\n"+
								"A 5 3 26\n" +
								"A 5 7 26\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 X X . X X . . X X . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . X . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . X . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

	@Test
	public void testColisionCabezaYCola() {

		//ENTRADA TEST
		String entradaTest = 	"30 30\n" +
								"4\n" + 
								"A 3 1 27\n"+
								"A 3 2 26\n" +
								"A 3 5 27\n" +
								"I 5 1 28\n";
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		//SALIDA TEST
		String salidaEsperadaTest = 
						"   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\n" +
						"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\n" +
						"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"28 . X X . . X . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"23 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"09 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
						"\r\n";

		//Redirigimos la salida estándar
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest);
		System.setOut(nuevo_out);
		try {
			Main.main(new String[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		//El test tendrá éxito si la salida real es igual a la esperada
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
}


