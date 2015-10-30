package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Fac {
    private Fac fac;
    private Exp exp;
    private String type;
    private int value;
    private String id;
    private boolean alternative;
    
    public void parseFac(Tokenizer tokens){
        
        if (tokens.getToken() == 31){ // The token is an integer   
            // Store integer value
            value = tokens.getTokenInteger();
            
            // Set type to integer
            type = "integer";
            
            // Consume token
            tokens.nextToken();
        }
        else if (tokens.getToken() == 32){ // The token is an identifier 
            // Store identifier
            id = tokens.getTokenIdentifier();
            
            // Set type to id
            type = "id";
            
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
            type = "exp";
        }
        else {
            System.out.println("Error: expected <int>, <id>, or (<exp>)");
            System.exit(0);
        }

        
        // If the next token is * then it is the second alternative
        if (tokens.getToken() == 24){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <fac>
            fac = new Fac();
            fac.parseFac(tokens);
            
            // Set alternative to true
            alternative = true;
        }
    }
    
    public void printFac(){
        switch (type){
            case "integer":
                // Print <int>
                System.out.print(value);
                break;
            case "id":
                // Print <id>
                System.out.print(id);
                break;
            case "exp":
                // Print (<exp>)
                System.out.print("(");
                exp.printExp();
                System.out.print(")");
        }
    }
    
    public int execFac(Scanner inputFile){
        int result = 0;
        switch (type){
        case "integer":
            // Get value of integer
            result = value;
            break;
        case "id":
            // Get value of id
            result = Id.getValue(id);
            break;
        case "exp":
            // Get value of <exp>
            result = exp.execExp(inputFile);
    }
        // If we have the second alternative then <op> * <fac>
        if (alternative){
            result = result * fac.execFac(inputFile);
        }
        return result;
    }
}
