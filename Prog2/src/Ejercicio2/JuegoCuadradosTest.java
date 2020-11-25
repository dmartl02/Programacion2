package Ejercicio2;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.print.attribute.standard.NumberOfDocuments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Practica4.NReinas;
import Practica5.SaltosCaballo;

public class JuegoCuadradosTest {

	private static InputStream in;
	private static PrintStream out;
	private final JuegoCuadrados juego = new JuegoCuadrados();

	
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
	public void testPuntosValidos() {
		String entradaTest = "5\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "5\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	@Test
	public void testPuntosExcesivos() {
		String entradaTest = "9\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "9\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	@Test
	public void testPuntosInsuficientes() {
		String entradaTest = "-1\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "-1\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
	
	@Test
	public void numeroLineasCorrecto() {
		String entradaTest = "5\n";
		
		InputStream nuevo_in = new ByteArrayInputStream(entradaTest.getBytes());
		System.setIn(nuevo_in);
		
		String salidaEsperadaTest = "5\n";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream();
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		SaltosCaballo.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}

}