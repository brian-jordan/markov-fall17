
public class WordGram implements Comparable<WordGram>{
	// Instance Variables
	private String[] myWords;
	private int myHash;
	// Construcor
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		for (int i = 0; i < size; i++){
			myWords[i] = words[index];
			index++;
		}
		// myHash = 17;
	}
	
	@Override
	public int hashCode() {
		if (myHash == 0){
			int[] primes = {13,23,31,41,53};
			for (int i = 0; i < myWords.length; i++){
				myHash += myWords[i].hashCode() * primes[i % 5];
			}
		}
		return myHash;
	}
	
	@Override
	public String toString() {
			return String.join(" ", myWords);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other;
		int comparison = compareTo(wg);
		if (comparison == 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int compareTo(WordGram wg) {
		int compare = 0;
		for (int i = 0; i < Math.min(myWords.length,wg.length()); i++){
			compare = this.myWords[i].compareTo(wg.myWords[i]);
			if (compare != 0)
				break;
		}
		if (compare == 0 && myWords.length != wg.length()){
			compare = myWords.length - wg.length();
		}
		return compare;
	}
	
	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		String[] shifted = new String[myWords.length];
		int i;
		for (i = 0; i < (shifted.length - 1); i++){
			shifted[i] = myWords[i + 1];
		}
		shifted[i] = last;
		WordGram newest = new WordGram(shifted, 0, shifted.length);
		return newest;
	}
}
