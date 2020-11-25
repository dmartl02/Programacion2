package Practica5;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SaltosCaballoTest {

private static InputStream in;
private static PrintStream out;

	@Before
	public void ejecutaAntesDeCadaTest() throws Exception {
		in = System.in;
		out = System.out;
	}
	

	@After
	public void ejecutaDespuésDeCadaTest() throws Exception {
		System.setIn(in);
		System.setOut(out);
	}
	
	
	@Test
	public void testMain1_25P() {
		String entradaTest = "8 1 1 5 3\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "[[1 1], [3 2], [5 3]]\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); 
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	
	@Test
	public void testMain2_25P() {
		String entradaTest = "0 0 0 0 0\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "[]\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	
	@Test
	public void testMain3_25P() {
		String entradaTest = "3 0 0 0 0\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "[0 0]\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	
	@Test
	public void testMain4_25P() {
		String entradaTest = "4 0 0 2 2\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "[[1 2], [2 0], [0 1], [2 2]]\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
}

