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
	private int excluded;
	private BloomFilter titlesBloomFilter;
	private boolean onlyTrustTrustedEntities;
	private static final String[] trustedEntities = {"New York Times", "Atlantic", "Guardian"};
	
	public Dataset(int numValuesAprox, boolean onlyTrustTrustedEntities) {
		this.onlyTrustTrustedEntities = onlyTrustTrustedEntities;
		dataset = new ArrayList<Publication>();
		this.maxValues = 0;
		this.excluded = 0;
		this.errors = 0;
		titlesBloomFilter = new BloomFilter(numValuesAprox, 4);	// aproximated value of objects, number of hash functions
	}
	
	public Dataset(int numValuesAprox) {
		this(numValuesAprox, false);
	}
	
	public void addValuesCSV(File fileName, String sep) {
		System.out.println("Reading file...");
		List<String> news = getLines(fileName);
		System.out.println("File has been successfully read\nParsing data...");
		
		for(int i = 1 ; i < news.size() ; i++) {
			if(this.maxValues != 0 && this.maxValues <= dataset.size()) {
				break;
			}
			String parts[] = news.get(i).split(sep);
			try {
				if(this.onlyTrustTrustedEntities) {
					if(isTrusted(parts[3].trim())) {
						addToDataset(parts, sep);
					}
					else {
						this.excluded++;
					}
				}
				else {
					addToDataset(parts, sep);
				}
			}
			catch(Exception e) {
				this.errors++;
			}
		}
		System.out.println(getParseInfo());
	}
	
	private void addToDataset(String[] parts, String sep) {
		int id = Integer.parseInt(parts[0].trim());
		dataset.add(new Publication(this.dataset.size(), parts[4].trim(), parts[2].trim(), parts[3].trim(), getContent(parts,9, sep), id));
		titlesBloomFilter.add(parts[2].trim());
	}
	
	public String[] getPublicators() {
		ArrayList<String> publicators = new ArrayList<String>();
		for(int i=0;i<this.dataset.size();i++) {
			String publicator = this.dataset.get(i).getPublicator();
			if(!publicators.contains(publicator)) {
				publicators.add(publicator);
			}
		}
		String[] content = new String[publicators.size()];
		for(int i = 0;i<publicators.size();i++) {
			content[i] = publicators.get(i);
		}
		return content;
	}
	
	private boolean isTrusted(String publicator) {
		for(int i=0;i<this.trustedEntities.length;i++) {
			if(publicator.compareTo(this.trustedEntities[i]) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsTitle(String title) {
		return this.titlesBloomFilter.contains(title.trim());
	}
	
	public void setMaxValues(int value) {
		this.maxValues = value;
	}
	
	public int getErrors() {
		return this.errors;
	}
	
	private String getParseInfo() {
		if(this.dataset.size() > 0) {
			if(this.errors + this.excluded > 0) {
				return "Data has been parsed but some information provided wasnt correctly formated. " + (this.errors + this.excluded) + " errors";
			}
			else {
				return "Data has been successfully parsed";
			}
		}
		else {
			if(this.errors > 0 && this.excluded == 0) {
				return "Major error parsing, no information was retained";
			}
			else if(this.excluded > 0) {
				return "No information was retained due to trusted entities impositions";
			}
			else {
				return "Error parsing all lines";
			}
		}		
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
	
	public ArrayList<Publication> getDataset() {
		return this.dataset;
	}
	
	public String toString() {
		String maxvalues = String.valueOf(this.maxValues);
		if(this.maxValues == 0) {
			maxvalues = "no limit"; //"∞";
		}
		return "This dataset has " + this.dataset.size() + " values. Errors occurred: " + this.errors + ". Excluded by lack of trust: " + this.excluded + ". Max values: " + maxvalues;
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
	
}
