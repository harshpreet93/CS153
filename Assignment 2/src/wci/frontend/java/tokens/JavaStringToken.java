package wci.frontend.java.tokens;

import wci.frontend.Source;
import wci.frontend.java.JavaToken;

import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaErrorCode.INVALID_CHARACTER;
import static wci.frontend.java.JavaErrorCode.UNEXPECTED_EOF;
import static wci.frontend.java.JavaTokenType.ERROR;
import static wci.frontend.java.JavaTokenType.STRING;

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
                    default:
                        text = Character.toString(nextChar());
                        type = ERROR;
                        value = INVALID_CHARACTER;
                        return;
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
        System.out.println(text);
    }
}
