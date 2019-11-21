package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FormatFileProperly {
	private List<String>[] lines;
	private int count;
	
	public FormatFileProperly(String[] fileNames) {
		
		/*
		String newFile = "src/project/newArticle.csv";
		FormatFileProperly f = new FormatFileProperly(getFileNames());
		try {
			f.export(newFile, "\t");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		*/
		
		
		
		this.count = 0;
		String auxS = "";
		String content = "";
		for(int i=0;i<fileNames.length;i++) {
			File f = new File(fileNames[i]);
			Scanner input = null;
			try {
				input = new Scanner(f);
				content = "";
				while(input.hasNextLine()) {
					auxS = input.nextLine();
					System.out.println(content);
					content += auxS;
					if(auxS.contains("\n")) {
						lines[i].add(content);
						content = "";
					}
				}
			} catch (FileNotFoundException e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			
			if(this.count > this.lines[i].size()) {
				this.count = this.lines[i].size();
			}
			
		}
	}
	
	public void export(String fileName, String sep) throws IOException {
		File f = new File(fileName);
		PrintWriter pw = new PrintWriter(new FileWriter(f, false));
		for(int i=0;i<count;i++) {
			String line = "";
			for(int j=0;j<lines.length;j++) {
				line += lines[j].get(0) + sep + lines[j].get(1) + sep + lines[j].get(2);
			}
			pw.write(line);
			System.out.println("da " + lines.length);
		}
		pw.close();
		System.out.println("Info exported successfully to \"" + fileName + "\"");
	}
	
	private static String[] getFileNames() {
		String f1 = "src/project/Articles/aId.txt";
		String f2 = "src/project/Articles/aTitle.txt";
		String f3 = "src/project/Articles/aPublicator.txt";
		String f4 = "src/project/Articles/aContent.txt";
		
		String[] fileNames = {f1, f2, f3, f4};
		return fileNames;
	}
	
	
	
	
	
	
	
	
	
	/*
	try {
		this.lines[i] = Files.readAllLines(Paths.get(f.getAbsolutePath())); // 
	} catch (IOException e) {
		System.out.println("Error reading the lines of the file \"" + f.getAbsolutePath() + "\"");
		e.printStackTrace();
	}
	if(this.count > this.lines[i].size()) {
		this.count = this.lines[i].size();
	}
	*/
	
	
}
