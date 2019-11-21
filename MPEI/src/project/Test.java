package project;

import java.io.File;

public class Test {
	
	public static void doTest(String fileName) {
		
		Dataset dataset = new Dataset(50000, true);
		dataset.setMaxValues(20);
		
		dataset.getValuesCSV(new File(fileName), ",");
		
		System.out.println(getPublicators(dataset));
		
		System.out.println(dataset.toString());
		
	}
	
	private static String getPublicators(Dataset dataset) {
		String content = "";
		String[] publicators = dataset.getPublicators();
		for(int i=0;i<publicators.length;i++) {
			content += String.format("%d - %s\n", i+1, publicators[i]);
		}
		return content;
	}
	
	private boolean testInserts(Dataset dataset) {
		dataset.showPublicationsFast();
		System.out.println(dataset.toString());
		return true;
	}
	
	private boolean textBloomFilter(Dataset dataset) {
		
		String title = "Sporting News Columnist: Belichick’s Trump Endorsement Should Make You Hate the Patriots - Breitbart,Breitbart";
		System.out.println("Houve um titulo \"" + title + "\"? :" + dataset.containsTitle(title));
		
		
		return true;
	}
	
}
