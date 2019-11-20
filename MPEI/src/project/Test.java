package project;

import java.io.File;

public class Test {
	
	public static void doTest(String fileName) {
		
		Dataset dataset = new Dataset();
		dataset.setMaxValues(20);
		dataset.getValuesCSV(new File(fileName), ",");
		
		
		dataset.showPublicationsFast();
		System.out.println(dataset.toString());
	}
	
}
