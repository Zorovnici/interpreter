package nonterminals;

import java.util.HashSet;
import java.util.Set;

import tokenizer.Tokenizer;

public class Id {
    private String identifier;                              // Identifier name 
    private int val;                                        // Identifier value
    private boolean declared, initialized;                  // Indicates if declared or initialized
    private static Set<Id> identifiers = new HashSet<Id>(); // Stores identifiers
    
    // Id constructor
    private Id(String name){
        identifier = name;
        declared = false;
        initialized = false;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setDeclared(boolean b) {
        this.declared = b;
    }
    
    public boolean getDeclared() {
        return declared;
    }
    
    public void setInitialized(boolean b) {
        this.declared = b;
    }
    
    public boolean getInitialized() {
        return declared;
    }
    
    public Set<Id> getIdentifiers(){
        return identifiers;
    }
    
    
    public static Id parseId(Tokenizer tokens){       
        Id id = null;
        // Iterate over the identifiers to see if the Id object already exists
        boolean exists = false;
        for (Id obj : identifiers) {
            if (obj.getIdentifier().equals(tokens.getTokenIdentifier())){
                // The id has been found
                exists = true;
                
                // Update existing object
                id = obj;
                
                // Exit loop
                break;
            }
        }
             
        // If the identifier doesn't already exist then create a new Id object
        if (!exists){
            // Create new id object <id>
            id = new Id(tokens.getTokenIdentifier());
            
            // Add Id to identifiers set to avoid duplicates
            identifiers.add(id);
            
        }
        
        // Consume token
        tokens.nextToken();
        
        return id;
    }
    
    public void printId(){
        System.out.print(identifier);
    }
    
    public void execId(){
        
    }
}
