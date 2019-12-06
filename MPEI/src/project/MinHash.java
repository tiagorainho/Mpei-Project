package project;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MinHash {
	private final static int largePrimeNumber = 10007;
	private int numPermutacoes;
	private int[] A, B;
	private int[][] signatures;
	private int shinglesLength;
	private double threshHold;
	private static boolean removeSpaces = true;
	
	
	/*
	 * 			* * * * * * * *			-> permutacoes
	 * 			*
	 * 			*
	 * 			*
	 * 			*
	 * 			*
	 * 				Strings/Documentos/Titulos
	 */
	
	
	public MinHash(int numPermutacoes, int letraLen, double threshHold) {
		this.numPermutacoes = numPermutacoes;
		this.A = getRandomValues(numPermutacoes);
		this.B = getRandomValues(this.A);
		this.shinglesLength = letraLen;
		this.signatures = new int[1][numPermutacoes];
		this.threshHold = threshHold;
	}
	
	public MinHash(int numPermutacoes) {
		this(numPermutacoes, 3, 0.6);
	}
	
	public MinHash(int numPermutacoes, int letraLen) {
		this(numPermutacoes, letraLen, 0.6);
	}
	
	public List<Integer> getSimilars(int numValues) {
		List<Integer> list = new LinkedList<Integer>();
		for(int i=0;i<numValues;i++) {
			if(areSimilar(numValues, i)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public void setRemoveSpaces(boolean removeSpaces) {
		this.removeSpaces = removeSpaces;
	}
	
	public List<LinkedList<Integer>> getSimilaresWithConstantField(List<String> similars, List<String> constants){
		boolean found;
		LinkedList<Integer> llAux;
		List<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();
		HashSet<Integer> used = new HashSet<Integer>();
		int countPercent = 0;
		int iterations = (int) Math.ceil(((this.signatures.length*(this.signatures.length-1))/2)/10);
		int iteration = 0;
		for(int i=0;i<this.signatures.length-1;i++) {			
			llAux = new LinkedList<Integer>();
			found = false;
			for(int j=i+1;j<this.signatures.length;j++) {
				
				if(constants.get(i).equals(constants.get(j))) {
					if(!used.contains(j)) {
						if(areSimilar(i, j)) {
							found = true;
							llAux.add(j);
							used.add(j);
						}
					}
				}
				// ############ show progress ##################
				if(iteration == iterations*countPercent) {
					if(countPercent != 10) {
						System.out.printf("%d%%.. ", countPercent*10);
					}
					countPercent++;
				}
				// #############################################
				
				iteration++;
			}
			if(found) {
				llAux.addFirst(i);
				list.add(llAux);
			}
		}
		System.out.println("Done!");
		return list;
	}
	
	public List<LinkedList<Integer>> getSimilars(){
		boolean found;
		LinkedList<Integer> llAux;
		List<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();
		HashSet<Integer> used = new HashSet<Integer>();
		int countPercent = 0;
		int iterations = (int) Math.ceil((this.signatures.length*(this.signatures.length-1))/2);
		double dist = iterations/10;
		int iteration = 0;
		for(int i=0;i<this.signatures.length-1;i++) {			
			llAux = new LinkedList<Integer>();
			found = false;
			for(int j=i+1;j<this.signatures.length;j++) {
				// ############ show progress ##################
				if(iteration == (int) (dist*countPercent/10)) {
					if(countPercent != 100) {
						System.out.printf("%d%%.. ", countPercent);
					}
					countPercent += 10;
					System.out.println(iteration);
				}
				// #############################################
				if(!used.contains(j)) {
					if(areSimilar(i, j)) {
						found = true;
						llAux.add(j);
						used.add(j);
					}
				}
				iteration++;
			}
			if(found) {
				llAux.addFirst(i);
				list.add(llAux);
			}
		}
		System.out.println("\nIterations: " + iterations);
		System.out.println("Iteration: " + iteration);
		System.out.println("Done!");
		return list;
	}
	
	public void showSimilars() {
		showSimilars(getSimilars());
	}
	
	private List<LinkedList<Integer>> getSignaturesList(){
		List<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> llAux;
		for(int i=0;i<this.signatures.length;i++) {
			llAux = new LinkedList<Integer>();
			for(int j=0;j<this.numPermutacoes;j++) {
				llAux.add(this.signatures[i][j]);
			}
			list.add(llAux);
		}
		return list;
	}
	
	public void showSimilars(List<LinkedList<Integer>> list) {
		LinkedList<Integer> aux;
		for(int i=0;i<list.size();i++) {
			aux = list.get(i);
			System.out.printf("{");
			for(int j=0;j<aux.size();j++) {
				System.out.printf("%d",aux.get(j));
				if(j<aux.size()-1) {
					System.out.printf(", ");
				}
				
			}
			System.out.printf("}\n");
		}
	}
	
	public boolean areSimilar(int v1, int v2) {
		return getSimilarity(v1, v2) >= this.threshHold;
	}
	
	public double getSimilarity(int v1, int v2) {
		int count = 0;
		for(int i=0;i<this.numPermutacoes;i++) {
			if(this.signatures[v1][i] == this.signatures[v2][i]) {
				count++;
			}
		}
		return (double) count/this.numPermutacoes;
	}
	
	public void add(String[] str) {
		
		if(str.length == 0) {
			System.out.println("Action not valid due to lack of values in the database");
			throw new ArrayIndexOutOfBoundsException();
		}		
		int initialLength = this.signatures.length;
		if(initialLength <= 1 && this.signatures[0][0] == 0) {
			initialLength = 0;
		}
		int[][] aux = new int[initialLength + str.length][numPermutacoes];
		System.arraycopy(this.signatures, 0 , aux, 0, this.signatures.length);
		this.signatures = aux;
		calculateSignatures(str, initialLength);
	}
	
	public void add(List<String> list) {
		this.add(convertListToStrings(list));
	}
	
	private void calculateSignatures(String[] strings, int initialLength) {
		String[] auxShingles;
		int[] signatureVector;
		for(int i=0;i<strings.length;i++) {
			auxShingles = getShingles(strings[i]);
			signatureVector = getSignature(auxShingles);
			for(int j=0;j<numPermutacoes;j++) {
				this.signatures[i + initialLength][j] = signatureVector[j];
			}
		}
	}
	
	private int[] getSignature(String[] shingles) {
		int[] signature = new int[this.numPermutacoes];
		signature = setArrayToValue(signature, this.largePrimeNumber);
		int auxSig, auxHash;
		for(int k=0;k<shingles.length;k++) {
			auxHash = Hash.hash(shingles[k]);
			for(int j=0;j<this.numPermutacoes;j++) {  //this.A.length
				auxSig = (Math.abs(this.A[j] * auxHash) + this.B[j]) % this.largePrimeNumber;
				if(auxSig < signature[j]) {
					signature[j] = auxSig;
				}
			}
		}
		return signature;
	}
	
	public double jaccardCoeficient(String s1, String s2) {
		if(this.removeSpaces) {
			s1 = s1.replace(" ", "");
			s2 = s2.replace(" ", "");
		}
		List<String> shingles1 = new LinkedList<String>();
		List<String> shingles2 = new LinkedList<String>();
		String[] s1Array = getShingles(s1);
		String[] s2Array = getShingles(s2);
		for(int i=0; i<s1Array.length;i++) {
			shingles1.add(s1Array[i]);
		}
		for(int i=0; i<s2Array.length;i++) {
			shingles2.add(s2Array[i]);
		}
		HashSet<String> interception = new HashSet<String>(shingles1);
		HashSet<String> union = new HashSet<String>(shingles1);
		interception.retainAll(shingles2);
		union.addAll(shingles2);
		return (double) interception.size() / union.size();
	}
	
	private List<String> convertStringArrayToList(String[] str) {
		List<String> list = new LinkedList<String>();
 		for(String aux: str) {
			list.add(aux);
		}
		return list;
	}
	
	public void showSignatures() {
		for(int i=0;i<signatures.length;i++) {
			for(int j=0;j<numPermutacoes;j++) {
				System.out.printf("%-8d",signatures[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setThreshHold(double threshHold) {
		this.threshHold = threshHold;
	}
	
	private int[] setArrayToValue(int[] array , int value) {
		for(int i=0;i<array.length;i++) {
			array[i] = value;
		}
		return array;
	}
	
	private String[] getShingles(String str) {
		if(this.removeSpaces) {
			str = str.replace(" ", "");
		}
		if(str.length() - this.shinglesLength + 1 > 0) {
			String[] list = new String[str.length() - this.shinglesLength + 1];
			for(int i=0;i<str.length() - this.shinglesLength + 1;i++) {
				list[i] = str.substring(i, i + this.shinglesLength);
			}
			return list;
		}
		return new String[0];
	}
	
	private String[] convertListToStrings(List<String> l) {
		String[] content = new String[l.size()];
		for(int i=0;i<content.length;i++) {
			content[i] = l.get(i);
		}
		return content;
	}
	
	private void showRandomValues(int[] values) {
		for(int i=0;i<values.length;i++) {
			System.out.println(values[i]);
		}
	}
	
	private int[] getRandomValues(int len) {
		int[] a = new int[len];
		for(int i=0;i<len; i++) {
			a[i] = (int) (Math.random() * this.largePrimeNumber + 1);
			if(a[i] == this.largePrimeNumber) {
				a[i]--;
			}
		}
		return a;
	}
	
	private int[] getRandomValues(int[] array) {
		int[] b = new int[array.length];
		int aux;
		for(int i=0;i < array.length; i++) {
			do {
				aux = (int) (Math.random() * this.largePrimeNumber + 1);
				if(aux == this.largePrimeNumber) {
					aux--;
				}
			}
			while(aux == array[i]);
			b[i] = aux;			
		}
		return b;
	}
}
