import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tokenizer {
	
	/**
     * Determines the type of token and returns the type.
     */
	private static String tokenType(String token) {
		String type = "";
		
		// Get starting character of token
		char first = token.charAt(0);
		
		if (token.equals("EOF")){
			// Token is end of file
			type = "EOF";
		}
		else if (Character.isLowerCase(first)){
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
			type = "symbol";
		}
		return type;
    }
	
	/**
     * Returns the corresponding integer for the reserved token.
     * If the token is not valid then returns an error.
     */
	private static int getReserved(String token) {
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
	
	/**
     * Returns the corresponding integer for the symbol token.
     * If the token is not valid then returns an error.
     */
	private static int getSymbol(String token) {
		int value = 0;
		if (token.equals(";")){
			value = 12;
		}
		else if (token.equals(",")){
			value = 13;
		}
		else if (token.equals("=")){
			value = 14;
		}
		else if (token.equals("!")){
			value = 15;
		}
		else if (token.equals("[")){
			value = 16;
		}
		else if (token.equals("]")){
			value = 17;
		}
		else if (token.equals("&&")){
			value = 18;
		}
		else if (token.equals("||")){
			value = 19;
		}
		else if (token.equals("(")){
			value = 20;
		}
		else if (token.equals(")")){
			value = 21;
		}
		else if (token.equals("+")){
			value = 22;
		}
		else if (token.equals("-")){
			value = 23;
		}
		else if (token.equals("*")){
			value = 24;
		}
		else if (token.equals("!=")){
			value = 25;
		}
		else if (token.equals("==")){
			value = 26;
		}
		else if (token.equals("<")){
			value = 27;
		}
		else if (token.equals(">")){
			value = 28;
		}
		else if (token.equals("<=")){
			value = 29;
		}
		else if (token.equals(">=")){
			value = 11;
		}
		else {
			System.out.println("Error: Invalid special token");
			System.exit(0);
		}
		return value;
    }
	
	/**
     * Returns the corresponding integer for the integer token.
     * If the token is not valid then returns an error.
     */
	private static int getInteger(String token) {
		int value = 31;
		
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
		return value;
	}
	
	/**
     * Returns the corresponding integer for the identifier token.
     * If the token is not valid then returns an error.
     */
	private static int getIdentifier(String token) {
		int value = 32;
		
		// Find out if the token contains an integer
		// If it does get the index of the first occurrence
		boolean containsInt = false;
		int intIndex;
		for (intIndex = 0; intIndex < token.length(); intIndex++){
			if(Character.isDigit(token.charAt(intIndex))){
				containsInt = true;
				break;
			}
		}
		
		// Check that all characters before the first integer
		// are upper case
		boolean allCaps = true;
		for (int i = 0; i < intIndex; i++){
			if(!Character.isUpperCase(token.charAt(i))){
				allCaps = false;
			}
		}
		
		// Check that all characters after the first integer
		// are also integers
		boolean allInts = true;
		if (containsInt){
			for (int i = intIndex; i < token.length(); i++){
				if(!Character.isDigit(token.charAt(i))){
					allInts = false;
				}
			}
		}
		
		// If the token is not in the proper format then return an error
		if (!allCaps || !allInts){
			System.out.println("Error: Invalid identifier token");
			System.exit(0);
		}
		
		return value;
	}

	public static void main(String[] args) {
		// Get file name from command line argument
		//String fileName = args[1];
		
		//try {
			// Open file & read stream
			//FileReader file = new FileReader(fileName);
			//BufferedReader br = new BufferedReader(file);
			
			// Read each line and tokenize
			//String line;
		
			// Expected: 1 4 32 12 2 32 14 31 12 11 32 12 3 33
			String line = "program int X ; begin X = 25 ; write X ; end EOF";
			
			//while((line = br.readLine()) != null){
				// Split tokens from line into an array
				// Currently only splits on whitespace
				// Still need to implement special cases
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					// Get token
					String token = st.nextToken();
					
					// Get token type
					String type = tokenType(token);

					// Validate and output token
					int output;
					switch (type) {
					case "reserved":
						output = getReserved(token);
						System.out.println(output);
						break;
					case "identifier":
						output = getIdentifier(token);
						System.out.println(output);
						break;
					case "integer":
						output = getInteger(token);
						System.out.println(output);
						break;
					case "symbol":
						output = getSymbol(token);
						System.out.println(output);
						break;
					case "EOF":
						output = 33;
						System.out.println(output);
						break;
					default:
						System.out.println("Invalid token type");
					}
				}
				
			//}
			
			// Close file & read stream
			//file.close();
			//br.close();
		//}
		//catch (IOException e) {
		//	System.out.println("Error opening file: " + e);
		//}
		
	}

}
