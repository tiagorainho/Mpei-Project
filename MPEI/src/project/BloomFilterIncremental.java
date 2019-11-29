package project;

public class BloomFilterIncremental implements BloomFilter {
	private byte[] array;
	private int numHashFunctions;
	private static final int maxValue = 127; // 2^7 -1
	
	public BloomFilterIncremental(int size, int numHashFunctions) {
		this.array = new byte[calculateOptimalArraySize(size, numHashFunctions)];
		this.numHashFunctions = numHashFunctions;
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
		return (int) ((size*numHashFunctions)/Math.log(2));		// k = (n*ln(2))/m   <=>   n = (k*m)/ln(2)
	}
}