package project;
import java.util.Date;

public class Publication {
	private int id;
	private String author;
	private String title;
	private String publicator;
	private String content;
	
	public Publication(int id, String author, String title, String publicator, String content) {
		this.author = author;
		this.title = title;
		this.publicator = publicator;
		this.content = content;
		this.id = id;
	}
	
	public Publication(String author, String title, String publicator, String content) {
		this.author = author;
		this.title = title;
		this.publicator = publicator;
		this.content = content;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getPublicator() {
		return this.publicator;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String toString() {
		return "Id: " + this.id + "\tAuthor: " + this.author + "\nTitle: " + this.title + "\nPublication: " + this.publicator + "\nContent: " +this.content;
	}
	
	
}
