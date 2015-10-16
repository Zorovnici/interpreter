package nonterminals;

import tokenizer.Tokenizer;

public class Loop {
    private Cond cond;
    private StmtSeq stmtSeq;
    
    public void parseLoop(Tokenizer tokens){
        // Verify that token is 'while'
        if (tokens.getToken() != 8){
            System.out.println("Error: expected while");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <cond>
        cond = new Cond();
        cond.parseCond(tokens);
        
        // Verify that next token is 'while'
        if (tokens.getToken() != 9){
            System.out.println("Error: expected while");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <stmt seq>
        stmtSeq = new StmtSeq();
        stmtSeq.parseStmtSeq(tokens);
        
        // Verify that next token is end
        if (tokens.getToken() == 3){
            System.out.println("Error: expected end");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Verify that next token is ';'
        if (tokens.getToken() == 12){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
    }
    
    public void printLoop(){
        // Print while
        System.out.println("while ");
        
        // Print <cond>
        cond.printCond();
        
        // Print loop
        System.out.println(" loop");
        
        // Print <stmt seq>
        stmtSeq.printStmtSeq();
        
        // Print end;
        System.out.println(" end;");
    }
    
    public void execLoop(){
        
    }
}
