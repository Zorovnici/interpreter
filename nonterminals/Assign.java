package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Assign {
    private Id id;
    private Exp exp;
    
    public void parseAssign(Tokenizer tokens){
        // Parse <id> but do not create a new id object in case
        // the identifier already exists
        id = Id.parseId(tokens, false);
        
        // Check that the assigned identifier has been declared
        if(!id.getDeclared()){
            System.out.println("Error: identifier has not been declared");
            System.exit(0);
        }

        // Verify that the next token is =
        if(tokens.getToken() != 14){
            System.out.println("Error: expected '='");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <exp>
        exp = new Exp();
        exp.parseExp(tokens);
        
        // Verify that next token is ;
        if(tokens.getToken() != 12){
            System.out.println("Error: expected ';'");
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
    }
    

    public void printAssign(int tab){
        // Print <id>
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        id.printId();
        
        // Print '='
        System.out.print(" = ");
        
        // Print <exp>
        exp.printExp();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execAssign(Scanner inputFile){
        // Get value of expression
        int value = exp.execExp(inputFile);
        
        // Assign value to id
        id.setValue(value);
    }
}
