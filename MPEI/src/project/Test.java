package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {
	static final Scanner input = new Scanner(System.in);
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void doTest(String fileName, String sep) throws IOException {
		
		
		Dataset dataset = new Dataset(50000);
		dataset.setMaxValues(20000);
		dataset.addValuesCSV(fileName);
		System.out.println(dataset.toString());
		
		
		dataset.showSimilarTitles(0.9, 100);
		//dataset.showSameTitleSimilarContent(0.4);
		
		
		/*
		String[] list = {"porra so quero acabar este projeto", "ola como estas tudo bem", "ola tudo bem como estas"};
		MinHash minHash = new MinHash(100);
		minHash.add(list);
		
		int v1 = 2;
		int v2 = 1;
		System.out.println("Teorico: " + minHash.jaccardCoeficient(list[v1], list[v2]));
		System.out.println("Pratico: " + minHash.getSimilarity(v1, v2));
		*/
		
		
		//dataset.showSimilarTitles(0.8, 10);
		//dataset.getSameNews(0.8, 100);
		
		//dataset.showSimilarNews(0.8, 100);
		
		
		/*
		int val = 10000;
		String m = getRandomString(val-1,val);
		String n = getRandomString(val-1, val);
		String[] a= {m,n};
		*/
		//String[] a= {"faeoifhaeuofhaeoiwfaof a haeufhaeou uoahuf auh faeuohf aehfahoufiwajoda+ºdofaekjfgaeook", "jnaoefjap maoj fjaei jfoa p kaiido+awºlĸð nmg sjkg ae ae8fseufsefseifhesoi"};
		//String[] a = {"Stuck at your work desk? Standing up and walking around for five minutes every hour during the workday could lift your mood, combat lethargy without reducing focus and attention, and even dull hunger pangs, according to an instructive new study. The study, which also found that frequent, brief walking breaks were more effective at improving   than a single, longer walk before work, could provide the basis for a simple, realistic New Year’s exercise resolution for those of us bound to our desks all day. There is growing evidence, of course, that long bouts of uninterrupted sitting can have undesirable physical and emotional consequences. Studies have shown that sitting motionless reduces blood flow to the legs, increasing the risk for atherosclerosis, the buildup of plaques in the arteries. People who sit for more than eight or nine hours daily, which for many of us describes a typical workday, also are at heightened risk for diabetes, depression and obesity compared with people who move more often. In response, researchers and some bosses have proposed a variety of methods for helping people reduce their sitting time at work, including standing workstations and treadmill desks. But such options are cumbersome and costly, making them impractical for many work situations. Some experts have worried, too, that if people are physically active at the office, they might subsequently become more tired, grumpy, distracted or hungry, any of which could have an undesirable effect on work performance and   health. So for the new study, which was published in November in the International Journal of Behavioral Nutrition and Physical Activity, researchers from the University of Colorado Anschutz Medical Campus, the Johnson  Johnson Human Performance Institute and other institutions decided to test several methods of increasing movement among office workers. (The study was funded largely by Johnson  Johnson, with additional support from the Colorado Nutrition Obesity Research Center.) To start, the researchers invited 30 sedentary adult office workers to a university clinic to complete a battery of health tests and questionnaires. The researchers measured their heart rates and stress hormone levels and asked them to rank, on a numerical scale, how energetic or tired they felt, as well as how happy they were, and whether they were feeling peckish or had little appetite just then. The volunteers also completed computerized games designed primarily to test their ability to concentrate and make decisions. Then, on three subsequent visits to the clinic, each volunteer simulated a   workday. During one visit, the volunteers sat for the whole time with no interruptions, except for bathroom breaks. During another, they walked moderately for 30 minutes at the start of their experimental day, and then sat for the next five and a half hours with no additional scheduled breaks. Finally, during a third visit, the volunteers sat for most of the six hours, but began each hour with five minutes of moderate walking, using treadmills at the clinic. At the start and end of each session, the researchers drew blood to check levels of stress hormones. And periodically throughout each day, they asked their volunteers to numerically rate their moods, energy, fatigue and appetites. The volunteers also repeated the computerized testing of their thinking skills at the close of each session. The researchers then analyzed the data. The numbers showed that on almost all measures, the subjects’ ratings of how they were feeling rose when they did not sit for six uninterrupted hours. They said that they felt much more energetic throughout the day if they had been active, whether that activity was bunched into a single longish walk at the start of the day or distributed into multiple brief breaks. On other measures, though, the   walks were more potent than the concentrated   version. When the workers rose most often, they reported greater happiness, less fatigue and considerably less craving for food than on either of the other days. Their feelings of vigor also tended to increase throughout the day, while they often had plateaued by early afternoon after walking only once in the morning. There were no differences on the scores on the cognitive tests, whether they sat all day or got up and moved. Stress hormones also remained steady during each visit. These results suggest that “even a little bit of activity, spread throughout the day, is a practical, easy way to improve ” says Jack Groppel, a study author and a founder of the Johnson  Johnson Human Performance Institute. He points out that the walking breaks did not cause people to feel more tired or hungry, but instead had the opposite effect. They also did not alter people’s ability to focus, so, in theory, should not affect productivity (for good or ill). This study, however, was small in scale,   and limited by its dependence on the volunteers’ perceptions of their responses to the experiment. But even so, “it’s clear that moving matters,” Dr. Groppel says. So set your 2017 appointment calendar, he suggests, to devote five minutes every hour to physical activity, whether you walk up and down a staircase, along a corridor or just pace around your office.", "With the year winding down and New Year’s resolutions just around the corner, it’s time to gear up for that   clutter purge. But the thought of tackling the kitchen junk drawer  —   or even taking down the decorations  —   can be overwhelming. So to help motivate you, several organizing professionals offered tips on how to streamline your closets, beat back the toys taking over the living room and, yes, finally deal with that junk drawer. START WITH THE HOLIDAY TRAPPINGS To keep plastic ornaments and Hanukkah candles organized, use small containers you already have on hand, like popcorn and cookie tins or empty shoeboxes and egg cartons. Wrap fragile ornaments in leftover tissue paper or newspaper, or invest in storage containers like archival ornament boxes from the Container Store. For strings of lights and garlands, use zip ties to avoid tangles and put them in plastic bags labeled according to use (tree lights, porch lights, balcony, bushes, etc. ). Zip ties can also be used on wreaths, so that they can be hung on coat hangers and covered with plastic garment bags from the dry cleaner. To avoid a stockpile of holiday cards, “save only those that are meaningful and special,” Tova Weinstock, a professional organizer in Brooklyn, said. “For the others, appreciate them as you receive them and then kindly send them to the trash. ” EDIT YOUR CLOSET Eliminating clothes you never wear is easier said than done. “I don’t believe in ‘If you haven’t used or worn something in a year, get rid of it,’” said Laura Cattano, another organizer in Brooklyn. “How you want to live moving forward is a much better guide for editing than whether or not you actually have used something in the past. ” Focus on the image you would like to project, she said, then start at one end of the closet and review each piece, pulling out those you know you can toss, donate or sell. Try on anything you haven’t worn in a while and consider how it makes you feel or if it could be styled differently. Clothes you have forgotten about could be “your new favorite thing,” she said, while there may be some pieces you wear all the time that should be retired. (If you subscribe to the Marie Kondo method of decluttering, ask yourself, “Does it ‘spark joy? ’”) Sort clothes by type, grouping pants together, then shirts or blouses, jackets or dresses. “If you can’t see it, you won’t wear it,” said Sharon Lowenheim, an organizer on the Upper East Side of Manhattan and the founder of the Organizing Goddess. “So remove dry cleaning bags and hang only one item per hanger. ” Arranging shirts by sleeve length, with the shortest at the front, makes them easier to find, said Ms. Lowenheim, who also subscribes to a rotation method: selecting garments to wear from the front of each section, then hanging them up toward the back after they are cleaned. “If you find yourself continually bypassing an item at the front, that is a signal to you that you just don’t like that item,” she said. “Get rid of it. ” Another way to keep your closet streamlined is to store   clothes out of sight, said Faith Roberson, an organizer in Manhattan and the founder of Organize With Faith. When clothes have been put away for half the year and you’ve done fine without them, “it changes your perspective,” she said. “You can honestly ask yourself, ‘Do I actually love this piece? And if I don’t, why am I holding onto it? ’” TACKLE THE TOYS Organizing pros agree that the best way to keep toys from taking over is to get children involved in selecting some to donate. “Finding a charity that provides toys to less fortunate children is very motivating for a child,” said Ms. Lowenheim, whose    daughter purged her playthings in this manner before each birthday and holiday, starting when she was a toddler. Before that, Ms. Lowenheim said, she would sneak into her daughter’s room while she was sleeping to gather up the games and stuffed animals she was no longer playing with. “When she was around 3, she started noticing if something was missing and would ask me about it,” Ms. Lowenheim said. “At that point, I realized that I had to involve her in the process. ” If they cooperate, be sure to respect their decision, Ms. Roberson said, even if they choose something you wouldn’t have. “Parents think about the money they’ve spent,” she said, or the sentimental value, which can make them reluctant to let go of that American Girl doll or the train set from Aunt Joan. Another strategy is to encourage them to sell the toys they no longer play with, either online or at a garage sale, and keep the proceeds for themselves. Once the toys have been pared down, Ms. Roberson swears by clear shoe or sweater drawers for storage. “They are stackable, they have dividers, are easy to clean, easy to label, and they come in four different sizes,” she said. To help maintain order, “label where each toy goes,” said Ms. Lowenheim, who recommends installing a tall bookcase and putting paints, jigsaw puzzles and other games that tend to leave a mess on the highest shelf. If your child is too young to read, label the containers with stickers or pictures. If everything has “a home” to return to at the end of the day, she said, cleanup will be easier. AND THAT JUNK DRAWER . .. “Don’t waste your time standing over the drawer sifting through it piece by piece,” Ms. Roberson said. Dump everything onto the floor or countertop, divide it into piles and then ask yourself: Do I want it? Does it work? Does it need to be in the junk drawer? If not, where else could it go? “Take it easy, and go pile by pile,” she said. Put things where they belong (like that miniature tube of toothpaste you got from the dentist that should go in your toiletries bag) and discard things you don’t need, like old keys and expired batteries and coupons. To ward off atrophy, buy a drawer organizer, suggested Ms. Lowenheim, who uses expandable drawer organizers from Staples in her own home. “The key thing is to measure the drawer, then find the organizer that best fits the space,” she said. “If it’s too small, it won’t help. ” The reward? “Keeping everything sorted by type,” she said, “will make it easier for you to find something when you need it. "};
		/*MinHash b = new MinHash(100, 10);
		b.add(a);
		System.out.println(b.getSimilarity(0, 1));
		*/
		
		
		
		
		//testBloomFilter(dataset, 1000000);
	}
	
	private static Menu getMenu() {
		/*					METER NA MAIN
		boolean cond = true;
		Menu menu = getMenu();
		while(cond) {
			menu.show();
			String a = input.nextLine();
		}*/
		
		
		String content =  "[1] Fazer A \n"
						+ "[2] Fazer B\n"
						+ "[3] Fazer c \n"
						+ "[4] \n";
		return new Menu(content);
	}
	
	private static String showPublicators(Dataset dataset) {
		String content = "";
		String[] publicators = dataset.getPublicators();
		for(int i=0;i<publicators.length;i++) {
			content += String.format("%d - %s\n", i+1, publicators[i]);
		}
		return content;
	}
	
	private static boolean testInserts(Dataset dataset) {
		dataset.showPublicationsFast();
		System.out.println(dataset.toString());
		return true;
	}
	
	private static void testBloomFilter(Dataset dataset, int value) {
		String[] randomStrings = getRandomStrings(value);
		testBloomFilterOptimized(dataset, randomStrings);
		testBloomFilterIncremental(dataset, randomStrings);
		testBloomFilterIncrementalMoreThanOne(dataset, randomStrings);
	}
	
	private static void testBloomFilterIncrementalMoreThanOne(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER INCREMENTAL \"More than one\" TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int count = 0;
		for(int i=0;i<d.size();i++) {
			if(dataset.containsMoreThanOneTitle(d.get(i).getTitle())) {
				count++;
			}
		}
		System.out.println("Bloom Filter: percentage of mapped keys more than once is: " + (double) (count*100)/d.size() + "% (" + (d.size() - count) + "/" + d.size() + ")");
		count = 0;
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsMoreThanOneTitle(randomValues[i])) {
				count++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (count*100)/randomValues.length + "%  (" + count + "/" + randomValues.length + ")");
	}
	
	private static void testBloomFilterIncremental(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER INCREMENTAL TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int errors = 0;
		for(int i=0;i<d.size();i++) {
			if(!dataset.containsTitleIncremental(d.get(i).getTitle())) {
				System.out.println("ERROR - " + d.get(i).getTitle());
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of true positives is: " + (double) (100-(errors*100)/d.size()) + "% (" + (d.size() - errors) + "/" + d.size() + ")");
		errors = 0;
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsTitleIncremental(randomValues[i])) {
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (errors*100)/randomValues.length + "%  (" + errors + "/" + randomValues.length + ")");
	}
	
	private static void testBloomFilterOptimized(Dataset dataset, String[] randomValues) {
		System.out.println("BLOOM FILTER OPTIMIZED TEST...");
		ArrayList<Publication> d = dataset.getDataset();
		int errors = 0;
		for(int i=0;i<d.size();i++) {
			if(!dataset.containsTitle(d.get(i).getTitle())) {
				System.out.println("ERROR - " + d.get(i).getTitle());
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of true positives is: " + (double) (100-(errors*100)/d.size()) + "% (" + (d.size() - errors) + "/" + d.size() + ")");
		errors = 0;
		for(int i=0;i<randomValues.length;i++) {
			if(dataset.containsTitle(randomValues[i])) {
				errors++;
			}
		}
		System.out.println("Bloom Filter: percentage of false positives is: " + (double) (errors*100)/randomValues.length + "%  (" + errors + "/" + randomValues.length + ")");
		BloomFilterIncremental a = dataset.getBloomFilterIncremental();
	}
	
	private static String[] getRandomStrings(int values) {
		String[] strings = new String[values];
		for(int i=0;i<values;i++) {
			strings[i] = getRandomString(5,20);
		}
		return strings;
	}
	
	private static String getRandomString(int min, int max) {
		String numbers = "1234567890";
		String lowers = "abcdefghijklmnopqrstuvwxyz";
		String universe = numbers + lowers + lowers.toUpperCase();
		String content = "";
		int len = (int) (Math.random()*(max-min) + min);
		for(int i=0;i<len;i++) {
			content += universe.charAt((int) (Math.random()*universe.length()));
		}
		return content;
	}
	
}
