package project;

public interface BloomFilter {
	void add(String value);
	boolean contains(String value);
}
