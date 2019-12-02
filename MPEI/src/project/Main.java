package project;
import java.io.FileNotFoundException;
//import org.json.*;
import java.io.IOException;

public class Main {
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Test.doTest(fileName1, ",");
		
		
	}
	
	

}