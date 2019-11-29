package project;

public class BloomFilterOptimized implements BloomFilter {
	private byte[] array;
	private int numHashFunctions;
	private int arrayVirtualLength;
	private static final int[] steps = {1, 2, 4, 8, 16, 32, 64, 128};
	
	public BloomFilterOptimized(int size, int numHashFunctions) {
		this.arrayVirtualLength = calculateOptimalArraySize(size, numHashFunctions);
		this.array = new byte[(int) Math.ceil(arrayVirtualLength/8)+1];
		this.numHashFunctions = numHashFunctions;
		
	}
	
	public void add(String content) {
		int index, subIndex;
		double aux;
		for(int i=0;i<this.numHashFunctions;i++) {
			aux = Math.abs(adjustToSize(Hash.hash(content + i)));
			index = (int) aux/8;
			subIndex = (int) aux % 8;
			if(getBit(array[index], subIndex) == 0) {
				array[index] = (byte) (array[index] + steps[subIndex]);
			}			
		}
	}
	
	public boolean contains(String content) {
		int index, subIndex;
		double aux;
		for(int i=0;i<this.numHashFunctions;i++) {
			aux = Math.abs(adjustToSize(Hash.hash(content + i)));
			index = (int) aux/8;
			subIndex = (int) aux % 8;
			if(getBit(array[index], subIndex) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {		
		return "Bloom filter size: " + this.array.length + ". Bloom Filter virtual length: " + this.arrayVirtualLength;
	}
	
	private static int getBit(byte b, int position)
	{
	   return (b >> position) & 1;
	}
	
	private double adjustToSize(int value) {
		return value % this.arrayVirtualLength;
	}
	
	private int calculateOptimalArraySize(int size, int numHashFunctions) {
		return (int) Math.ceil(((size*numHashFunctions)/Math.log(2)));		// k = (n*ln(2))/m   <=>   n = (k*m)/ln(2)
	}
}

/*
		//upper extreme position
		if(subIndex == threshHolds.length - 1) {
			if(array[index] < threshHolds[subIndex]) {
				array[index] = (byte) (array[index] + threshHolds[subIndex]);
			}
			// array[index] >= threshHolds[subIndex]
			continue;
		}
		//	lower extreme position
		else if(subIndex == 0) {
			if(array[index] % 2 == 0) {
				array[index] = (byte) (array[index] + threshHolds[subIndex]);
			}
			// array[index] % 2 != 0
			continue;
		}
*/
