package nonterminals;

import tokenizer.Tokenizer;

public class CompOp {
    private String type;

    public void parseCompOp(Tokenizer tokens){
        if (tokens.getToken() == 25){ // The token is !=
            type = "!=";
        }
        else if (tokens.getToken() == 26){ // The token is ==
            type = "==";
        }
        else if (tokens.getToken() == 27){ // The token is <
            type = "<";
        }
        else if (tokens.getToken() == 28){ // The token is >
            type = ">";
        }
        else if (tokens.getToken() == 29){ // The token is <=
            type = "<=";
        }
        else if (tokens.getToken() == 30){ // The token is >=
            type = ">=";
        }
        else {
            System.out.println("Error: expected !=, ==, <, >, <=, or >=");
            System.exit(0);
        }
    }
    
    public void printCompOp(){
        switch (type){
            case "!=":
                System.out.println(" != ");
                break;
            case "==":
                System.out.println(" == ");
                break;
            case "<":
                System.out.println(" < ");
                break;
            case ">":
                System.out.println(" > ");
                break;
            case "<=":
                System.out.println(" <= ");
                break;
            case ">=":
                System.out.println(" >= ");
                break;
        }
    }
    
    public void execCompOp(){
        
    }
}
