package project;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Dataset {
	private ArrayList<Publication> dataset;
	private int maxValues;
	private int errors;
	
	public Dataset() {
		dataset = new ArrayList<Publication>();
		this.maxValues = 0;
		this.errors = 0;
	}
	
	public void getValuesCSV(File fileName, String sep) {
		System.out.println("Reading file...");
		List<String> news = getLines(fileName);
		System.out.println("File has been successfully read");
		
		for(int i = 1 ; i < news.size() ; i++) {
			if(this.maxValues != 0 && this.maxValues <= dataset.size()) {
				break;
			}
			String parts[] = news.get(i).split(sep);
			try {
				int id = Integer.parseInt(parts[0].trim());
				dataset.add(new Publication(id, parts[4].trim(), parts[2].trim(), parts[3].trim(), getContent(parts,9, sep)));
			}
			catch(Exception e) {
				this.errors++;
			}
		}
		System.out.println("File has been successfully parsed");
	}
	
	private String getContent(String[] parts, int place, String sep) {
		String content = "";
		for(int i=place;i<parts.length;i++) {
			content += parts[i];
			if(i != parts.length-1) {
				content += ",";
			}
		}
		return content;
	}
	
	private List<String> getLines(File fileName){
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fileName.getAbsolutePath()));
		} catch (IOException e) {
			System.out.println("Error reading the lines of the file \"" + fileName.getAbsolutePath() + "\"");
			e.printStackTrace();
		}
		return lines;
	}
	
	public void setMaxValues(int value) {
		this.maxValues = value;
	}
	
	public int getErrors() {
		return this.errors;
	}
	
	public void showPublicationsFast() {
		showPublicationsFast(this.dataset.size());
	}
	
	public void showPublicationsFast(int value) {
		for(int i=0;i<value;i++) {
			System.out.println(dataset.get(i).toString() + "\n");
		}
	}
	
	public String showPublications() {
		return showPublications(this.dataset.size());
	}
	
	public String showPublications(int value) {
		String content = "";
		for(int i=0;i<value;i++) {
			content += dataset.get(i).toString() + "\n";
		}
		return content;
	}
	
	public String toString() {
		return "This dataset has " + dataset.size() + " values. Errors occurred: " + this.errors + ". Max values: " + this.maxValues;
	}
	
}
