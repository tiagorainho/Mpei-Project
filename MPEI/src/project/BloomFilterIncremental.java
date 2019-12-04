package project;

public class BloomFilterIncremental implements BloomFilter {
	private byte[] array;
	private int numHashFunctions;
	private static final double prob = 0.001;
	private static final int maxValue = 127; // 2^7 -1
	
	public void testMoreThanOne() {
		int count = 0;
		for(int i = 0; i< array.length;i++) {
			if(array[i]>1) {
				count++;
			}
		}
		System.out.println("More than one count: " + count);
	}
	
	public BloomFilterIncremental(int size, int numHashFunctions) {
		this.numHashFunctions = numHashFunctions;
		this.array = new byte[calculateOptimalArraySize(size, numHashFunctions)];
	}
	
	public void add(String content) {
		int index;
		for(int i=0;i<this.numHashFunctions;i++) {
			index = Math.abs(adjustToSize(Hash.hash(content + i)));
			if(array[index] < this.maxValue) {
				array[index] = (byte) (array[index] + 1);
			}
		}
	}
	
	public boolean containsMoreThanOne(String content) {
		int index;
		for(int i=0;i<this.numHashFunctions;i++) {
			index = Math.abs(adjustToSize(Hash.hash(content + i)));
			if(array[index] <= 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(String content) {
		int index;
		for(int i=0;i<this.numHashFunctions;i++) {
			index = Math.abs(adjustToSize(Hash.hash(content + i)));
			if(array[index] == 0) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {		
		return "Bloom filter size: " + this.array.length;
	}
	
	private int adjustToSize(int value) {
		return value % this.array.length;
	}
	
	private int calculateOptimalArraySize(int size, int numHashFunctions) {
		//return (int) Math.ceil((size * Math.log(this.prob)) / Math.log(1 / Math.pow(2, Math.log(2))));
		return (int) ((size*numHashFunctions)/Math.log(2)) * 8;		// k = (n*ln(2))/m   <=>   n = (k*m)/ln(2)
	}
}