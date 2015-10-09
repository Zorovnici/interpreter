package tokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    
    /*
     * Private members --------------------------------------------------------
     */
    
    private Queue<Integer> tokens;   // Stores tokens
    private Set<String> identifiers; // Stores identifiers
    private Set<String> declared;    // Keeps track of declarations
    private boolean isDeclared;      // Indicates if declaration is complete

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
            value = 30;
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
    
    /*
     * Public members --------------------------------------------------------
     */
    
    /**
     * Constructor
     */
    public Tokenizer(String inFile){
        // Queue to store tokens
        tokens = new LinkedList<Integer>();
        
        try {
            // Open file & read stream
            FileReader file = new FileReader(inFile);
            BufferedReader br = new BufferedReader(file);

            // Read each line and split into tokens
            String line;
            while((line = br.readLine()) != null){

                // Pattern matching to split the line into tokens
                Pattern pattern = Pattern.compile("[\\w']+|(&&)|(\\|\\|)|(!=)|(==)|(<=)|(>=)|[\\S]");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    // Get token
                    String token = matcher.group(0);

                    // Get token type
                    String type = tokenType(token);

                    // Validate and output token
                    switch (type) {
                    case "reserved":
                        tokens.add(getReserved(token));
                        System.out.println(getReserved(token));
                        break;
                    case "identifier":
                        tokens.add(getIdentifier(token));
                        System.out.println(getIdentifier(token));
                        break;
                    case "integer":
                        tokens.add(getInteger(token));
                        System.out.println(getInteger(token));
                        break;
                    case "symbol":
                        tokens.add(getSymbol(token));
                        System.out.println(getSymbol(token));
                        break;
                    default:
                        System.out.println("Invalid token type");
                        System.exit(0);
                    }
                }

            }
            
            // Add end of file token
            tokens.add(33);
            System.out.println(33);

            // Close file & read stream
            file.close();
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error opening file. " + e);
        }
    }
    
    /**
     * Returns the current token
     */
    public int getToken(){
        if(tokens.size()==0){
            System.out.print("Error: there are no tokens");
            System.exit(0);
        }
        return tokens.peek();
    }
    
    /**
     * Advances to next token
     */
    public void nextToken(){
        if(tokens.size()==0){
            System.out.print("Error: there are no tokens");
            System.exit(0);
        }
        tokens.remove();
    }

}
