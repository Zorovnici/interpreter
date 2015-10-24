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
    
    
    public static Id parseId(Tokenizer tokens, boolean declaration){       
        Id id = null;
        // Iterate over the identifiers to see if the Id object already exists
        boolean exists = false;
        for (Id obj : identifiers) {
            if (obj.getIdentifier().equals(tokens.getTokenIdentifier())){
                // The id has been found
                exists = true;
                
                // Check to see if this is a double declaration
                if (declaration){
                    System.out.println("Error: identifier has already been declared");
                    System.exit(0);
                }
                
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
  
            // If this is a declaration update the declaration status
            if (declaration){
                id.setDeclared(true);
            }
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
