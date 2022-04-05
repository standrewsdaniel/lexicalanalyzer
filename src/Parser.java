//@author: Daniel St Andrews
//@purpose: parse input passed from a lexical object and print the trace of the tree to the screen.
//Created on 3/1/2022
//version 1.0

public class Parser {

    //DC
    public Parser()
    {
        Lexical lexi = new Lexical("ErrorNoLexPassed");
    }

    //NDC
    public Parser(Lexical lexi)
    {
        this.lexi = lexi;
        this.hasRun = false;
    }

    //Lexical object that gets overwritten at constructor.
    Lexical lexi = new Lexical();
    //Variable for init
    boolean hasRun;

    public void expr()
    {
        System.out.println("Enter <expr>");

        term();

        while(lexi.nextToken == 21 || lexi.nextToken == 22)
        {
            lexi.lex();
            term();
        }
        System.out.println("Exit <expr>");
    } // End of expr()

    public void term()
    {
        System.out.println("Enter <term>");
        factor();
        while(lexi.nextToken == 23 || lexi.nextToken == 24)
        {
            lexi.lex();
            factor();
        }

        System.out.println("Exit <term>");
    } // End of term()

    public void factor()
    {
        System.out.println("Enter <factor>");

        if(lexi.nextToken == 11 || lexi.nextToken == 10)
        {
            lexi.lex();
        }
        else
        {
            if(lexi.nextToken == 25)
            {
                lexi.lex();
                expr();
                if(lexi.nextToken == 26)
                {
                    lexi.lex();
                }
                else
                {
                    System.out.println("Error.");
                }
            }
            else
            {
                System.out.println("Error.");
            }
        }
        System.out.println("Exit <factor>");
    }

    //Starts parsing the passed lexical obj
    public void parse()
    {
        //Clears null run
        if(this.hasRun == false)
        {
            lexi.lex();
            hasRun = true;
            parse();
        }
        else
        {
            lexi.lex();
            expr();
        }
    }
}
