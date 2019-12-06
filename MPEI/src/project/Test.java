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
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void doTest(String fileName, String sep) throws IOException {
		
		/*
		Dataset dataset = new Dataset(100000);
		dataset.setMaxValues(10000);
		dataset.addValuesCSV(fileName);
		dataset.addValuesCSV(fileName2);
		dataset.addValuesCSV(fileName3);
		System.out.println(dataset.toString());
		
		dataset.showSimilarTitles(0.6, 100);
		*/
		
		//dataset.showSameTitleSimilarContent(0.6);
		
		testMinHash(500);
		
		
		
		
		
		
		
		
		//showSameTitlePublications
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		String[] list = {"porra so quero acabar este projeto", "ola como estas tudo bem", "ola tudo bem como estas"};
		MinHash minHash = new MinHash(100);
		minHash.add(list);
		
		int v1 = 2;
		int v2 = 1;
		System.out.println("Teorico: " + minHash.jaccardCoeficient(list[v1], list[v2]));
		System.out.println("Pratico: " + minHash.getSimilarity(v1, v2));
		*/
		
		
		//dataset.showSimilarTitles(0.8, 10);
		//dataset.getSameNews(0.8, 100);
		
		//dataset.showSimilarNews(0.8, 100);
		
		
		/*
		int val = 10000;
		String m = getRandomString(val-1,val);
		String n = getRandomString(val-1, val);
		String[] a= {m,n};
		*/
		//String[] a= {"faeoifhaeuofhaeoiwfaof a haeufhaeou uoahuf auh faeuohf aehfahoufiwajoda+ºdofaekjfgaeook", "jnaoefjap maoj fjaei jfoa p kaiido+awºlĸð nmg sjkg ae ae8fseufsefseifhesoi"};
		/*MinHash b = new MinHash(100, 10);
		b.add(a);
		System.out.println(b.getSimilarity(0, 1));
		*/
		
		
		
		
		//testBloomFilter(dataset, 1000000);
	}
	
	private static void testMinHash(int value) throws FileNotFoundException {
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
	
	private static boolean testInserts(Dataset dataset) {
		dataset.showPublicationsFast();
		System.out.println(dataset.toString());
		return true;
	}
	
	private static void testBloomFilter(Dataset dataset, int value) {
		String[] randomStrings = getRandomStrings(value);
		testBloomFilterOptimized(dataset, randomStrings);
		testBloomFilterIncremental(dataset, randomStrings);
		testBloomFilterIncrementalMoreThanOne(dataset, randomStrings);
	}
	
	private static void testBloomFilterIncrementalMoreThanOne(Dataset dataset, String[] randomValues) {
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
	
	private static void testBloomFilterIncremental(Dataset dataset, String[] randomValues) {
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
	
	private static void testBloomFilterOptimized(Dataset dataset, String[] randomValues) {
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
			System.out.println(strings[i]);
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
