package project;
import java.io.File;
import java.io.FileNotFoundException;
//import org.json.*;
import java.io.IOException;
import java.util.Scanner;

public class GUI {
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	static final Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		Dataset dataset = null;
		
		int option = 0;
			
		System.out.println("\t---Inicializar o DataSet---");
		System.out.print("Tamanho aproximado de elementos: ");
		int numValores = readInteger();
		//inicializar o Dataset
		dataset = new Dataset(numValores, true); //storageOptimization -> true, usa o BlommFilterOptimized
		System.out.println("Deseja definir um limite para a análise de dados?(útil para testes!)[s/n]: ");
		char anwser = readChar();
		if(anwser == 's' || anwser == 'y') {
			System.out.print("Limite: ");
			int maxValues = readInteger();
			if(dataset.size() < maxValues) {
				dataset.setMaxValues(maxValues);
			}else {
				System.out.println("Não foi possível definir um máximo!");
			}
		}
		
		System.out.println("As notícias utilizadas são provenientes dos ficheiros de teste utilizados no trabalho\n-> Articles/articles1.csv\n-> Articles/articles2.csv\n-> Articles/articles3.csv");
		System.out.println("Reading Files...");
		// ler conteúdo dos ficheiros e introduzir os respectivos tíutlos no BloomFilterIncremental
		dataset.addValuesCSV(new File(fileName1), ",");
		//dataset.addValuesCSV(new File(fileName2), ",");
		//dataset.addValuesCSV(new File(fileName3), ",");
		System.out.println("Files have been successfully read!");
		
		do {
			option = menuInicial();
			System.out.println(option);
			
			switch(option) {	
			case 1:
				System.out.println(dataset.toString());
				break;
				
			case 2:
				dataset.showPublicationsFast(dataset.getMaxValues()); //diferença entre os 4 show publicactions?
				break;
				
			case 3:
				Publication p = lerNoticia();
				dataset.addToDataset(p);
				break;
				
			case 4:
				//função do rainho
				
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
						System.out.print("Especificar limiar de semelhança: ");
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
						System.out.print("Especificar limiar de semelhança: ");
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
						System.out.println("Introduza uma opção existente por favor!");
						break;
					}
				}while(optionTrabalho != 4);
				break;
				
			case 6:
				System.out.println("Obrigado!");
				System.exit(0);
				
				break;
				
			default:
				System.out.println("Introduza uma opção existente por favor!");
				break;
			}
			
		}while(option != 0);		
		
	}


	private static Publication lerNoticia() {
		System.out.print("Autor: ");
		String author = read.nextLine();
		System.out.print("Editora: ");
		String edit = read.nextLine();
		System.out.print("Título: ");
		String tit = read.nextLine();
		System.out.print("Conteúdo: ");
		String content = read.nextLine();
		System.out.print("Id da notícia: ");
		int id = readInteger();
		return new Publication(0, author, tit, edit, content);
	}



	//----------Menus----------
	private static int menuInicial() {
		System.out.println("\t--------Menu Inicial-------"); 
		System.out.println("1) Mostar informação do dataset,\n"
				+ "2) Mostrar notícias presentes no dataset,\n"
				+ "3) Introduzir + notícias ao dataset,\n"
				+ "4) Mostrar notícias similares com base no título da notícia,\n"
				+ "5) Objectivos do trabalho (falar sobre isto no relatório),\n"
				+ "6) Sair.");
		
		System.out.print("-->");
		return readInteger();
	}
	
	//subMenu
	private static int menuTrabalho() {
		System.out.println("\t-------Menu Trabalho-------");
		System.out.println("1) Lista de notícias repetidas com títulos iguais,\n"
				+ "2) Lista de notícias com títulos similares,\n"
				+ "3) Pares de notícias com conteúdos similares,\n"
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
			System.out.println("Introduza um número por favor!");
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
			System.out.println("Introduza um número por favor!");
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
}
