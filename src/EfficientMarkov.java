import java.util.*;

public class EfficientMarkov extends MarkovModel{
	// Instance Variable
	Map<String,ArrayList<String>> myMap;
	// Constructor
	public EfficientMarkov(int order){
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	// Fill HashMap based on text input
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int pos = 0; pos < (text.length() + 1 - myOrder); pos++){
			String key = myText.substring(pos, pos + myOrder);
			ArrayList<String> nextCharSet = new ArrayList<>();
			if (! myMap.containsKey(key)){
				myMap.put(key, nextCharSet);
			}
			ArrayList<String> specific = myMap.get(key);
			if (pos == text.length() - myOrder){
				specific.add(PSEUDO_EOS);
			}
			else {
				String nextChar = myText.substring(pos + myOrder, pos + myOrder + 1);
				specific.add(nextChar);
			}
		}
	}
	// Returns ArrayList of characters that follow the input in the text
	@Override
	public ArrayList<String> getFollows(String key){
		try{
			return myMap.get(key);
		}
		catch(NoSuchElementException e){
			System.out.println("Error: Word is not used.");
			ArrayList<String> error = new ArrayList<>();
			error.add("Error");
			return error;
		}
	}
}
