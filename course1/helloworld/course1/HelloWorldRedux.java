package course1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloWorldRedux {

	public void readAndPrint() throws IOException {
		String fName  = "hello_unicode.txt";
		String source = "data_helloworld/"+fName;
		Path p = Paths.get(source);
		System.out.println(p.toAbsolutePath());
		BufferedReader reader = Files.newBufferedReader(p);
		while (true) {
			String line = reader.readLine();
			if (line==null) {
				break;
			}
			System.out.println(line);
		}
	}
	
	public void readAndPrintURL() throws IOException {
		URL source = new URL("http://dukelearntoprogram.com/java/hello_unicode.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(source.openStream()));
		while (true) {
			String line = reader.readLine();
			if (line==null) {
				break;
			}
			System.out.println(line);
		}
	}
	
	public static void main(String[] args) throws IOException  {
		HelloWorldRedux hwr = new HelloWorldRedux();
		hwr.readAndPrintURL();

	}
	
	

}
