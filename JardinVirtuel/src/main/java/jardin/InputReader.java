package jardin;

import java.util.Scanner;

public class InputReader {
	
	private Scanner sc;
	
	public InputReader() {
		sc = new Scanner(System.in);
	}
	
	public int readIntValue() {
		return sc.nextInt();
	}
}
