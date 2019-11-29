package project;

public class Menu {
	private String content;
	
	public Menu(String content) {
		this.content = content;
	}
	
	public void add(String newContent) {
		this.content += newContent;
	}

	public void show() {
		System.out.println(this.content);
	}
}
