package nonterminals;

import tokenizer.Tokenizer;

public class Comp {
    private Op op1, op2;
    private CompOp compOp;
    
    public void parseComp(Tokenizer tokens){
        // Verify that the next token is '('
        if (tokens.getToken() != 20){
            System.out.println("Error: expected (");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <op>
        op1 = new Op();
        op1.parseOp(tokens);
        
        // Parse <comp op>
        compOp = new CompOp();
        compOp.parseCompOp(tokens);
        
        // Parse <op>
        op2 = new Op();
        op2.parseOp(tokens);
        
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
        System.out.println("(");
        
        // Print <op>
        op1.printOp();
        
        // Print <comp op>
        compOp.printCompOp();
        
        // Print <op>
        op2.printOp();
        
        // Print )
        System.out.println(")");
    }
    
    public void execComp(){
        
    }
}
