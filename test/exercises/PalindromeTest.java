package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest {
	
	Palindrome testPalindrome = new Palindrome();

	// 2. Test that a word exists in the dictionary 
	
	@Test
	void testWordExist() throws Exception {
		assertTrue(testPalindrome.wordExist("civic"));
	}

	// 3. Test that a word is a palindrome
	
	@Test
	void isPalindromeTest() throws Exception {
		assertTrue(testPalindrome.isPalindrome("level"));
	}

}
