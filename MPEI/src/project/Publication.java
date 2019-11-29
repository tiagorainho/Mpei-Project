package project;
import java.util.Date;

public class Publication {
	private int id;
	//private int externalID;
	private String author;
	private String title;
	private String publicator;
	private String content;
	//private Date date;
	
	public Publication(int id, String author, String title, String publicator, String content, int idExt) {	// Date date
		//this.externalID = idExt;
		this.author = author;
		this.title = title;
		this.publicator = publicator;
		this.content = content;
		this.id = id;
		//this.date = date;
		
	}
	
	public String getPublicator() {
		return this.publicator;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String toString() {
		return "Id: " + this.id + "\tAuthor: " + this.author + "\nTitle: " + this.title + "\nPublication: " + this.publicator + "\nContent: " +this.content;
	}
	
	
}
