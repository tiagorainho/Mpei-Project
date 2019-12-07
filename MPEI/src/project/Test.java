package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
	static final Scanner input = new Scanner(System.in);
	static final String fileName1 = "Articles/articles1.csv";
	static final String fileName2 = "Articles/articles2.csv";
	static final String fileName3 = "Articles/articles3.csv";
	
	public static void doTest() throws IOException {
		
		
		Dataset dataset = new Dataset(120000);
		dataset.setMaxValues(50000);
		dataset.addValuesCSV(fileName1);
		dataset.addValuesCSV(fileName2);
		dataset.addValuesCSV(fileName3);
		System.out.println(dataset.toString());
		
		dataset.showSameTitleSimilarContent(0.6);
		
		
		
		
	}
	
	public static void testAll() throws FileNotFoundException {
		Dataset dataset = new Dataset(100000);
		dataset.setMaxValues(10000);
		dataset.addValuesCSV(fileName1);
		dataset.addValuesCSV(fileName2);
		dataset.addValuesCSV(fileName3);
		System.out.println(dataset.toString());
		testBloomFilter(dataset, 10000000);
		testMinHash(1500);
	}
	
	public static void testMinHash(int value) throws FileNotFoundException {
		System.out.println("TESTING MIN HASH...");
		Dataset dataset = new Dataset(value);
		dataset.setMaxValues(value);
		dataset.addValuesCSV(fileName1);
		dataset.addValuesCSV(fileName2);
		dataset.addValuesCSV(fileName3);
		List<String> list = dataset.getTitles(value);
		MinHash minHash = new MinHash(100);
		minHash.add(list);
		double erroSum = 0;
		int count = 0;
		BigInteger iterationsAux = new BigInteger("1").multiply(BigInteger.valueOf(list.size()).multiply(BigInteger.valueOf(list.size()))).divide(BigInteger.valueOf(2));
		BigInteger partAux = BigInteger.ONE.multiply(iterationsAux).divide(BigInteger.valueOf(10));
		long iteration = 0;
		long iterations = iterationsAux.longValue();
		long part = partAux.longValue();
		for(int i=0;i<list.size()-1;i++) {
			for(int j=i;j<list.size();j++) {
				// ############ show progress ##################
				if(iteration == part*count) {
					if(count != 10) {
						System.out.printf("%d%%.. ", count*10);
					}
					count++;
				}
				iteration++;
				// #############################################
				erroSum += Math.abs(minHash.getSimilarity(i, j) - minHash.jaccardCoeficient(list.get(i), list.get(j)));
			}
		}
		double percentage = erroSum*100/iteration;
		System.out.println(String.format("Done!\nPercentagem de erro do minHash: %.2f%% (%.2f/%d)",percentage, erroSum, iteration));
	}
	
	private static String showPublicators(Dataset dataset) {
		String content = "";
		String[] publicators = dataset.getPublicators();
		for(int i=0;i<publicators.length;i++) {
			content += String.format("%d - %s\n", i+1, publicators[i]);
		}
		return content;
	}
	
	public static void testBloomFilter(Dataset dataset, int value) {
		String[] randomStrings = getRandomStrings(value);
		testBloomFilterOptimized(dataset, randomStrings);
		testBloomFilterIncremental(dataset, randomStrings);
		testBloomFilterIncrementalMoreThanOne(dataset, randomStrings);
	}
	
	public static void testBloomFilterIncrementalMoreThanOne(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER INCREMENTAL \"More than one\" TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int count = 0;
		for(int i=0;i<d.size();i++) {
			if(dataset.containsMoreThanOneTitle(d.get(i).getTitle())) {
				count++;
			}
		}
		System.out.println("Bloom Filter: percentage of mapped keys more than once is: " + (double) (count*100)/d.size() + "% (" + (d.size() - count) + "/" + d.size() + ")");
		count = 0;
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsMoreThanOneTitle(randomValues[i])) {
				count++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (count*100)/randomValues.length + "%  (" + count + "/" + randomValues.length + ")");
	}
	
	public static void testBloomFilterIncremental(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER INCREMENTAL TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int errors = 0;
		for(int i=0;i<d.size();i++) {
			if(!dataset.containsTitleIncremental(d.get(i).getTitle())) {
				System.out.println("ERROR - " + d.get(i).getTitle());
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of true positives is: " + (double) (100-(errors*100)/d.size()) + "% (" + (d.size() - errors) + "/" + d.size() + ")");
		errors = 0;
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsTitleIncremental(randomValues[i])) {
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (errors*100)/randomValues.length + "%  (" + errors + "/" + randomValues.length + ")");
	}
	
	public static void testBloomFilterOptimized(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER OPTIMIZED TEST...");
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
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsTitle(randomValues[i])) {
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (errors*100)/randomValues.length + "%  (" + errors + "/" + randomValues.length + ")");
		BloomFilterIncremental a = dataset.getBloomFilterIncremental();
	}
	
	private static String[] getRandomStrings(int values) {
		String[] strings = new String[values];
		for(int i=0;i<values;i++) {
			strings[i] = getRandomString(5,20);
		}
		return strings;
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
