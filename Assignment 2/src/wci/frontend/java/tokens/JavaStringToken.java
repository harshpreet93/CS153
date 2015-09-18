package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static wci.frontend.java.JavaErrorCode.*; //This needs to be changed to import static wci.frontend.java.JavaErrorCode.*

/**
 * <h1>PascalStringToken</h1>
 *
 * <p> Pascal string tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source)
            throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
            throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\"');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }

            if (currentChar() != '\\' && (currentChar != '\"') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();  // consume character
            }

            // a backslash followed by a double quote is a double quote character
            //take care of a case such as "\""
            if (currentChar == '\\') {
//                while ((currentChar == '\\') && (peekChar() == '\"')) {
//                    textBuffer.append("\\\"");
//                    valueBuffer.append(peekChar()); // append double-quote
////                    currentChar = nextChar();        // consume the escape character and the following character
//                    nextChar();
////                    currentChar = nextChar();
//                }
                switch (peekChar()){
                    case 't': valueBuffer.append('\t');
                        textBuffer.append("\\t");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case 'n': valueBuffer.append('\n');
                        textBuffer.append("\\n");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case 'r': valueBuffer.append('\r');
                        textBuffer.append("\\r");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case 'f': valueBuffer.append('\f');
                        textBuffer.append("\\f");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case '\'': valueBuffer.append('\'');
                        textBuffer.append( "\\\'" );
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case '\"': valueBuffer.append('\"');
                        textBuffer.append( "\\\"" );
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case '\\': valueBuffer.append('\\');
                        textBuffer.append( "\\\\");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    case 'b': valueBuffer.append('\b');
                        textBuffer.append("\\b");
                        nextChar();
                        currentChar = nextChar();
                        break;
                    default: //TODO: add error handling for unknown characters
                        break;

                }
            }

            //take care of a case such as "\\"
//            if(currentChar == '\\') {
//                while ((currentChar == '\\') && (peekChar() == '\\')) {
//                    textBuffer.append("\\\\");
//                    valueBuffer.append(currentChar); // append double-quote
//                    currentChar = nextChar();        // consume the escape character and the following character
//                    currentChar = nextChar();
//                }
//            }
        } while ((currentChar != '\"') && (currentChar != EOF));

        if (currentChar == '\"') {
            nextChar();  // consume final double quotes
            textBuffer.append('\"');

            type = STRING;
            value = valueBuffer.toString();
        }
        else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        	
        text = textBuffer.toString();
        System.out.println(text);
    }
    
/*  
    public static void main(String args[]) throws Exception {
    	String aPath = "/Users/udaiveer/Desktop/School/compilers/compilerProject/CS153/tests/test3.in";

    	Source aSource = new Source(new BufferedReader(new FileReader(aPath)));
    	aSource.nextChar();
    	JavaStringToken tmp = new JavaStringToken(aSource); 
    	tmp.extract();

    }
*/
}
