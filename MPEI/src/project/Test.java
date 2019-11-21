package project;

import java.io.File;
import java.util.ArrayList;

public class Test {
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void doTest(String fileName, String sep) {
		
		Dataset dataset = new Dataset(50000);
		//dataset.setMaxValues(2000);
		
		dataset.addValuesCSV(new File(fileName1), sep);
		//dataset.addValuesCSV(new File(fileName2), sep);
		//dataset.addValuesCSV(new File(fileName3), sep);
		
		//System.out.println(showPublicators(dataset));
		
		System.out.println(dataset.toString());
		
		textBloomFilter(dataset, 10000);
		
	}
	
	private static String showPublicators(Dataset dataset) {
		String content = "";
		String[] publicators = dataset.getPublicators();
		for(int i=0;i<publicators.length;i++) {
			content += String.format("%d - %s\n", i+1, publicators[i]);
		}
		return content;
	}
	
	private static boolean testInserts(Dataset dataset) {
		dataset.showPublicationsFast();
		System.out.println(dataset.toString());
		return true;
	}
	
	private static void textBloomFilter(Dataset dataset) {
		textBloomFilter(dataset, 1000);
	}
	
	private static void textBloomFilter(Dataset dataset, int value) {
		System.out.println("BLOOM FILTER TEST...");
		ArrayList<Publication> d= dataset.getDataset();
		int errors = 0;
		for(int i=0;i<d.size();i++) {
			if(!dataset.containsTitle(d.get(i).getTitle())) {
				System.out.println("ERROR - " + d.get(i).getTitle());
				errors++;
			}
		}
		System.out.println("Bloom Filter: " + d.size() + " successes. " + errors + " errors");
		errors = 0;
		for(int i=0;i<value;i++) {
			String random = getRandomString(5,20);
			if(dataset.containsTitle(random)) {
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (errors*100)/value + "%  (" + errors + "/" + value + ")");
	}
	
	private static String getRandomString(int min, int max) {
		String numbers = "1234567890";
		String lowers = "abcdefghijklmnopqrstuvwxyz";
		String universe = numbers + lowers + lowers.toUpperCase();
		String content = "";
		int len = (int) (Math.random()*(max-min) + min);
		for(int i=0;i<len;i++) {
			content += universe.charAt((int) (Math.random()*universe.length()));
		}
		return content;
	}
	
}
