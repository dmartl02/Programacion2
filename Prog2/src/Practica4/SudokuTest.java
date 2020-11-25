package Practica4;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;





import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Clase para realizar tests obtendidos de un fichero que
 * sigue la sintaxis de los tests de VPL, de la forma
 * case=
 * input=
 * output=
 * 
 * El contenido de output debe ir entre comillas.
 * @author adolfo
 *
 */

public class SudokuTest {
	static PrintStream out;
	static InputStream in;
	
	static Scanner lee;
	static ByteArrayOutputStream out_real;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpBefore() throws Exception {
		String nombreFicheroTestsVPL = "tests/testsSudokuVPL.txt";
		
		// Fijo el patrón para extraer la entrada y salida del texto
		String patron = "\n*case=|\n*input=|\n*output=";
		lee = new Scanner( new File(nombreFicheroTestsVPL) ).useDelimiter(patron);
	}
	
	@AfterClass
	public static void setUpAfter() throws Exception {
		lee.close();
	}

	@Before
	public void setUpBeforeClass() throws Exception {
		out = System.out;
		in = System.in;
		
		// Ignoro el nombre del test
		lee.next();
		//El siguiente lee.next() obtiene la entrada del test
		InputStream new_in = new ByteArrayInputStream(lee.next().getBytes());
		System.setIn(new_in);
		
		out_real = new ByteArrayOutputStream();
		PrintStream new_out = new PrintStream(out_real);
		System.setOut(new_out);	
	}

	@After
	public void setUpAfterClass() throws Exception {
        System.setIn(in);
        System.setOut(out);
	}

	@Test
	public void test01() throws UnsupportedEncodingException{
		Sudoku.main(new String[0]);
		
		String out_esperada = lee.next();
		String out_actual = out_real.toString("UTF8");

		//Sólo considero el caso en el que el output va entre comillas.
		assertEquals(out_esperada, "\""+out_actual+"\"");
	}

	@Test
	public void test02() throws UnsupportedEncodingException{
		Sudoku.main(new String[0]);
		
		String out_esperada = lee.next();
		String out_actual = out_real.toString("UTF8");

		//Sólo considero el caso en el que el output va entre comillas.
		assertEquals(out_esperada, "\""+out_actual+"\"");
	}
	
	@Test
	public void test03() throws UnsupportedEncodingException{
		Sudoku.main(new String[0]);
		
		String out_esperada = lee.next();
		String out_actual = out_real.toString("UTF8");

		//Sólo considero el caso en el que el output va entre comillas.
		assertEquals(out_esperada, "\""+out_actual+"\"");
	}
//	
//	@Test
//	public void test04() throws UnsupportedEncodingException{
//		Sudoku.main(new String[0]);
//		
//		String out_esperada = lee.next();
//		String out_actual = out_real.toString("UTF8");
//
//		//Sólo considero el caso en el que el output va entre comillas.
//		assertEquals(out_esperada, "\""+out_actual+"\"");
//	}
//	
//	@Ignore @Test
//	public void test05() throws UnsupportedEncodingException{
//		Sudoku.main(new String[0]);
//		
//		String out_esperada = lee.next();
//		String out_actual = out_real.toString("UTF8");
//
//		//Sólo considero el caso en el que el output va entre comillas.
//		assertEquals(out_esperada, "\""+out_actual+"\"");
//	}
}
