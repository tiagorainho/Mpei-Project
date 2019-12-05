package project;
import java.io.File;
import java.io.FileNotFoundException;
//import org.json.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
<<<<<<< Updated upstream
	public static void main(String[] args) throws FileNotFoundException {
=======
	static final Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		Dataset dataset = null;
		
		int option = 0;
			
		System.out.println("\t---Inicializar o DataSet---");
		System.out.print("Tamanho aproximado de elementos: ");
		int numValores = readInteger();
		//inicializar o Dataset
		dataset = new Dataset(numValores, true); //storageOptimization -> true, usa o BlommFilterOptimized
		System.out.println("Deseja definir um limite para a an�lise de dados?(�til para testes!)[s/n]: ");
		char anwser = readChar();
		if(anwser == 's' || anwser == 'y') {
			System.out.print("Limite: ");
			int maxValues = readInteger();
			if(dataset.size() < maxValues) {
				dataset.setMaxValues(maxValues);
			}else {
				System.out.println("N�o foi poss�vel definir um m�ximo!");
			}
		}
		
		System.out.println("As not�cias utilizadas s�o provenientes dos ficheiros de teste utilizados no trabalho\n-> Articles/articles1.csv\n-> Articles/articles2.csv\n-> Articles/articles3.csv");
		System.out.println("Reading Files...");
		// ler conte�do dos ficheiros e introduzir os respectivos t�utlos no BloomFilterIncremental
		dataset.addValuesCSV(new File(fileName1), ",");
		//dataset.addValuesCSV(new File(fileName2), ",");
		//dataset.addValuesCSV(new File(fileName3), ",");
		System.out.println("Files have been successfully read!");
>>>>>>> Stashed changes
		
		do {
			option = menuInicial();
			System.out.println(option);
			
			switch(option) {	
			case 1:
				System.out.println(dataset.toString());
				break;
				
			case 2:
				dataset.showPublicationsFast(dataset.getMaxValues()); //diferen�a entre os 4 show publicactions?
				break;
				
			case 3:
				Publication p = lerNoticia();
				dataset.addToDataset(p);
				break;
				
			case 4:
				//fun��o do rainho
				
				break;
				
			case 5:
				int optionTrabalho = 0;
				do {
					optionTrabalho = menuTrabalho();
					
					switch(optionTrabalho) {
					case 1:
						//dataset.getEqualTitles()
						break;
						
					case 2:
						System.out.print("Especificar limiar de semelhan�a: ");
						double threshHold = readDouble();
						dataset.showSimilarTitles(threshHold);
						
						if(System.getProperty("os.name").contains("Windows")) {
							System.out.print("Os resultados obtidos foram exportados para o ficheiro \"src\\logs\\titles.txt\"");
						}else {
							System.out.print("Os resultados obtidos foram exportados para o ficheiro \"src/logs/titles.txt\"");
							//faltam testes aqui!
						}
						break;
						
					case 3:
						System.out.print("Especificar limiar de semelhan�a: ");
						double threshHold1 = readDouble();
						dataset.showSimilarNews(threshHold1);
						
						if(System.getProperty("os.name").contains("Windows")) {
							System.out.print("Os resultados obtidos foram exportados para o ficheiro \"src\\logs\\titles.txt\"");
						}else {
							System.out.print("Os resultados obtidos foram exportados para o ficheiro \"src/logs/titles.txt\"");
							//faltam testes aqui!
						}
						break;
						
					case 4:
						
						break;
						
					default: 
						System.out.println("Introduza uma op��o existente por favor!");
						break;
					}
				}while(optionTrabalho != 4);
				break;
				
			case 6:
				System.out.println("Obrigado!");
				System.exit(0);
				
				break;
				
			default:
				System.out.println("Introduza uma op��o existente por favor!");
				break;
			}
			
		}while(option != 0);		
		
	}


	private static Publication lerNoticia() {
		System.out.print("Autor: ");
		String author = read.nextLine();
		System.out.print("Editora: ");
		String edit = read.nextLine();
		System.out.print("T�tulo: ");
		String tit = read.nextLine();
		System.out.print("Conte�do: ");
		String content = read.nextLine();
		System.out.print("Id da not�cia: ");
		int id = readInteger();
		
		return new Publication(id, author, tit, edit, content);
	}



	//----------Menus----------
	private static int menuInicial() {
		System.out.println("\t--------Menu Inicial-------"); 
		System.out.println("1) Mostar informa��o do dataset,\n"
				+ "2) Mostrar not�cias presentes no dataset,\n"
				+ "3) Introduzir + not�cias ao dataset,\n"
				+ "4) Mostrar not�cias similares com base no t�tulo da not�cia,\n"
				+ "5) Objectivos do trabalho (falar sobre isto no relat�rio),\n"
				+ "6) Sair.");
		
		System.out.print("-->");
		return readInteger();
	}
	
	//subMenu
	private static int menuTrabalho() {
		System.out.println("\t-------Menu Trabalho-------");
		System.out.println("1) Lista de not�cias repetidas com t�tulos iguais,\n"
				+ "2) Lista de not�cias com t�tulos similares,\n"
				+ "3) Pares de not�cias com conte�dos similares,\n"
				+ "4) Voltar.\n");
		
		System.out.print("-->");
		return readInteger();
	}
	
	
	//----------Util-----------
	private static int readInteger() {
		int num = 0;
		try {
			num = Integer.parseInt(read.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Introduza um n�mero por favor!");
			e.printStackTrace();
			System.exit(0);
		}
		return num;
	}
	
	private static double readDouble() {
		double num = 0;
		try {
			num = Double.parseDouble(read.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Introduza um n�mero por favor!");
			e.printStackTrace();
			System.exit(0);
		}
		return num;
	}
	
	private static char readChar() {
		char val = '\u0000';
		String line = read.nextLine();
		if(line.length() > 1) {
			System.out.println("Introduza um caracter por favor!");
			System.exit(0);
		}
		try {
			val = line.charAt(0);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Introduza um caracter por favor!");
			e.printStackTrace();
			System.exit(0);
		}
		return val;
	}