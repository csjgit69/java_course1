package course1;

import java.io.File;

import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		String fName  = "hello_unicode.txt";
		String source = "../data_helloworld/"+fName;

		//FileResource res = new FileResource();
		DirectoryResource res = new DirectoryResource();
		for (File f : res.selectedFiles()) {
			System.out.println(f.getAbsolutePath());
		}
//		for (String line : res.lines()) {
//			System.out.println(line);
//		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloWorld hObj = new HelloWorld();
		hObj.runHello();
	}
}
