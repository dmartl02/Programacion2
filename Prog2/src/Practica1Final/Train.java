package Practica1Final;

public class Train {
	private Direction direction;
	private Position position;
	private int wagons;

	public Train(Direction direction, Position position, int wagons) {
		this.direction = direction;
		this.position = position;
		this.wagons = wagons;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getWagons() {
		return wagons;
	}

	public void setWagons(int wagons) {
		this.wagons = wagons;
	}


	public void move() {
		int x = position.getX();
		int y = position.getY();

		switch(direction) {
		case DOWN:
			if(x<29) {
				position.setX(x+1);	
			} else if(x == 29) {
				position.setX(x-1);
				wagons--;
			}
			break;

		case UP:
			if(x<0) {
				position.setX(x-1);	
			} else if(x == 0) {
				position.setX(x+1);
				wagons--;
			}
			break;

		case LEFT:
			if(y<0) {
				position.setY(y-1);
			} else if(y == 0) {
				position.setY(y+1);
				wagons--;
			}
			break;	

		case RIGHT:
			if(y<29) {
				position.setY(y+1);	
			} else if(y == 29) {
				position.setY(y-1);
				wagons--;
			}
			break;
		}
	}


	public void moveWhenCollision(Position collision) {
		int collisionX = collision.getX();
		int collisionY = collision.getY();
		int trainX = position.getX();
		int trainY= position.getY();

		wagons--;

		switch (direction) {
		case DOWN:
			trainX = collisionX + wagons;
			break;

		default:
			break;
		}

		position.setX(trainX);
		position.setY(trainY);

	}




	@Override
	public String toString() {
		return "Train [direction=" + direction + ", position=" + position + ", wagons=" + wagons + "]";
	}



}
