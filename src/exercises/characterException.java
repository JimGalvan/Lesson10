package exercises;

public class characterException extends Exception {
	
	static String message = "\" is not valid (it contains special characters)" 
			+ " a new puzzle will be loaded";
	
	characterException(String string){
		super(string);
	}
	

	public static void main(String[] args) {

	}

}
