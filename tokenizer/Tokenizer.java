package tokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    
    /*
     * Private members --------------------------------------------------------
     */
    
    private Queue<Token> tokens;            // Stores tokens

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
    private static Token getReserved(String str) {
        int value = 0;
        if (str.equals("program")){
            value = 1;
        }
        else if (str.equals("begin")){
            value = 2;
        }
        else if (str.equals("end")){
            value = 3;
        }
        else if (str.equals("int")){
            value = 4;
        }
        else if (str.equals("if")){
            value = 5;
        }
        else if (str.equals("then")){
            value = 6;
        }
        else if (str.equals("else")){
            value = 7;
        }
        else if (str.equals("while")){
            value = 8;
        }
        else if (str.equals("loop")){
            value = 9;
        }
        else if (str.equals("read")){
            value = 10;
        }
        else if (str.equals("write")){
            value = 11;
        }
        else {
            System.out.println("Error: Invalid reserved token");
            System.exit(0);
        }
        
        // Create and return token
        Token token = new Token();
        token.setId(value);
        return token;
    }

    /**
     * Returns the corresponding integer for the symbol token.
     * If the token is not valid then returns an error.
     */
    private static Token getSymbol(String str) {
        int value = 0;
        if (str.equals(";")){
            value = 12;
        }
        else if (str.equals(",")){
            value = 13;
        }
        else if (str.equals("=")){
            value = 14;
        }
        else if (str.equals("!")){
            value = 15;
        }
        else if (str.equals("[")){
            value = 16;
        }
        else if (str.equals("]")){
            value = 17;
        }
        else if (str.equals("&&")){
            value = 18;
        }
        else if (str.equals("||")){
            value = 19;
        }
        else if (str.equals("(")){
            value = 20;
        }
        else if (str.equals(")")){
            value = 21;
        }
        else if (str.equals("+")){
            value = 22;
        }
        else if (str.equals("-")){
            value = 23;
        }
        else if (str.equals("*")){
            value = 24;
        }
        else if (str.equals("!=")){
            value = 25;
        }
        else if (str.equals("==")){
            value = 26;
        }
        else if (str.equals("<")){
            value = 27;
        }
        else if (str.equals(">")){
            value = 28;
        }
        else if (str.equals("<=")){
            value = 29;
        }
        else if (str.equals(">=")){
            value = 30;
        }
        else {
            System.out.println("Error: Invalid special token");
            System.exit(0);
        }
        
        // Create and return token
        Token token = new Token();
        token.setId(value);
        return token;
    }

    /**
     * Returns the corresponding integer for the integer token.
     * If the token is not valid then returns an error.
     */
    private static Token getInteger(String str) {
        int value = 31;

        // Check if token is a valid integer
        boolean num = true;
        for (int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                num = false;
            }
        }
        if (!num){
            System.out.println("Error: Invalid integer token");
            System.exit(0);
        }
        
        // Create and return token
        Token token = new Token();
        token.setId(value);
        token.setInteger(Integer.parseInt(str));
        return token;
    }

    /**
     * Returns the corresponding integer for the identifier token.
     * If the token is not valid then returns an error.
     */
    private static Token getIdentifier(String str) {
        int value = 32;

        // Find out if the token contains an integer
        // If it does get the index of the first occurrence
        boolean containsInt = false;
        int intIndex;
        for (intIndex = 0; intIndex < str.length(); intIndex++){
            if(Character.isDigit(str.charAt(intIndex))){
                containsInt = true;
                break;
            }
        }

        // Check that all characters before the first integer
        // are upper case
        boolean allCaps = true;
        for (int i = 0; i < intIndex; i++){
            if(!Character.isUpperCase(str.charAt(i))){
                allCaps = false;
            }
        }

        // Check that all characters after the first integer
        // are also integers
        boolean allInts = true;
        if (containsInt){
            for (int i = intIndex; i < str.length(); i++){
                if(!Character.isDigit(str.charAt(i))){
                    allInts = false;
                }
            }
        }

        // If the token is not in the proper format then return an error
        if (!allCaps || !allInts){
            System.out.println("Error: Invalid identifier token");
            System.exit(0);
        }

        // Create and return token
        Token token = new Token();
        token.setId(value);
        token.setIdentifier(str);
        return token;
    }
    
    /*
     * Public members --------------------------------------------------------
     */
    
    /**
     * Constructor
     */
    public Tokenizer(String inFile){
        tokens = new LinkedList<Token>();
        
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
                    String str = matcher.group(0);

                    // Get token type
                    String type = tokenType(str);

                    // Validate and output token
                    switch (type) {
                    case "reserved":
                        tokens.add(getReserved(str));
                        System.out.println(getReserved(str).getId());
                        break;
                    case "identifier":
                        tokens.add(getIdentifier(str));
                        System.out.println(getIdentifier(str).getId());
                        break;
                    case "integer":
                        tokens.add(getInteger(str));
                        System.out.println(getInteger(str).getId());
                        break;
                    case "symbol":
                        tokens.add(getSymbol(str));
                        System.out.println(getSymbol(str).getId());
                        break;
                    default:
                        System.out.println("Invalid token type");
                        System.exit(0);
                    }
                }

            }
            
            // Add end of file token
            Token token = new Token();
            token.setId(33);
            tokens.add(token);
            System.out.println(token.getId());

            // Close file & read stream
            file.close();
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error opening file. " + e);
        }
    }
    
    /**
     * Returns the current token id
     */
    public int getToken(){
        if(tokens.size()==0){
            System.out.print("Error: there are no tokens");
            System.exit(0);
        }
        return tokens.peek().getId();
    }
    
    /**
     * Returns the current token integer value
     */
    public int getTokenInteger(){
        if(tokens.size()==0){
            System.out.print("Error: there are no tokens");
            System.exit(0);
        }
        if (tokens.peek().getId() != 31){
            System.out.print("Error: can't get integer - not an integer token");
            System.exit(0);
        }
        return tokens.peek().getInteger();
    }
    
    /**
     * Returns the current token identifier value
     */
    public String getTokenIdentifier(){
        if(tokens.size()==0){
            System.out.print("Error: there are no tokens");
            System.exit(0);
        }
        if (tokens.peek().getId() != 32){
            System.out.print("Error: can't get identifier - not an identifier token");
            System.exit(0);
        }
        return tokens.peek().getIdentifier();
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
    
    /**
     * Returns true if there are no more tokens, false otherwise 
     */
    public boolean isEmpty(){
        boolean empty = true;
        if(tokens.size()==0){
            empty = false;
        }
        return empty;
    }

}
