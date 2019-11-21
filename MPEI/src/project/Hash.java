package project;

public class Hash {
	
	public static int multiplic(String content) {
		return 1;
	}
	
	public static int hash(String content) {
		int hash = 0;
		for(int i=0;i<content.length();i++) {
			hash += 37 * hash + content.charAt(i);
		}
		return hash;
	}

}
