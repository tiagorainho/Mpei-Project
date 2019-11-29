package project;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
	static final Scanner input = new Scanner(System.in);
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void doTest(String fileName, String sep) {
		
		Dataset dataset = new Dataset(120000, true, true);
		dataset.addValuesCSV(new File(fileName), sep);
		//dataset.addValuesCSV(new File(fileName2), sep);
		//dataset.addValuesCSV(new File(fileName3), sep);		
		
		dataset.showSimilarTitles(0.7, 100);
		
		
		
		
		
		
		
		
		
		
		//testBloomFilter(dataset, 100000);
		
	}
	
	private static Menu getMenu() {
		/*					METER NA MAIN
		boolean cond = true;
		Menu menu = getMenu();
		while(cond) {
			menu.show();
			String a = input.nextLine();
		}*/
		
		
		String content =  "[1] Fazer A \n"
						+ "[2] Fazer B\n"
						+ "[3] Fazer c \n"
						+ "[4] \n";
		return new Menu(content);
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
	
	private static void testBloomFilter(Dataset dataset) {
		testBloomFilter(dataset, 100000);
	}
	
	private static void testBloomFilter(Dataset dataset, int value) {
		System.out.println("BLOOM FILTER TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int errors = 0;
		for(int i=0;i<d.size();i++) {
			if(!dataset.containsTitle(d.get(i).getTitle())) {
				System.out.println("ERROR - " + d.get(i).getTitle());
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of true positives is: " + (double) (100-(errors*100)/d.size()) + "% (" + (d.size() - errors) + "/" + d.size() + ")");
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
