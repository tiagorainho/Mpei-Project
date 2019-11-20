package project;
import java.util.Date;

public class Publication {
	private int id;
	private String author;
	private String title;
	private String publication;
	private String content;
	private Date date;
	
	public Publication(int id, String author, String title, String publication, String content) {	// Date date
		this.id = id;
		this.author = author;
		this.title = title;
		this.publication = publication;
		this.content = content;
		//this.date = date;
	}
	
	public String toString() {
		return "Id: " + this.id + "\tAuthor: " + this.author + "\nTitle: " + this.title + "\nPublication: " + this.publication + "\nContent: " +this.content;
	}
	
	
}
