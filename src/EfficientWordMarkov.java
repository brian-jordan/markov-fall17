import java.util.*;

public class EfficientWordMarkov extends WordMarkovModel{
	// Instance Variable
	Map<WordGram,ArrayList<String>> myMap;
	// Constructor
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		myMap.clear();
		for (int pos = 0; pos <= myWords.length - myOrder; pos++){
			WordGram key = new WordGram(myWords, pos, myOrder);
			ArrayList<String> nextWordSet = new ArrayList<>();
			if (! myMap.containsKey(key)){
				myMap.put(key, nextWordSet);
			}
			ArrayList<String> specific = myMap.get(key);
			if (pos == myWords.length - myOrder){
				specific.add(PSEUDO_EOS);
			}
			else {
				String nextWord = myWords[pos + myOrder];
				specific.add(nextWord);
			}
		}
	}
	
	public ArrayList<String> getFollows(WordGram key){
		try{
			return myMap.get(key);
		}
		catch(NoSuchElementException e){
			System.out.println("Error: Words are not used.");
			ArrayList<String> error = new ArrayList<>();
			return error;
		}
	}
}
