package exercises;

/* email syntax based on: https://help.returnpath.com/hc/en-us/articles/220560587-What-are-the-rules-for-email-address-syntax-
*/
public class validator {

	public static boolean phoneNumber(String string) {		
		return string.matches("[(][1-9]\\d{2}[)][1-9]\\d{2}-\\d{4}");
	}

	public static boolean email(String string) {
		
		return string.matches("[a-z]+[^@]+([a-z]|\\d)+[@]((\\d|[a-z]{2,})[.][a-z]{2,}|(\\d|[a-z]{2,})[.][a-z]{2,}[.][a-z]{2,})");
		
	}

}
