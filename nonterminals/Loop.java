package nonterminals;

import java.util.Scanner;

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
        
        // Verify that next token is 'loop'
        if (tokens.getToken() != 9){
            System.out.println("Error: expected loop");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <stmt seq>
        stmtSeq = new StmtSeq();
        stmtSeq.parseStmtSeq(tokens);
        
        // Verify that next token is end
        if (tokens.getToken() != 3){
            System.out.println("Error: expected end");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Verify that next token is ';'
        if (tokens.getToken() != 12){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
    }
    
    public void printLoop(int tab){
        // Print while
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("while ");
        
        // Print <cond>
        cond.printCond();
        
        // Print loop
        System.out.print(" loop");
        
        // Print <stmt seq>
        stmtSeq.printStmtSeq(tab + 1);
        
        // Print end;
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("end;");
    }
    
    public void execLoop(Scanner inputFile){
        while(cond.execCond(inputFile)){
            stmtSeq.execStmtSeq(inputFile);
        }
    }
}
