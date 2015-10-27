package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Output {
    private IdList idList;
    
    public void parseOutput(Tokenizer tokens){
        // Verify that token is 'write'
        if (tokens.getToken() != 11){
            System.out.println("Error: expected write");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <id list>
        idList = new IdList();
        idList.parseIdList(tokens, false, false);
        
        // Verify that next token is ';'
        if (tokens.getToken() != 12){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
        
    }

    public void printOutput(int tab){
        // Print write
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("write ");
        
        // Print <id list>
        idList.printIdList();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execOutput(Scanner inputFile){
        idList.execIdListOutput(inputFile);
    }
}
