package nonterminals;

import java.util.Scanner;

import tokenizer.Tokenizer;

public class Stmt {
    private Assign assign;
    private If ifStmt;
    private Loop loop;
    private Input input;
    private Output output;
    
    public void parseStmt(Tokenizer tokens){
        if (tokens.getToken() == 32){
            // Parse <assign>
            assign = new Assign();
            assign.parseAssign(tokens);
        }
        else if (tokens.getToken() == 5){
            // Parse <if>
            ifStmt = new If();
            ifStmt.parseIf(tokens);
        }
        else if (tokens.getToken() == 8){
            // Parse <loop>
            loop = new Loop();
            loop.parseLoop(tokens);
        }
        else if (tokens.getToken() == 10){
            // Parse <input>
            input = new Input();
            input.parseInput(tokens);
        }
        else if (tokens.getToken() == 11){
            // Parse <output>
            output = new Output();
            output.parseOutput(tokens);
        }
        else {
            System.out.println("Error: expected <stmt>");
            System.exit(0);
        }
    }
    
    public void printStmt(int tab){
        for(int i = 0; i < tab; i++){
            System.out.print("   ");
        }
        
        if (assign != null){
            // Print <assign>
            assign.printAssign(tab);
        }
        else if (ifStmt != null){
            // Print <if>
            ifStmt.printIf(tab);
        }
        else if (loop != null){
            // Print <loop>
            loop.printLoop(tab);
        }
        else if (input != null){
            // Print <input>
            input.printInput(tab);
        }
        else if (output != null){
            // Print <output>
            output.printOutput(tab);
        }
    }
    
    public void execStmt(Scanner inputFile){
        if (assign != null){
            assign.execAssign(inputFile);
        }
        else if (ifStmt != null){
            ifStmt.execIf(inputFile);
        }
        else if (loop != null){
            loop.execLoop(inputFile);
        }
        else if (input != null){
            input.execInput(inputFile);
        }
        else if (output != null){
            output.execOutput(inputFile);
        }
    }
}
