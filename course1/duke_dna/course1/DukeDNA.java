package course1;
import edu.duke.*;

public class DukeDNA {
	public void stringP() {
		String s = "dukeprogramming";
		String x = s.substring(4,7);
		System.out.println("s:" + s);
		System.out.println("x:" + x);
		System.out.println(s.length());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DukeDNA test = new DukeDNA();
		test.stringP();
	}

}
