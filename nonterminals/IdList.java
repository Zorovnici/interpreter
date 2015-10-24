package nonterminals;

import tokenizer.Tokenizer;

public class IdList {
    private Id id;
    private IdList idList;

    public void parseIdList(Tokenizer tokens, boolean declaration){
        // Parse <id> but do not create a new id object in case
        // the identifier already exists
        id = Id.parseId(tokens, declaration);
        
        // If next token is a comma then parse <id list>
        if (tokens.getToken() == 13){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <id list>
            idList = new IdList();
            idList.parseIdList(tokens, declaration);
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
    }
    
    public void execIdList(){
        
    }
}
