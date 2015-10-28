package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class IdList {
    private Id id;
    private IdList idList;

    public void parseIdList(Tokenizer tokens, boolean declaration, boolean assignment){
        // Parse <id> but do not create a new id object in case
        // the identifier already exists
        id = Id.parseId(tokens, declaration, assignment);
        
        // If next token is a comma then parse <id list>
        if (tokens.getToken() == 13){
            // Advance to next token
            tokens.nextToken();
            
            // Parse <id list>
            idList = new IdList();
            idList.parseIdList(tokens, declaration, assignment);
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
    
    public void execIdListInput(Scanner inputFile){
        if(!inputFile.hasNext()){
            System.out.println("Error: input file is empty");
            System.exit(0);
        }
        
        // Assign value to id
        id.setValue(inputFile.nextInt());
        
        // Initialize id
        id.setInitialized(true);
        
        if(idList != null){
            idList.execIdListInput(inputFile);
        }
    }
    
    public void execIdListOutput(Scanner inputFile){
        System.out.print(id.getIdentifier() + " = ");
        System.out.println(Id.getValue(id.getIdentifier()));
    }
}
