package Practica1Final;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static ArrayList<Train> trains;
	private static Board board;
	private static Train train;
	private static Position position;
	private static Position positionCataclismo;

	private static char directionCharacter;
	private static String[] splittedTrain;
	private static int numberOfTrains;
	private static int wagons, positionX, positionY;
	private static String trainKeyboard;
	private static char cataclismoCharacter;
	private static int wagonsCataclismo, positionXCataclismo, positionYCataclismo;


	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		board = new Board();
		
		boolean canPlay = true;

		while (canPlay){
			trains = new ArrayList<Train>(numberOfTrains);

			restartGame();
			
			Game game = new Game(board, trains);
			
			try {
				numberOfTrains = keyboard.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("Conjunto de trenes incorrecto.");
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
					System.out.println("Conjunto de trenes incorrecto.");
					System.out.println();
					System.exit(0);
				}
				
				String positionYKeyboard = splittedTrain[2];
				positionY = Integer.parseInt(positionYKeyboard);

				String positionXKeyboard = splittedTrain[3];
				positionX = Integer.parseInt(positionXKeyboard);
				positionX = 29 - positionX;
				
				
				String cataclismoKeyboard = splittedTrain[0];
				cataclismoCharacter = cataclismoKeyboard.charAt(0);
				
				String wagonsCataclismoKeyboard = splittedTrain[1];
				wagonsCataclismo = Integer.parseInt(wagonsCataclismoKeyboard);
				
				String positionYCatKeyboard = splittedTrain[2];
				positionYCataclismo = Integer.parseInt(positionYCatKeyboard);

				String positionXCatKeyboard = splittedTrain[3];
				positionXCataclismo = Integer.parseInt(positionXCatKeyboard);
				positionXCataclismo = 29 - positionXCataclismo;

				position = new Position(positionX, positionY);
				
				positionCataclismo = new Position(positionXCataclismo, positionYCataclismo);
				board.setCataclismo(positionCataclismo);
				
				checkDirection();
				checkNumberOfWagons();
				checkPosition();
				checkInsideTheBoard();

			
				train = new Train(directionCharacter, position, wagons);
				trains.add(train);
				
				//Detect if there are collisions when creating the trains
				if ((trains.size() > 1) && (game.detectCollisionNoMoving(i))) {
					System.out.println("Conjunto de trenes incorrecto.");
					System.out.println();
					System.exit(0);
				}
			}
			
			System.out.println();
			game.start();
		}
	}

	
	private static void restartGame() {
		trains.clear();
		board = new Board();
	}

	
	public static void checkNumberOfTrains() {
		if((numberOfTrains > 10) || (numberOfTrains < 1)) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkDirection() {
		if((directionCharacter != 'B') && (directionCharacter != 'A') && (directionCharacter != 'I') && (directionCharacter != 'D') && (cataclismoCharacter != 'C')) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkNumberOfWagons() {
		if((wagons < 1) || (wagons > 30)) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkPosition() {
		if((positionX < 0) || (positionX > 29) || (positionY < 0) || (positionY > 29) || (positionXCataclismo < 0) || (positionXCataclismo > 29) || (positionYCataclismo < 0) || (positionYCataclismo > 29)) {
			System.out.println("Conjunto de trenes incorrecto.");
			System.out.println();
			System.exit(0);
		}
	}

	
	public static void checkInsideTheBoard() {
		if(directionCharacter == 'B'){
			if((positionX - wagons) > 29) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'A'){
			if((positionX + wagons) < 0) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'I'){
			if((positionY + wagons) < 0 ) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}

		} else if(directionCharacter == 'D'){
			if((positionY - wagons) > 29) {
				System.out.println("Conjunto de trenes incorrecto.");
				System.out.println();
				System.exit(0);
			}
		}
	}

	
	public static ArrayList<Train> getTrains() {
		return trains;
	}
	
	public static Position getPositionCataclismo() {
		return positionCataclismo;
	}
	public static int getWagonsCataclismo() {
		return wagonsCataclismo;
	}
}

/*
10
D 9 11 23
A 8 11 17
A 5 15 15
A 5 15 8
B 9 23 13
A 6 23 6
D 9 8 9
I 13 17 0
A 12 13 11
I 5 20 9
*/