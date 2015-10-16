package nonterminals;

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
        idList.parseIdList(tokens);
        
        // Verify that next token is ';'
        if (tokens.getToken() != 1){
            System.out.println("Error: expected ;");
            System.exit(0);
        }
    }

    public void printOutput(){
        // Print write
        System.out.println("write ");
        
        // Print <id list>
        idList.printIdList();
        
        // Print ;
        System.out.print(";");
    }
    
    public void execOutput(){
        
    }
}
