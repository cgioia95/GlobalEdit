import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class GlobalEditDistance {
	
	public static int accuracy = 0;
	public static int no_words_found = 0;

	
	public static int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) {
		dp[i][0] = i;
		}
		for (int j = 0; j <= len2; j++) {
		dp[0][j] = j;
		}
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
		char c1 = word1.charAt(i);
		for (int j = 0; j < len2; j++) {
		char c2 = word2.charAt(j);
		//if last two chars equal
		if (c1 == c2) {
		//update dp value for +1 length
		dp[i + 1][j + 1] = dp[i][j];
		} else {
		int replace = dp[i][j] + 1;
		int insert = dp[i][j + 1] + 1;
		int delete = dp[i + 1][j] + 1;
		int min = replace > insert ? insert : replace;
		min = delete > min ? min : delete;
		dp[i + 1][j + 1] = min;
		}
		}
		}
		return dp[len1][len2];
		}
	
  
		
		

	
	
	public static void main(String[] args) throws IOException {
		
		FileWriter writer = new FileWriter("C:\\Users\\Marina Office\\Google Drive\\UNI\\Knowledge Technologies\\Project 1\\2019S1-proj1-data\\res.csv");
		
		writer.append("Mispelt,Predicted,Correct");
		writer.append("\n");
		
		// Constructed an array list of our dictionary words
		Dictionary dict = new Dictionary("C:\\Users\\Marina Office\\Google Drive\\UNI\\Knowledge Technologies\\Project 1\\2019S1-proj1-data\\dict.txt");
		
		System.out.println(dict.words.size());
		
		Dictionary misspell = new Dictionary("C:\\Users\\Marina Office\\Google Drive\\UNI\\Knowledge Technologies\\Project 1\\2019S1-proj1-data\\misspell.txt");

		System.out.println(misspell.words.size());

		Dictionary correct = new Dictionary("C:\\Users\\Marina Office\\Google Drive\\UNI\\Knowledge Technologies\\Project 1\\2019S1-proj1-data\\correct.txt");
		
		System.out.println(correct.words.size());
		
		
//		 Cycle through all the mispelt words
		for (int i=0; i < misspell.words.size(); i++) {
			
			
			// Declare the mispelt word and its associated correct word, aswell as an arraylist for this word of the closest words to it in the dictionary 
			String mispeltWord = misspell.words.get(i);
			
		
			writer.append(mispeltWord + ",");
			
			String correctWord = correct.words.get(i);
			

			
			String finalAnswer;
			
			ArrayList<WWS> candidates = new ArrayList<WWS>(); // or you can use an ArrayList
			
			// Cycle through the dictionary, each cycle declare the dictionary word inspected
			// if minEditDistance bwetween our mispelt word and this dictWord is < the threshold, then add it to our list of close word 
			for (int j=0; j < dict.words.size(); j++) {
				
				String dictWord = dict.words.get(j);
				
				if (minDistance(mispeltWord, dictWord) <= 1){
					
					WWS wws = new WWS(dictWord, minDistance(mispeltWord, dictWord));
					
					candidates.add(wws);
					
				}
				
			}
			
			
			
			if(candidates.size()>0) {
				
				Collections.sort(candidates);
				
				finalAnswer = candidates.get(0).word;
				writer.append(finalAnswer + ",");
						
			}
			
			else {
				writer.append("NONE_FOUND");
			}
			
			
				System.out.println(i+1);
				writer.append(correctWord + ",");
				writer.append("\n");
				
			}
			
		writer.close();

	}
}


