import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class RemoteControl {
	public static BillBoards b = new BillBoards();
	//Distance travelled by drone in every moment 
	public static int dist = 1;

	public static void main(String args[]) {
		Drone d1 = new Drone();
		Drone d2 = new Drone();
		//tempIns contains instruction given to the drones 
		char[] tempIns = readInstructions().toCharArray();
		// flag to keep a track the instruction are read alternatively 
		boolean flag = true;
		for (char ch : tempIns) {
			if (flag == true) {
				// movement is a defined behaviour as the drones are getting instrutions 
				d1 = movement(d1, ch);
				// crash evaluates if two drones are crossing each other , and if they are in same direction then we are not considering it as a crash 
				//after every movement its been checked that are drones crossing path ?
				if (crash(d1, d2)) {
					System.out.println("Coordinates where drones crashed:" + "(" + d1.getX() + "," + d1.getY() + ")");
					break;
				}
				flag = false;
			} else {

				d2 = movement(d2, ch);
				if (crash(d1, d2)) {
					System.out.println("Coordinates where drones crashed:" + "(" + d1.getX() + "," + d1.getY() + ")");
					break;
				}
				flag = true;
			}
		}
		//To iterate over the billboard count to display boards which are visited more than once 
		Iterator<String> iterator = b.bill.keySet().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			String value = b.bill.get(key).toString();
			int billBoard = Integer.parseInt(value);
			if (billBoard > 1)
				count++;
		}
		System.out.println("Number of Billboards which are photographed more than once:" + count);
		System.out.println("Position Coordinates of Drone 1:("+d1.getX()+","+d1.getY()+")");
		System.out.println("Position Coordinates of Drone 2:("+d2.getX()+","+d2.getY()+")");
	}

	public static Drone movement(Drone d, char ins) {
		// Instruction set for Drone movement 
		if (ins == '>') {
			int x = d.getX();
			x = x + dist;
			d.setX(x);
			d.setDir(ins);
			d.started = true;

		} else if (ins == '^') {
			int y = d.getY();
			y = y + dist;
			d.setY(y);
			d.setDir(ins);
			d.started = true;
		} else if (ins == '<') {
			int x = d.getX();
			x = x - dist;
			d.setX(x);
			d.setDir(ins);
			d.started = true;

		} else if (ins == 'v') {
			int y = d.getY();
			y = y - dist;
			d.setY(y);
			d.setDir(ins);
			d.started = true;
		} else if (ins == 'x')

		{
			//considering coordinates as a key to hashmap for storing the billboard position which are clicked  
			String coord = "" + d.getX() + "," + d.getY();

			click(coord);
		}

		return d;
	}
// Click evaluates the number of times billBoard is been clicked 
	public static void click(String billBoard) {
		if (b.bill.containsKey(billBoard)) {
			int count = 0;
			count = (int) b.bill.get(billBoard);
			count++;
			b.bill.put(billBoard, count);
		} else {
			b.bill.put(billBoard, 1);
		}
	}
// Reading instruction set from the file 
	public static String readInstructions() {
		String ins = "";

		try (BufferedReader br = new BufferedReader(new FileReader("droneinput.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				ins = ins + sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
// To remove white space in the instruction set plese remove the comment from below statement 
		// ins= ins.replaceAll("\\s","");

		return ins;
	}
// Crash eveluates if two drones are crossing and weather they are not in the same direction as its been considered as a happy path :)
	public static boolean crash(Drone d1, Drone d2) {

		return (d1.getX() == d2.getX() && d1.getY() == d2.getY() && d1.started && d2.started && d1.dir != d2.dir);

	}

}


//Thank you 
//Karan Jeet Singh