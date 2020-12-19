package Practica1Final;

import java.util.ArrayList;


public class Game {

	private Board board;
	private ArrayList<Train> trains;
	private int maximumTrains;

	public Game(Board board, ArrayList<Train> trains) {
		this.board = board;
		this.trains = trains;
		this.maximumTrains = trains.size();
	}

	public void start() {
		int index = 0;
		Train temp;
		boolean crashed = false;


		while(!trains.isEmpty()) {
			temp = trains.get(index);

			temp.move();

			crashed = detectCollision(index);

			if (crashed) {

			}

			index++;

			if(isLapFinished(index)) {
				lapFinished(index);
				index = 0;
			}
		}

	}

	public boolean detectCollision(int index) {
		return false;
	}


	public boolean isLapFinished(int index) {
		return index == maximumTrains ? true : false;
		
	}

	public void lapFinished(int index) {
		for(int i = 0; i < trains.size(); i++) {
			if(trains.get(i).getWagons() == 0) {
				trains.remove(i);
				maximumTrains--;
				i--;
			}
		}
	}
}
