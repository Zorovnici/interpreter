package nonterminals;

import tokenizer.Tokenizer;

public class Exp {
    private Fac fac;
    private Exp exp;
    private String type;
    
    public void parseExp(Tokenizer tokens){
        // Parse <fac>
        fac = new Fac();
        fac.parseFac(tokens);
        
        if (tokens.getToken() == 22){ // The token is +
            // Advance to next token
            tokens.nextToken();
            
            // Parse <exp>
            exp = new Exp();
            exp.parseExp(tokens);
            
            // Set type to plus
            type = "plus";
        }
        if (tokens.getToken() == 23){ // The token is -
            // Advance to next token
            tokens.nextToken();
            
            // Parse <exp>
            exp = new Exp();
            exp.parseExp(tokens);
            
            // Set type to minus
            type = "minus";
        }   
    }
    
    public void printExp(){
        // Print <fac>
        fac.printFac();
        
        // Print + <exp>
        if (type.equals("plus")){
            // Print +
            System.out.print(" + ");
            
            // Print <exp>
            exp.printExp();
        }
        
        // Print - <exp>
        if (type.equals("minus")){
            // Print +
            System.out.print(" - ");
            
            // Print <exp>
            exp.printExp();
        }
    }
    
    public void execExp(){
        
    }
}
