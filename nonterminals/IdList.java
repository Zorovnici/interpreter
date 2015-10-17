package nonterminals;

import tokenizer.Tokenizer;

public class IdList {
    private Id id;
    private IdList idList;

    public void parseIdList(Tokenizer tokens){
        // Parse <id>
        id = new Id();
        id.parseId(tokens);
        
        // If next token is a comma then parse <id list>
        if (tokens.getToken() == 13){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <id list>
            idList = new IdList();
            idList.parseIdList(tokens);
        }
    }
    
    public void printIdList(){
        // Print <id>
        id.printId();
        
        // Print <id list>
        if(idList != null){
            System.out.print(", ");
            idList.printIdList();
        }
        
        // Print ;
        System.out.print(";");
    }
    
    public void execIdList(){
        
    }
}
