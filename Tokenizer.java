import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tokenizer {
	
	/**
     * Returns the current token.
     */
	private static int getToken() {	
		return 0;
    }
	
	/**
     * Skips the current token; next token becomes current token.
     */
	private static void skipToken() {

    }
	
	/**
     * Returns the value of the current (integer) token.
     * If the token is not an integer then returns an error.
     */
	private static int intVal(String token) {
		int value = 0;
		
		// Check if token is a valid integer
		boolean num = true;
		for (int i = 0; i < token.length(); i++){
			if(!Character.isDigit(token.charAt(i))){
				num = false;
			}
		}
		if (!num){
			System.out.println("Error: Invalid integer token");
			System.exit(0);
		}
		else{
			value = Integer.parseInt(token);
		}
		return value;
    }
	
	/**
     * Returns the name (string) of the current (id) token.
     * If the token is not an id then returns an error.
     */
	private static String idName() {
		return "";
    }
	
	/**
     * Determines the type of token and returns the type.
     */
	private static String tokenType(String token) {
		String type = "";
		
		// Get starting character of token
		char first = token.charAt(0);
		
		if (Character.isLowerCase(first)){
			// Character is lower case so must be reserved
			type = "reserved";
		}
		else if (Character.isUpperCase(first)){
			// Character is upper case so must be an identifier
			type = "identifier";
		}
		else if (Character.isDigit(first)){
			// Character is a digit so must be an integer
			type = "integer";
		}
		else {
			// Character is a special character
			type = "special";
		}
		return type;
    }
	
	/**
     * Returns the corresponding integer for the reserved token.
     * If the token is not valid then returns an error.
     */
	private static int reserved(String token) {
		int value = 0;
		if (token.equals("program")){
			value = 1;
		}
		else if (token.equals("begin")){
			value = 2;
		}
		else if (token.equals("end")){
			value = 3;
		}
		else if (token.equals("int")){
			value = 4;
		}
		else if (token.equals("if")){
			value = 5;
		}
		else if (token.equals("then")){
			value = 6;
		}
		else if (token.equals("else")){
			value = 7;
		}
		else if (token.equals("while")){
			value = 8;
		}
		else if (token.equals("loop")){
			value = 9;
		}
		else if (token.equals("read")){
			value = 10;
		}
		else if (token.equals("write")){
			value = 11;
		}
		else {
			System.out.println("Error: Invalid reserved token");
			System.exit(0);
		}
		return value;
    }

	public static void main(String[] args) {
		// Get file name from command line argument
		String fileName = args[1];
		
		try {
			// Open file & read stream
			FileReader file = new FileReader(fileName);
			BufferedReader br = new BufferedReader(file);
			
			// Read each line and tokenize
			String line;
			while((line = br.readLine()) != null){
				// Split tokens from line into an array
				// Currently only splits on whitespace
				// Still need to implement special cases
				String[] tokens = line.split("\\");
				
				for (int i = 1; i < tokens.length; i++){
					// Get token type
					String type = tokenType(tokens[i]);
					
					// Validate and output token
					switch (type) {
					case "reserved":
						break;
					case "identifier":
						break;
					case "integer":
						break;
					case "special":
						break;
					default:
						System.out.println("Invalid token type");
					}
				}
			}
			
			// Close file & read stream
			file.close();
			br.close();
		}
		catch (IOException e) {
			System.out.println("Error opening file: " + e);
		}
		
	}

}
