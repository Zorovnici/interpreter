package nonterminals;

import java.util.HashSet;
import java.util.Set;

import tokenizer.Tokenizer;

public class Id {
    private String identifier;                              // Identifier name 
    private int value;                                      // Identifier value
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
        this.initialized = b;
    }
    
    public boolean getInitialized() {
        return initialized;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public static int getValue(String id){
        int num = 0;
        for (Id obj : identifiers) {
            if (obj.getIdentifier().equals(id)){
                // Return error if id is not initialized
                if (!obj.initialized){
                    System.out.println("Error: " + obj.identifier + " is not initialized");
                    System.exit(0);
                }
                
                // Get value
                num = obj.value;
                
                // Exit loop
                break;
            }
        }
        return num;
    }
    
    public Set<Id> getIdentifiers(){
        return identifiers;
    }
       
    public static Id parseId(Tokenizer tokens, boolean declaration, boolean assignment){       
        Id id = null;
        // Iterate over the identifiers to see if the Id object already exists
        boolean exists = false;
        for (Id obj : identifiers) {
            if (obj.getIdentifier().equals(tokens.getTokenIdentifier())){
                // The id has been found
                exists = true;
                
                // Check to see if this is a double declaration
                if (declaration){
                    System.out.println("Error: " + obj.identifier + " has already been declared");
                    System.exit(0);
                }
                
                // If this is an assignment then update the initialized status
                if (assignment){
                    obj.setInitialized(true);
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
            
            // If this is an assignment then update the initialized status
            if (assignment){
                id.setInitialized(true);
            }
        }
        
        // Consume token
        tokens.nextToken();
        
        return id;
    }
    
    public void printId(){
        System.out.print(identifier);
    }
    
}
