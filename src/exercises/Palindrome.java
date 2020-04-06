package exercises;

import static org.junit.Assert.fail;
import java.util.List;

import examples.FileHelper;

public class Palindrome {

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}
	void test() {
		fail("no yet implemented");
	}

	boolean wordExist(String word) {
		return loadWords().contains(word);
	}
	
	boolean isPalindrome(String candidate) {
				
		if (wordExist(candidate)) {
		
		String word = "";
		String character;
		
		for (int i = candidate.length(); i > 0; i--) {
	    	char value = candidate.charAt(i - 1);
	    	character = String.valueOf(value);
	    	word = word + character;
		}
		    return word.equalsIgnoreCase(candidate);
		} else 
			return false;
}
}
