package EntregaExamen1;

public class Train {
	private char direction;
	private Position position;
	private int wagons;

	public Train(char direction, Position position, int wagons) {
		this.direction = direction;
		this.position = position;
		this.wagons = wagons;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
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
		case 'B':
			if(x == 29) {
				wagons--;
			} else {
				position.setX(x+1);
			}

			break;

		case 'A':
			if(x == 0) {
				wagons--;
			} else {
				position.setX(x-1);
			}

			break;

		case 'I':
			if(y == 0) {
				wagons--;
			} else {
				position.setY(y-1);
			}

			break;

		case 'D':
			if(y == 29) {
				wagons--;
			} else {
				position.setY(y+1);
			}

			break;
		}
	}


	public void moveWhenCollision() {
		wagons--;

		switch(direction){
		case 'B':
			position.setX(position.getX() + wagons);

			if(position.getX() > 29){
				while((position.getX() > 29) && (wagons > 0)){
					position.setX(position.getX() - 1);
					wagons--;
				}
			}

			break;

		case 'A':
			position.setX(position.getX() - wagons);

			if(position.getX() < 0){
				while((position.getX() < 0 ) && (wagons > 0)){
					position.setX(position.getX() + 1);
					wagons--;
				}
			}

			break;

		case 'I':
			position.setY(position.getY() - wagons);

			if(position.getY() < 0){
				while((position.getY() < 0) && (wagons > 0)){
					position.setY(position.getY() + 1);
					wagons--;
				}
			}

			break;

		case 'D':
			position.setY(position.getY() + wagons);

			if(position.getY() > 29){
				while((position.getY() > 29) && (wagons > 0)){
					position.setY(position.getY() - 1);
					wagons--;
				}
			}

			break;
		}
	}


	@Override
	public String toString() {
		return "Train [direction=" + direction + ", position=" + position + ", wagons=" + wagons + "]";
	}



}