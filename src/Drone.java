//Drone class is used to maintin the coordinates , directiona and if the drone has started or not 
//With the help of this class more drones can be added and handeled 
public class Drone {

	int x, y;
	char dir;
	public boolean started = false;

	public Drone() {
		int x = 0;
		int y = 0;
		dir = '^';
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}
}
