package nonterminals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        
        if(idList != null){
            idList.execIdListInput(inputFile);
        }
    }
    
    public void execIdListOutput(Scanner inputFile){
        try {
            PrintWriter writer = new PrintWriter("output");
            //writer.print(Id.getValue(id.getIdentifier()));
            System.out.println();
            System.out.println("Output: " + Id.getValue(id.getIdentifier()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
