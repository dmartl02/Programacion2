package Practica1Final_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Practica1Final_2.Position;

public class Main {

	private static ArrayList<Train> trains;
	private static Board board;
	private static Train train;
	private static Position position;

	private static char directionCharacter;
	private static int rows, columns;
	private static int numberOfTrains;
	private static int wagons, positionX, positionY;
	private static final int MIN_TRAINS = 1;
	private static final int MIN_ROWS = 10;
	private static final int MAX_ROWS = 100;
	private static final int MIN_COLUMNS = 10;
	private static final int MAX_COLUMNS = 100;
	private static final int MAX_WAGONS = 100; //TODO TODO añadir en la mod
	private static Scanner keyboard;
	private static boolean canPlay = true;
	private static int counterTrains = 0; //TODO TODO rename 
	private static int counterWagons = 0; //TODO TODO añadir en la mod



	public static void main(String[] args) throws IOException {
		keyboard = new Scanner(System.in);

		while (keyboard.hasNext()){
			try {	
				rows = keyboard.nextInt();
				columns = keyboard.nextInt();

				checkNumberOfRows();
				checkNumberOfColumns();

				board = new Board(rows, columns);

				keyboard.nextLine();

				numberOfTrains = keyboard.nextInt();

				checkNumberOfTrains();

				trains = new ArrayList<Train>(numberOfTrains);

				while(counterTrains < numberOfTrains) { //TODO cambiar counterTrains
					keyboard.nextLine();

					String directionKeyboard = keyboard.next();
					directionCharacter = directionKeyboard.charAt(0);

					wagons = keyboard.nextInt();
					counterWagons = counterWagons + wagons; //TODO TODO añadir en la mod

					positionY = keyboard.nextInt();
					positionX = keyboard.nextInt();
					positionX = (rows-1)-positionX;
					position = new Position(positionX, positionY);

					checkDirection();
					checkNumberOfWagons();
					checkNumberOfWagonsTotal();
					checkPosition();
					checkInsideTheBoard();

					train = new Train(directionCharacter, position, wagons, board);
					trains.add(train);
					counterTrains++; //TODO cambiar counterTrains
				}

			} catch(Exception e) {
				canPlay = false;
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}

			Game game = new Game(board, trains);


			if(canPlay && (counterTrains == numberOfTrains)) { //TODO cambiar counterTrains
				game.start();
				counterTrains = 0;
				counterWagons = 0; //TODO TODO añadir en la mod
				restartGame();
			} else {
				canPlay = false;
				keyboard.close();
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}
		}

		keyboard.close();
	}


	private static void restartGame() {
		trains.clear();
		board = new Board(rows, columns);
	}


	public static void checkNumberOfRows() {
		if((rows < MIN_ROWS) || (rows > MAX_ROWS)) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}	
	}


	public static void checkNumberOfColumns() {
		if((columns < MIN_COLUMNS) || (columns > MAX_COLUMNS)) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}	
	}


	public static void checkNumberOfTrains() {
		if(numberOfTrains < MIN_TRAINS) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}
	
	//TODO TODO añadir en la mod
	public static void checkTotalTrains() {
		if(counterTrains < numberOfTrains) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}

	public static void checkDirection() {
		if((directionCharacter != 'B') && (directionCharacter != 'A') && (directionCharacter != 'I') && (directionCharacter != 'D')) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}


	public static void checkNumberOfWagons() {
		if((wagons < 1) || (wagons > 30)) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}

	//TODO TODO añadir en la modificación
	public static void checkNumberOfWagonsTotal() {
		if(counterWagons > MAX_WAGONS) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}


	public static void checkPosition() {
		if((positionX < 0) || (positionX > rows-1) || (positionY < 0) || (positionY > columns-1)) {
			canPlay = false;
			keyboard.close();
			System.out.println("Conjunto de trenes incorrecto.");
			System.exit(0);
		}
	}


	public static void checkInsideTheBoard() {
		if(directionCharacter == 'B'){
			if((positionX - wagons) < -1) {
				canPlay = false;
				keyboard.close();
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}

		} else if(directionCharacter == 'A'){
			if((positionX + wagons) > rows) {
				canPlay = false;
				keyboard.close();
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}

		} else if(directionCharacter == 'I'){
			if((positionY + wagons) > columns) {
				canPlay = false;
				keyboard.close();
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}

		} else if(directionCharacter == 'D'){
			if((positionY - wagons) < -1) {
				canPlay = false;
				keyboard.close();
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}
		}
	}
	
}
