package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Cond {
    private Comp comp;
    private Cond cond1, cond2;
    private String type;
    
    public void parseCond(Tokenizer tokens){
        if (tokens.getToken() == 20){ // The token is (            
            // Parse <comp>
            comp = new Comp();
            comp.parseComp(tokens);
            
            // Set condition type to comp
            type = "comp";
        }
        else if (tokens.getToken() == 15){ // The next token is !
            // Consume token
            tokens.nextToken();
            
            // Parse <cond>
            cond1 = new Cond();
            cond1.parseCond(tokens);
            
            // Set condition type to not
            type = "not";
        }
        else if (tokens.getToken() == 16){ // The next token is [
            // Consume token
            tokens.nextToken();
            
            // Parse <cond>
            cond1 = new Cond();
            cond1.parseCond(tokens);
            
            if (tokens.getToken() == 18){ // The next token is &&
                // Advance to next token
                tokens.nextToken();
                
                // Parse <cond>
                cond2 = new Cond();
                cond2.parseCond(tokens);
                
                // Set condition type to and
                type = "and";
            }          
            else if (tokens.getToken() == 19){ // The next token is ||
                // Consume token
                tokens.nextToken();
                
                // Parse <cond>
                cond2 = new Cond();
                cond2.parseCond(tokens);
                 
                // Set condition type to or
                type = "or";
            }
            
            // Verify that next token is ']'
            if (tokens.getToken() != 17){
                System.out.println("Error: expected ], token: " + tokens.getToken());
                System.exit(0);
            }
            
            // Advance to next token
            tokens.nextToken();
        }
        else {
            System.out.println("Error: expected <comp>, !<cond> , [<cond> && <cond>], "
                    + "or [<cond> or <cond>]");
        }
    }
    
    public void printCond(){
        switch(type){
            case "comp":  
                // Print <comp>
                comp.printComp();
                break;
            case "not":  
                // Print !
                System.out.print("!");
                
                // Print <cond>              
                cond1.printCond();
                
                break;
            case "and":  
                // Print [
                System.out.print("[");
                
                // Print <cond>              
                cond1.printCond();
                
                // Print &&
                System.out.print(" && ");
                
                // Print <cond>              
                cond2.printCond();
                
                // Print [
                System.out.print("]");
                
                break;
            case "or":  
                // Print [
                System.out.print("[");
                
                // Print <cond>              
                cond1.printCond();
                
                // Print &&
                System.out.print(" || ");
                
                // Print <cond>              
                cond2.printCond();
                
                // Print [
                System.out.print("]");
                
                break;
            default:
                System.out.println("Error: invalid condition type");
                break;
        }
    }
    
    public boolean execCond(Scanner inputFile){
        boolean result = false;
        switch(type){
        case "comp":  
            result = comp.execComp(inputFile);
            break;
        case "not":  
            result = !cond1.execCond(inputFile);
            break;
        case "and":  
            result = cond1.execCond(inputFile) && cond2.execCond(inputFile);
            break;
        case "or":  
            result = cond1.execCond(inputFile) || cond2.execCond(inputFile);
            break;
        default:
            System.out.println("Error: invalid condition type");
            break;
        }
        return result;       
    }
}
