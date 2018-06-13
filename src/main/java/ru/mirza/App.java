package ru.mirza;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Lexer test = new Lexer();
        List<Token> tokens = test.process("x +2 = x + 28 /26");
        for (Token token : tokens)
            System.out.println(token);
    }
}
