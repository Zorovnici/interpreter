package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Decl {
    private IdList idList;

    public void parseDecl(Tokenizer tokens){
        // Verify that token is int
        if (tokens.getToken() != 4){
            System.out.println("Error: expected int");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
            
        // Parse <id list>
        idList = new IdList();
        idList.parseIdList(tokens, true, false);
        
        // Verify that next token is ;
        if(tokens.getToken() != 12){
            System.out.println("Error: expected ';'");
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
    }
    
    public void printDecl(int tab){
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        
        // Print int
        System.out.print("int ");
        
        // Print <id list>
        idList.printIdList();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execDecl(Scanner in){
        
    }
}
