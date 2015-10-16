package nonterminals;

import tokenizer.Tokenizer;

public class IdList {
    private ID id;
    private IdList idList;

    public void parseIdList(Tokenizer tokens){
        // Parse <id>
        id = new ID();
        id.parseID(tokens);
        
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
        id.printID();
        
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
