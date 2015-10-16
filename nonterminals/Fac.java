package nonterminals;

import tokenizer.Tokenizer;

public class Fac {
    private Op op;
    private Fac fac;
    
    public void parseFac(Tokenizer tokens){
        // Parse <op>
        op = new Op();
        op.parseOp(tokens);
        
        // If the next token is * then it is the second alternative
        if (tokens.getToken() == 24){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <fac>
            fac = new Fac();
            fac.parseFac(tokens);
        }
    }
    
    public void printFac(){
        // Print <op>
        op.printOP();
        
        // Print * <fac>
        if (fac != null){
            // Print *
            System.out.print(" * ");
            
            // Print <fac>
            fac.printFac();
        }
    }
    
    public void execFac(){
        
    }
}
