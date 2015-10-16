package nonterminals;

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
        idList.parseIdList(tokens);
    }
    
    public void printDecl(){
        // Print int
        System.out.println("int");
        
        // Print <id list>
        idList.printIdList();
    }
    
    public void execDecl(){
        
    }
}
