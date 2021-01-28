package Practica1Final_2;

import java.util.ArrayList;

import Practica1Final_2.Board;
import Practica1Final_2.Position;
import Practica1Final_2.Train;

public class Game {

	private Board board;
	private ArrayList<Train> trains;
	private int maximumTrains;

	public Game(Board board, ArrayList<Train> trains) {
		this.board = board;
		this.trains = trains;
	}

	public void start() {
		int index = 0;
		Train temp;
		Position collision;
		boolean crashed = false;

//		board.printWithTrains(trains);
//		board.print();

		orderTrains();
		this.maximumTrains = trains.size();

		while (!trains.isEmpty()) {
			temp = trains.get(index);
			
			collision = new Position(temp.getPosition().getX(), temp.getPosition().getY());
			crashed = detectCollision(index);
			
			if (crashed) {
				board.setCollision(collision);
			}

//			board.printWithTrains(trains);
//			board.print();
			
			temp.move();
			
//			if (crashed) {
//				board.setCollision(collision);
//			}
			
			if (temp.getWagons() <= 0) {
				trains.remove(index);
				this.maximumTrains = trains.size();
			} else {
				index++;
			}

			if (index == maximumTrains) {
				index = 0;
			}
		}

		board.print();
	}

	private void orderTrains() {
		ArrayList<Train> orderedTrains = new ArrayList<Train>();

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'B') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'A') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'I') {
				orderedTrains.add(trains.get(i));
			}
		}

		for (int i = 0; i < trains.size(); i++) {
			if (trains.get(i).getDirection() == 'D') {
				orderedTrains.add(trains.get(i));
			}
		}

		trains = orderedTrains;
	}

	public boolean detectCollisionNoMoving(int index) {		
		Train temp;
		Train movingTrain = trains.get(index);

		for (int i = 0; i < trains.size()-1; i++) {
			if (i != index) {
				temp = trains.get(i);
				
				if (thereIsATrain(movingTrain.getPosition(), temp)) {
					//movingTrain.moveWhenCollision();
					//temp.moveWhenCollision();
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean detectCollision(int index) {
		Train temp;
		Train movingTrain = trains.get(index);

		for (int i = 0; i < trains.size(); i++) {
			if (i != index) {
				temp = trains.get(i);
				
				if (thereIsATrain(movingTrain.getPosition(), temp)) {
					movingTrain.moveWhenCollision();
					temp.moveWhenCollision();

					return true;
				}
			}
		}

		if (thereIsCollision(movingTrain.getPosition())) {
			movingTrain.moveWhenCollision();
			return true;
		}

		return false;
	}
	

	private boolean thereIsATrain(Position movingTrain, Train current) {
		int movingTrainX = movingTrain.getX();
		int movingTrainY = movingTrain.getY();

		char currentDirection = current.getDirection();
		int currentWagons = current.getWagons();
		Position currentPosition = current.getPosition();

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		for (int i = 0; i < currentWagons; i++) {
			if ((currentX == movingTrainX) && (currentY == movingTrainY)) { 
				currentPosition.setX(movingTrainX);
				currentPosition.setY(movingTrainY);
			
				return true;
			}
			
			switch (currentDirection) {
			case 'B':
				currentX--;
				break;

			case 'A':
				currentX++;
				break;

			case 'I':
				currentY++;
				break;

			case 'D':
				currentY--;
				break;
			}
		}

		return false;
	}

	private boolean thereIsCollision(Position position) {
		if (board.getBoard()[position.getX()][position.getY()] == 'X') {
			return true;
		}

		return false;
	}
	
}

