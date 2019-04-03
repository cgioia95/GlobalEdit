import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Dictionary{
	
	
		 ArrayList<String> words = new ArrayList<String>(); // or you can use an ArrayList

		// constructor: read words from a file
		@SuppressWarnings("resource")
		public Dictionary(String path) throws IOException{ 
		     
				BufferedReader br  = null;
				String line;
				
				File file = new File(path);

				br = new BufferedReader(new FileReader(file));

				while ((line = br.readLine()) != null){
				        words.add(line.trim());
				}
				} 
		 }