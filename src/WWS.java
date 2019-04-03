
public class WWS implements Comparable<WWS>{
	
	int score;
	String word;

	
	public WWS(String word, int score) {
		this.word = word;
		this.score = score;
	}


	@Override
	public int compareTo(WWS o) {
		return this.score - o.score;
	}
}
