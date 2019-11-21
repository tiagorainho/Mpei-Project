package project;
//import org.json.*;
import java.io.IOException;

public class Main {
	static final String fileName1 = "src/project/Articles/articles1.csv";
	static final String fileName2 = "src/project/Articles/articles2.csv";
	static final String fileName3 = "src/project/Articles/articles3.csv";
	
	public static void main(String[] args) {
		
		Test.doTest("ficheiro", ",");
		
		
		
	}
	
	

}




/*
JSONObject obj = new JSONObject(" .... ");
String pageName = obj.getJSONObject("pageInfo").getString("pageName");

JSONArray arr = obj.getJSONArray("posts");
for (int i = 0; i < arr.length(); i++)
{
    String post_id = arr.getJSONObject(i).getString("post_id");
    ......
}
*/