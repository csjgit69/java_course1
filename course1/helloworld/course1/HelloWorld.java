package course1;

import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		FileResource res = new FileResource();
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloWorld hObj = new HelloWorld();
		hObj.runHello();
	}
}
