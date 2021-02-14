package Conv2_1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Conv2_1.Position;

public class Main {

	private static ArrayList<Train> trains;
	private static Board board;
	private static Train train;
	private static Position position;

	private static char directionCharacter;
	private static String[] splittedTrain, splittedBoard;
	private static int rows, columns;
	private static int numberOfTrains;
	private static int wagons, positionX, positionY;
	private static String trainKeyboard, boardKeyboard;
	private static final int MIN_TRAINS = 1;
	private static final int MIN_ROWS = 10;
	private static final int MAX_ROWS = 100;
	private static final int MIN_COLUMNS = 10;
	private static final int MAX_COLUMNS = 100;
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		boolean canPlay = true;

		while (canPlay){
			trains = new ArrayList<Train>(numberOfTrains);
			
			boardKeyboard = keyboard.nextLine();
			splittedBoard = boardKeyboard.split(" ");
			
			try {
				String rowsKeyboard = splittedBoard[0];
				rows = Integer.parseInt(rowsKeyboard);
			} catch (NumberFormatException nfe) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}
			
			try {
				String columnsKeyboard = splittedBoard[1];
				columns = Integer.parseInt(columnsKeyboard);
			} catch (NumberFormatException nfe) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}
			
			checkNumberOfRows();
			checkNumberOfColumns();
		
			restartGame();
			
			Game game = new Game(board, trains);
			
			try {
				numberOfTrains = keyboard.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("Conjunto de trenes incorrecto trains not number.");
				System.out.println();
				System.exit(0);
			}

			keyboard.nextLine();
			checkNumberOfTrains();

			for(int i=0; i<numberOfTrains; i++) {
				trainKeyboard = keyboard.nextLine();
				splittedTrain = trainKeyboard.split(" ");

				String directionKeyboard = splittedTrain[0];
				directionCharacter = directionKeyboard.charAt(0);
				
				try {
					String wagonsKeyboard = splittedTrain[1];
					wagons = Integer.parseInt(wagonsKeyboard);
				} catch (IndexOutOfBoundsException iooe) {
					System.out.println("Conjunto de trenes incorrecto 2.");
					System.out.println();
					System.exit(0);
				}
				
				String positionYKeyboard = splittedTrain[2];
				positionY = Integer.parseInt(positionYKeyboard);

				String positionXKeyboard = splittedTrain[3];
				positionX = Integer.parseInt(positionXKeyboard);
				positionX = (rows-1) - positionX;
				
				position = new Position(positionX, positionY);
				
				checkDirection();
				checkNumberOfWagons();
				checkPosition();
				checkInsideTheBoard();

				train = new Train(directionCharacter, position, wagons, board);
				trains.add(train);
			}
			
			game.start();
		}
	}

	
	private static void restartGame() {
		trains.clear();
		board = new Board(rows, columns);
	}

	
	public static void checkNumberOfRows() {
		if((rows < MIN_ROWS) || (rows > MAX_ROWS)) {
			System.out.println("Conjunto de trenes incorrecto rows.");
			System.out.println();
			System.exit(0);
		}	
	}
	
	
	public static void checkNumberOfColumns() {
		if((columns < MIN_COLUMNS) || (columns > MAX_COLUMNS)) {
			System.out.println("Conjunto de trenes incorrecto columns.");
			System.out.println();
			System.exit(0);
		}	
	}
	
	
	public static void checkNumberOfTrains() {
		if(numberOfTrains < MIN_TRAINS) {
			System.out.println("Conjunto de trenes incorrecto number of trains.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkDirection() {
		if((directionCharacter != 'B') && (directionCharacter != 'A') && (directionCharacter != 'I') && (directionCharacter != 'D')) {
			System.out.println("Conjunto de trenes incorrecto direction.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkNumberOfWagons() {
		if((wagons < 1) || (wagons > 30)) {
			System.out.println("Conjunto de trenes incorrecto wagons.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkPosition() {
		if((positionX < 0) || (positionX > rows-1) || (positionY < 0) || (positionY > columns-1)) {
			System.out.println("Conjunto de trenes incorrecto position.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkInsideTheBoard() {
		if(directionCharacter == 'B'){
			if((positionX - wagons) > rows) {
				System.out.println("Conjunto de trenes incorrecto outside.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'A'){
			if((positionX + wagons) < 0) {
				System.out.println("Conjunto de trenes incorrecto outside.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'I'){
			if((positionY + wagons) < 0 ) {
				System.out.println("Conjunto de trenes incorrecto outside.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'D'){
			if((positionY - wagons) > columns) {
				System.out.println("Conjunto de trenes incorrecto ouside.");
				System.out.println();
				System.exit(0);
			}
		}
	}

	
	public static ArrayList<Train> getTrains() {
		return trains;
	}
}

