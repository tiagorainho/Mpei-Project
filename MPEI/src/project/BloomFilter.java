package project;

public class BloomFilter {
	private byte[] array;
	private int numHashFunctions;
	private static final int maxValue = 8;
	
	public BloomFilter(int size, int numHashFunctions) {
		this.array = new byte[calculateArraySize(size, numHashFunctions)];
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
	
	private int adjustToSize(int value) {
		return value % this.array.length;
	}
	
	private int calculateArraySize(int size, int numHashFunctions) {
		return (int) ((size*numHashFunctions)/Math.log(2));		// k = (n*ln(2))/m   <=>   n = (k*m)/ln(2)
	}
	
	
	
}
