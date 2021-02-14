package  Practica1Final_2;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

	private Board board;
	private static ArrayList<Train> trains;
	private int maximumTrains;

	public Game(Board board, ArrayList<Train> trains) {
		this.board = board;
		this.trains = trains;
	}

	public void start() {
		int index = 0;
		Train temp;
		
		
		orderTrains();
		this.maximumTrains = trains.size();
		
		board.setTrainsWithCollisions(trains);
//		board.setTrains(trains);
		
		board.print();
		
		
		/*for (Train train : trains) {
			if(train.checkInitialCollisionHeads(trains)) {
				trains.removeAll(trains);
			}
			
			System.out.println(trains);
		}*/
		
		
		for (Train train : trains) {
			train.checkInitialCollision();
		}
		
		while (!trains.isEmpty()) {
			temp = trains.get(index);
			
			/*if(temp.checkCollisionWithAnotherHead(trains)) {
				trains.removeAll(trains);
			}
			*/
			
			temp.move();

			board.drawTrain(temp);
			board.print();

			if (temp.getWagons() <= 0) {
				trains.remove(index);
				this.maximumTrains = trains.size();
			} else {
				index++;
			}
			
			if (!temp.getSubtrains().isEmpty()) {
				for (int i = 0; i < temp.getSubtrains().size(); i++) {
					Train subTrain = temp.getSubtrains().get(i);
					if (subTrain.getWagons() == 0) {
						temp.getSubtrains().remove(subTrain);
					}
				}
			}
			
			if (index == maximumTrains) {
				index = 0;
			}
		}
		
		
		board.print();
		System.out.println();
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

	public static ArrayList<Train> getTrains() {
		return trains;
	}
}