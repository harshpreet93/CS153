package wci.frontend.java;

import wci.frontend.EofToken;
import wci.frontend.Scanner;
import wci.frontend.Source;
import wci.frontend.Token;
import wci.frontend.error.UnexpectedEndOfFileException;
import wci.frontend.java.tokens.*;

public class JavaScanner extends Scanner {
    private static final String SINGLE_LINE_COMMENT_START = "//";
    private static final char SINGLE_LINE_COMMENT_END = Source.EOL;
    private static final String MULTI_LINE_COMMENT_START = "/*";
    private static final String MULTI_LINE_COMMENT_END = "*/";

    private static final char START_OF_CHARACTER_TOKEN = '\'';
    private static final char START_OF_STRING_TOKEN = '\"';

    /**
     * Constructor
     *
     * @param source the source to be used with this scanner.
     */
    public JavaScanner(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        skipIgnorableCharacters();

        Token token = null;
        char currentChar = currentChar();
        if (Character.isLetter(currentChar)) {
            token = new JavaWordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new JavaNumberToken(source);
        }
        else if (currentChar == START_OF_CHARACTER_TOKEN) {
            token = new JavaCharacterToken(source);
        }
        else if (currentChar == START_OF_STRING_TOKEN) {
            token = new JavaStringToken(source);
        }
        /*
        In Java, there are some special symbols that contain 2 or even 3 characters, which may make you think
        searching SPECIAL_SYMBOLS for a String containing only 1 character won't be able to identify some
        special symbols. This isn't so only because all Java special symbols containing more than 1
        character have a counterpart special symbol that contains their starting character as their only character.
        Example: ">=" is identified as a special symbol correctly because ">" is also a special symbol.
         */
        else if (JavaTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) {
            token = new JavaSpecialSymbolToken(source);
        }
        else if (currentChar == Source.EOF) {
            token = new EofToken(source);
        }
        // TODO uncomment this when JavaErrorToken and JavaErrorCode are completed.
        else {
          token = new JavaErrorToken(source, JavaErrorCode.INVALID_EXPRESSION, ""+currentChar);

            /*
             We want to be able to continue to lex even after encountering an error, so when an error happens
             we just skip the offending character.
            */
            nextChar();
        }

        return token;
    }

    /**
     * @return a 2 character substring beginning at the current source position.
     * @throws Exception
     */
    private String currentTwoCharString() throws Exception {
        return new String(new char[] {currentChar(), source.peekChar()});
    }

    /**
     * This function skips all characters after the single comment including any
     * new lines with no characters in them 
     * @throws Exception
     */
    private void skipSingleLineComment() throws Exception {
        while (source.peekChar() != SINGLE_LINE_COMMENT_END) {
            nextChar();
        }
    }

    private void skipMultiLineComment() throws Exception {
        while (!currentTwoCharString().equals(MULTI_LINE_COMMENT_END)) {
            if (currentChar() == Source.EOF)
                throw new UnexpectedEndOfFileException();
            else
                nextChar();
        }
    }

    private void skipComments() throws Exception {
        if (currentTwoCharString().equals(SINGLE_LINE_COMMENT_START)){
            skipSingleLineComment();
        }
        else if (currentTwoCharString().equals(MULTI_LINE_COMMENT_START)){
        	skipMultiLineComment();
        }
    }

    private void skipWhiteSpace() throws Exception {
        while (Character.isWhitespace(currentChar()))
            nextChar();
    }
    
    private void skipIgnorableCharacters() throws Exception {
        boolean hasSkippedChars;
        do {
            int startPosition = source.getPosition();
            skipWhiteSpace();
            skipComments();
            int endPosition = source.getPosition();
            hasSkippedChars = startPosition != endPosition;
        } while (hasSkippedChars);
    }
}
