package nonterminals;

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
        idList.parseIdList(tokens);
        
        // Verify that next token is ';'
        if (tokens.getToken() != 1){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
    }

    public void printInput(){
        // Print read
        System.out.println("read ");
        
        // Print <id list>
        idList.printIdList();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execInput(){
        
    }
}
