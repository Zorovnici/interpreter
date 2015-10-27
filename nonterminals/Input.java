package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Input {
    private IdList idList;
    
    public void parseInput(Tokenizer tokens){
        // Verify that token is 'read'
        if (tokens.getToken() != 10){
            System.out.println("Error: expected read");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <id list>
        idList = new IdList();
        idList.parseIdList(tokens, false);
        
        // Verify that next token is ';'
        if (tokens.getToken() != 12){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
        
        // Consume token
        tokens.nextToken();
    }

    public void printInput(int tab){
        // Print read
        System.out.println("");
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        System.out.print("read ");
        
        // Print <id list>
        idList.printIdList();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execInput(Scanner inputFile){
        idList.execIdListInput(inputFile);
    }
}
