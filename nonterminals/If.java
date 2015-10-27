package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class If {
    private Cond cond;
    private StmtSeq stmtSeq1, stmtSeq2;
    
    public void parseIf(Tokenizer tokens){
        // Check that token is if
        if (tokens.getToken() != 5){
            System.out.println("Error: expected if");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <cond>
        cond = new Cond();
        cond.parseCond(tokens);
        
        // Verify that token is 'then'
        if (tokens.getToken() != 6){
            System.out.println("Error: expected then");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <stmt seq>
        stmtSeq1 = new StmtSeq();
        stmtSeq1.parseStmtSeq(tokens);
        
        // If the next token is else then parse second alternative
        if (tokens.getToken() == 7){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <stmt seq>
            stmtSeq2 = new StmtSeq();
            stmtSeq2.parseStmtSeq(tokens);
        }
        
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
    
    public void printIf(int tab){
        // Print if
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("if ");
        
        // Print <cond>
        cond.printCond();
        
        // Print then
        System.out.print(" then");
        
        // Print <stmt seq>
        stmtSeq1.printStmtSeq(tab + 1);
        
        // Print second alternative
        if (stmtSeq2 != null){           
            // Print else
            System.out.println("");
            for(int i = 0; i < tab; i++){
                System.out.print("   ");
            }
            System.out.print("else");
            
            // Print <stmt seq>
            stmtSeq2.printStmtSeq(tab + 1);
        }
        
        // Print end;
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("end;");
    }
    
    public void execIf(Scanner inputFile){
        if (cond.execCond(inputFile)){
            stmtSeq1.execStmtSeq(inputFile);
        }
        else if (stmtSeq2 != null){
            stmtSeq2.execStmtSeq(inputFile);
        }
    }
}
