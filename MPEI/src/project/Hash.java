package project;

public class Hash {
	
	public static int multiplic(String content) {
		return 1;
	}
	
	public static int hash(String content) {
		int hash = 0;
		for(int i=0;i<content.length();i++) {
			hash = 37 * hash + content.charAt(i);
		}
		return hash;
	}
	
	public static int hash2(String content) {
		int ki = 1431655765;
		int h = 0;
		for(int i = 0; i < content.length(); i++) {
			h += (content.codePointAt(i)*ki);
		}
		int highorder = h & 0xf8000000;
		h = h << 5;
		h = h ^ (highorder >> 27);
		h = h ^ ki;
		return Math.abs(h);
	}

}
