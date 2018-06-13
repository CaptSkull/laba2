package ru.mirza;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private List<Lexem> lexems = new ArrayList<>();

    public Lexer() {
        lexems.add(new Lexem("VAR", Pattern.compile("[a-zA-Z]+")));
        lexems.add(new Lexem("DIGIT", Pattern.compile("0|[1-9][0-9]*")));
        lexems.add(new Lexem("ASSIGNOP", Pattern.compile("=")));
        lexems.add(new Lexem("OP", Pattern.compile("[*/+-]")));
        lexems.add(new Lexem("WS", Pattern.compile("[ \t\f\r\n]+")));
    }

    private Pattern tPatterns(){
        StringBuffer tokenBuf = new StringBuffer();
        for (Lexem tType : lexems)
            tokenBuf.append(String.format("|(?<%s>%s)", tType.getValue(), tType.getPattern()));

        return Pattern.compile(new String(tokenBuf.substring(1)));
    }

    public List<Token> process(String s) {

        ArrayList<Token> tokens = new ArrayList<Token>();

        Matcher matcher = tPatterns().matcher(s);
        while (matcher.find()) {
            for (Lexem tok: lexems) {
                if (matcher.group("WS") != null)
                    continue;
                else if (matcher.group(tok.getValue()) != null) {
                    tokens.add(new Token(tok, matcher.group(tok.getValue())));
                    break;
                }
            }

        }


        return tokens;
    }
}