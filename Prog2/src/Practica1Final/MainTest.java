/*
package Practica1Final;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainTest {

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
	public void testCorrectInput() {
		//10 trenes, 10 líneas
			String entradaTest = 	"10\r\n" + 
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
		
		String salidaEsperadaTest = "   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2\r\n" + 
									"   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9\r\n" + 
									"29 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"28 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"27 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"26 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"25 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"24 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"23 . . . . . . . . . . . X . . . X . . . . . . . . . . . . . .\r\n" + 
									"22 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"21 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"20 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"19 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"18 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"17 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"16 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"15 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"14 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"13 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"12 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"11 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"10 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"09 . . . . . . . . . . . . . X . X . . . . . . . X . . . . . .\r\n" + 
									"08 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"07 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"06 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"05 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"04 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"03 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"02 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"01 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\r\n" + 
									"00 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .";
		
		ByteArrayOutputStream salidaRealTest = new ByteArrayOutputStream(); 
		PrintStream nuevo_out = new PrintStream(salidaRealTest); 
		System.setOut(nuevo_out);
		
		Main.main(new String[0]);
		
		assertEquals(salidaEsperadaTest,salidaRealTest.toString());
	}
}
	
/**/