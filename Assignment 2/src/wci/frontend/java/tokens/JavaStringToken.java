package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.pascal.PascalErrorCode.*; //This needs to be changed to import static wci.frontend.java.JavaErrorCode.*

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

            if ((currentChar != '\"') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();  // consume character
            }

            // a backslash followed by a double quote is a double quote character
            //take care of a case such as "\""
            if (currentChar == '\\') {
                while ((currentChar == '\\') && (peekChar() == '\"')) {
                    textBuffer.append("\\\"");
                    valueBuffer.append(currentChar); // append double-quote
                    currentChar = nextChar();        // consume the escape character and the following character
                    currentChar = nextChar();
                }
            }

            //take care of a case such as "\\"
            if(currentChar == '\\') {
                while ((currentChar == '\\') && (peekChar() == '\\')) {
                    textBuffer.append("\\\\");
                    valueBuffer.append(currentChar); // append double-quote
                    currentChar = nextChar();        // consume the escape character and the following character
                    currentChar = nextChar();
                }
            }
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
    }
}
