//@author Daniel St Andrews
//@purpose: Lexical analyzer adapted from C lexical analyzer from assignment 2.
//@run reads in a given string and parses into integer tokens to be used in syntax parser.
//Created on 3/1/2022
//version 1.0

import java.lang.reflect.Array;
import java.util.*;

public class Lexical {
    //CHAR TOKEN CODES
    private static final int LETTER = 2;
    private static final int DIGIT = 1;
    private static final int NULL_VAL = 0;
    private static final int UNKNOWN = 99;
    private static final int EOF = -1;

    // TOKEN CODES
    private static final int INT_LIT = 10;
    private static final int IDENT = 11;
    private static final int ASSIGN_OP = 20;
    private static final int ADD_OP = 21;
    private static final int SUB_OP = 22;
    private static final int MULT_OP = 23;
    private static final int DIV_OP = 24;
    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;

    // EXTRA TOKEN CODES
    private static final int FOR_CODE = 30;
    private static final int IF_CODE = 31;
    private static final int ELSE_CODE = 32;
    private static final int WHILE_CODE = 33;
    private static final int DO_CODE = 34;
    private static final int INT_CODE = 35;
    private static final int FLOAT_CODE = 36;
    private static final int SWITCH_CODE = 37;

    //STRING AND CHAR GLOBARL VARS
    private char[] lexeme;
    private char nextChar;

    //INT VARS
    private int lexLen;
    public int nextToken;
    private int charClass;
    private int i = 0;

    //String to be analyzed
    private String input;

    //DC
    public Lexical()
    {
        this.input = "";
    }

    //NDC
    public Lexical(String input)
    {
        this.input = input;
        lexeme = new char[100];

    }

    //Gets a char from the array read from input and categorizes it to be looked up
    public void getChar()
    {
        //Temp array for sifting string into chars
        char arr[] = this.input.toCharArray();
        if(i < this.input.length())
        {
            if ((int)(nextChar = Array.getChar(arr, i)) != 0){
                i++;
                if (Character.isLetter(nextChar)) {
                    charClass = LETTER;
                }
                else if (Character.isDigit(nextChar)) {
                    charClass = DIGIT;
                }
                else {
                    charClass = UNKNOWN;
                }
            } else
                charClass = EOF;
        }
        else
        {
            charClass = EOF;
        }
    }//~~~~End of getChar~~~~

    //Adds char to lexeme for current token, in order to be printed
    public void addChar()
    {
        if(lexLen <= 98)
        {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        }
        else
        {
            System.out.println("Error, Lexeme is too long!");
        }
    }//~~~~End of addChar~~~~

    //Assign char a token code
    //@param char to be categorized
    public int lookUp(char ch)
    {
        //Handles assigning nextToken
        switch(ch){
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;
    }//~~~~End of lookUp~~~~


    //@return nextToken, the token currently being printed.
    public int lex()
    {
        lexLen = 0;
        getNonBlank();
        switch (charClass)
        {
            /* Parse identifiers */
            case LETTER:
                addChar();
                getChar();
                while(charClass == LETTER || charClass == DIGIT){
                    addChar ();
                    getChar (); }
                if(String.valueOf(lexeme, 0, lexLen ) == "if")
                {
                    nextToken = IF_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "for")
                {
                    nextToken = FOR_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "else")
                {
                    nextToken = ELSE_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "while")
                {
                    nextToken = WHILE_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "do")
                {
                    nextToken = DO_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "int")
                {
                    nextToken = INT_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "float")
                {
                    nextToken = FLOAT_CODE;
                    break;
                }
                else if(String.valueOf(lexeme, 0, lexLen ) == "switch")
                {
                    nextToken = SWITCH_CODE;
                    break;
                }
                nextToken = IDENT;
                break;
            /* Parse integer literals */
            case DIGIT:
                addChar();
                getChar();
                while(charClass == DIGIT){
                    addChar();
                    getChar(); }
                nextToken = INT_LIT;
                break;
            /* Parentheses and operators */
            case UNKNOWN:
                lookUp(nextChar);
                getChar();
                break;
            /* EOF */
            case EOF:
                nextToken = EOF;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexLen = 3;
                break;
            case NULL_VAL:
                getChar();
                return nextToken;
        }

        int offset = 0;
        System.out.println("Next token is:" + nextToken + " Next lexeme is: " + String.valueOf(lexeme, 0, lexLen));
        return nextToken;
    }//~~~~End of Lex~~~~

    //Advances char reader by one when space encountered
    public void getNonBlank(){
        while (nextChar == ' ')
            getChar();
    }//~~~~End of getNonBlank~~~~

}
