package nonterminals;

import tokenizer.Tokenizer;

public class Comp {
    private String compare;
    private String type1;
    private String type2;
    private int value1;
    private int value2;
    private String id1;
    private String id2;
    private Exp exp;
    
    public void parseComp(Tokenizer tokens){
        // Verify that the next token is '('
        if (tokens.getToken() != 20){
            System.out.println("Error: expected (, token: " + tokens.getToken());
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
        
        // Get first operator
        if (tokens.getToken() == 31){ // The token is an integer   
            // Store integer value
            value1 = tokens.getTokenInteger();
            
            // Set type to integer
            type1 = "integer";
            
            // Consume token
            tokens.nextToken();
        }
        else if (tokens.getToken() == 32){ // The token is an identifier 
            // Store identifier
            id1 = tokens.getTokenIdentifier();
            
            // Set type to id
            type1 = "id";
            
            // Consume token
            tokens.nextToken();
        }
        else if (tokens.getToken() == 20){ // The token is '('
            // Advance to next token
            tokens.nextToken();
            
            // Parse <exp>
            exp = new Exp();
            exp.parseExp(tokens);
            
            // Verify that next token is ')'
            if (tokens.getToken() != 21){
                System.out.println("Error: expected )");
                System.exit(0);
            }
            
            // Advance to next token
            tokens.nextToken();
            
            // Set type to exp
            type1 = "exp";
        }
        else {
            System.out.println("Error: expected <int>, <id>, or (<exp>)");
            System.exit(0);
        }
        
        // Get compare action
        if (tokens.getToken() == 25){ // The token is !=
            compare = "!=";
        }
        else if (tokens.getToken() == 26){ // The token is ==
            compare = "==";
        }
        else if (tokens.getToken() == 27){ // The token is <
            compare = "<";
        }
        else if (tokens.getToken() == 28){ // The token is >
            compare = ">";
        }
        else if (tokens.getToken() == 29){ // The token is <=
            compare = "<=";
        }
        else if (tokens.getToken() == 30){ // The token is >=
            compare = ">=";
        }
        else {
            System.out.println("Error: expected !=, ==, <, >, <=, or >=");
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
        
        // Get second operator
        if (tokens.getToken() == 31){ // The token is an integer   
            // Store integer value
            value2 = tokens.getTokenInteger();
            
            // Set type to integer
            type2 = "integer";
            
            // Consume token
            tokens.nextToken();
        }
        else if (tokens.getToken() == 32){ // The token is an identifier 
            // Store identifier
            id2 = tokens.getTokenIdentifier();
            
            // Set type to id
            type2 = "id";
            
            // Consume token
            tokens.nextToken();
        }
        else if (tokens.getToken() == 20){ // The token is '('
            // Advance to next token
            tokens.nextToken();
            
            // Parse <exp>
            exp = new Exp();
            exp.parseExp(tokens);
            
            // Verify that next token is ')'
            if (tokens.getToken() != 21){
                System.out.println("Error: expected )");
                System.exit(0);
            }
            
            // Advance to next token
            tokens.nextToken();
            
            // Set type to exp
            type2 = "exp";
        }
        else {
            System.out.println("Error: expected <int>, <id>, or (<exp>)");
            System.exit(0);
        }
        
        // Verify that the next token is ')'
        if (tokens.getToken() != 21){
            System.out.println("Error: expected )");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
    }
    
    public void printComp(){
        // Print (
        System.out.print("(");
        
        // Print <op>
        if (type1.equals("integer")){
            System.out.print(value1);
        }
        else if (type1.equals("id")){
            System.out.print(id1);
        }
        else{
            exp.printExp();
        }
        
        // Print <comp op>
        System.out.print(" " + compare + " ");
        
        // Print <op>
        if (type2.equals("integer")){
            System.out.print(value2);
        }
        else if (type2.equals("id")){
            System.out.print(id2);
        }
        else{
            exp.printExp();
        }
        
        // Print )
        System.out.print(")");
    }
    
    public void execComp(){
        
    }
}
