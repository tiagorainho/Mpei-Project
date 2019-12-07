package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GUI {
	static final String fileName1 = "Articles/articles1.csv";
	static final String fileName2 = "Articles/articles2.csv";
	static final String fileName3 = "Articles/articles3.csv";
	static final Scanner read = new Scanner(System.in);
	private static String optionS;
	private static boolean aux;
	private static int option;
	private static double threshHold;
	
	public static void main(String[] args) throws IOException {
		
		Dataset dataset = initiate();
		
		do {
			menuInicial();
			optionS = read.nextLine();
			
			switch(optionS) {	
			case "1":
				System.out.println(dataset.toString());
				break;
				
			case "2":
				dataset.showPublicationsFast();
				break;
				
			case "3":
				Publication p = lerNoticia();
				dataset.addToDataset(p);
				break;
			case "4":
				System.out.print("Nome do Autor: ");
				String aut = read.nextLine();
				if(dataset. authorExits(aut)){
					System.out.println("O autor "+aut+" existe no Dataset!");
				}
				else{
					System.out.println("O autor "+aut+" não existe no Dataset!");
				}
				break;
			case "5":
				dataset.showSameTitleSimilarContent(readDouble("Especificar limiar de semelhança: "));
				break;
				
			case "6":
				int optionTrabalho = 0;
				do {
					menuTrabalho();
					optionS = read.nextLine();
					
					switch(optionS) {
					case "1":
						dataset.showPublicationsWithEqualTitles();
						break;
						
					case "2":
						dataset.showSimilarTitles(readDouble("Especificar limiar de semelhança: "));
						showLogInfo();
						break;
						
					case "3":
						dataset.showSimilarNews(readDouble("Especificar limiar de semelhança: "));
						showLogInfo();
						break;
						
					default: 
						System.out.println("Opcao nao reconhecida");
						break;
					}
				}while(optionS.equals("4"));
				break;
				
			case "7":
				System.out.println("Saiu!");
				System.exit(0);
				break;
				
			default:
				System.out.println("Opcao nao reconhecida");
				break;
			}
			
		}
		while(true);		
		
	}
	
	private static void showLogInfo() {
		if(System.getProperty("os.name").contains("Windows")) {
			System.out.print("Os resultados obtidos foram exportados para o ficheiro \"logs\\news.txt\"\n");
		}
		else {
			System.out.print("Os resultados obtidos foram exportados para o ficheiro \"logs/news.txt\"\n");
		}
	}
	
	private static Dataset initiate() {
		System.out.println("#####\tNEWS FINDER\t#####");
		Dataset dataset = new Dataset(200000); //storageOptimization -> true, usa o BlommFilterOptimized
		do {
			System.out.println("Deseja definir um limite para a analise de dados?(Util para testes!)[s/n]: ");
			aux = true;
			optionS = read.nextLine();
			if(optionS.equalsIgnoreCase("s") || optionS.equalsIgnoreCase("n")) {
				aux = false;
			}
			else {
				System.out.println("Comando nao reconhecido");
			}
		}
		while(aux);
		int value = 0;
		if(optionS.equalsIgnoreCase("s")) {
			do {
				aux = true;
				try {
					System.out.print("Limite: ");
					optionS = read.nextLine();
					value = Integer.parseInt(optionS);
					if(String.valueOf(value).equals(optionS)) {
						aux = false;
					}
				}
				catch(Exception e) {
					System.out.println("Limite nao reconhecido");
				}
			}
			while(aux);
			dataset.setMaxValues(value);
		}
		System.out.println("As noticias utilizadas sao provenientes dos ficheiros de teste utilizados no trabalho\n-> Articles/articles1.csv\n-> Articles/articles2.csv\n-> Articles/articles3.csv");
		// ler conteudos dos ficheiros e introduzir os respectivos tiutlos no BloomFilterIncremental
		try {
			dataset.addValuesCSV(fileName1);
			dataset.addValuesCSV(fileName2);
			dataset.addValuesCSV(fileName3);
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao ler os ficheiros!");
			e.printStackTrace();
		}
		System.out.println("Ficheiros lidos corretamente!");
		return dataset;
	}

	private static Publication lerNoticia() {
		System.out.print("Autor: ");
		String author = read.nextLine();
		System.out.print("Editora: ");
		String edit = read.nextLine();
		System.out.print("Titulo: ");
		String tit = read.nextLine();
		System.out.print("Conteudo: ");
		String content = read.nextLine();
		return new Publication(author, tit, edit, content);
	}



	//----------Menus----------
	private static void menuInicial() {
		System.out.println("\t--------Menu Inicial-------"); 
		System.out.println("1) Mostar informaçao do dataset\n"
				+ "2) Mostrar noticias presentes no dataset\n"
				+ "3) Introduzir mais noticias ao dataset\n"
				+ "4) Verificar se existe um autor\n"
				+ "5) Lista noticias similares com titulos iguais\n"
				+ "6) Objectivos do trabalho\n"
				+ "7) Sair.");
		
		System.out.print("-->");
	}
	
	//subMenu
	private static void menuTrabalho() {
		System.out.println("\t-------Menu Trabalho-------");
		System.out.println("1) Lista de noticias repetidas com titulos iguais\n"
				+ "2) Lista de noticias com titulos similares\n"
				+ "3) Pares de noticias com conteudos similares\n"
				+ "4) Voltar.\n");
		
		System.out.print("-->");
	}
	
	private static double readDouble(String str) {
		while(true) {
			aux = true;
			System.out.print(str);
			optionS = read.nextLine();
			try {
				threshHold = Double.parseDouble(optionS);
			}
			catch(Exception e) {
				System.out.println("Valor nao reconhecido");
			}
			if(threshHold >= 0 && threshHold <= 1) {
				if(String.valueOf(threshHold).equals(optionS)) {
					return threshHold;
				}
			}
			else {
				System.out.println("Os valores do limiar de semelhanca devem estar entre 0 e 1 inclusive");
			}
		}
	}
}