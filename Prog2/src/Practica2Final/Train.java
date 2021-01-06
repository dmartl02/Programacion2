package Practica2Final;

public class Train {
	private char direction;
	private int wagons, row, column;
	
	public Train(char direction, int wagons, int row, int column) {
		this.direction = direction;
		this.wagons = wagons;
		this.row = row;
		this.column = column;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public int getWagons() {
		return wagons;
	}

	public void setWagons(int wagons) {
		this.wagons = wagons;
	}

	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}


	public void move() {
		int x = getRow();
		int y = getColumn();

		switch(direction) {
		case 'B':
			if(x == 29) {
				wagons--;
			} else {
				setRow(x+1);
			}
			
			break;

		case 'A':
			if(x == 0) {
				wagons--;
			} else {
				setRow(x-1);
			}
			
			break;

		case 'I':
			if(y == 0) {
				wagons--;
			} else {
				setColumn(y-1);
			}
			
			break;
			
		case 'D':
			if(y == 29) {
				wagons--;
			} else {
				setColumn(y+1);
			}
			
			break;
		}
	}

	/*
	public void moveWhenCollision(Position collision) {
		int collisionX = collision.getX();
		int collisionY = collision.getY();
		
		int trainX = position.getX();
		int trainY= position.getY();
		
		boolean crashedTrain = false;
		boolean crashedTrainUpDown = false;
		boolean crashedTrainLeftRight = false;

		wagons--;
		
		if(crashedTrain) {
			if(crashedTrainUpDown) {
				switch (direction) {
					case 'B':
						trainX = collisionX + wagons;
						crashedTrain = true;
						crashedTrainUpDown = true;


						if(trainX > 29) {
							while(trainX > 29 || wagons > 0) {
								trainX--;
								wagons--;
							}
						}

						break;

					case 'A':
						trainX = collisionX - wagons;
						crashedTrain = true;
						crashedTrainUpDown = true;

						if(trainX < 0) {
							while(trainX < 0 || wagons > 0) {
								trainX++;
								wagons--;
							}
						}

						break;
				}		
				
			} else if(crashedTrainLeftRight) {
				switch (direction) {
					case 'I':
						trainY = collisionY - wagons;
						crashedTrain = true;
						crashedTrainLeftRight = true;

						if(trainY < 0) {
							while(trainX < 0 || wagons > 0) {
								trainY++;
								wagons--;
							}
						}

						break;

					case 'D':
				
						trainY = collisionY + wagons;
						crashedTrain = true;
						crashedTrainLeftRight = true;


						if(trainY > 29) {
							while(trainY > 29 || wagons > 0) {
								trainY--;
								wagons--;
							}
						}			

						break;

					}
				}
			}	
		
		
		position.setX(trainX);
		position.setY(trainY);
	}
	/**/

	@Override
	public String toString() {
		return "Train [direction=" + direction + ", x=" + row + ", y= " + column + ",wagons=" + wagons + "]";
	}



}

