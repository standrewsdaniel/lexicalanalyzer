//@author Daniel St Andrews
//@purpose: Driver for testing implementation of lexical analyzer and syntax parser / parse tree builder
//ICSI 311 Spring 2022
//@run Reads in line from an input file and runs it through the analyzer and parser before exiting.

import java.io.*;
import java.util.*;

public class Driver {

    public static void main(String[] args)
    {
        // Just for readability in output, labels which number output is being printed out of 20 test inputs.
        int runNum = 0;

        //Try catch for making sure file is read in
        try
        {
            //File declaration
            File in = new File("data.in");
            Scanner reader = new Scanner(in);

            // Read in file and as long as another line
            while(reader.hasNextLine())
            {
                runNum++;
                System.out.println("\n~~~~~~~~~~~~LEXICAL ANALYZER INPUT " + runNum + "~~~~~~~~~~~~\n");

                String input = reader.nextLine();
                Lexical lexAnalyzer = new Lexical(input);
                while(lexAnalyzer.nextToken != -1)
                {
                    lexAnalyzer.lex();
                }

                System.out.println("\n~~~~~~~~~~~~~~~~PARSE TREE " + runNum + "~~~~~~~~~~~~~~~~\n");

                Lexical parseLex = new Lexical(input);
                Parser newTree = new Parser(parseLex);
                newTree.parse();

                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            }
        }
        catch(Exception e)
        {
            System.out.println("File not found. Exiting...");
        }
    }
}
