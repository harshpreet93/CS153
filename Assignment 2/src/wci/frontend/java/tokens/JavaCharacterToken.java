package wci.frontend.java.tokens;

import wci.frontend.Source;
import wci.frontend.error.UnexpectedEndOfFileException;
import wci.frontend.java.JavaToken;
import wci.frontend.java.JavaTokenType;

public class JavaCharacterToken extends JavaToken {
    private static final char CHARACTER_END = '\'';

    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCharacterToken(Source source) throws Exception {
        super(source);
    }

    // TODO we need this to be able to identify escape characters (READ THE ASSIGNMENT INSTRUCTIONS FOR MORE INFO)
    @Override
    protected void extract() throws Exception {
        nextChar(); // Consume starting quote.

        String valueString = "";
        char currentChar = nextChar();
        while (currentChar != CHARACTER_END) {
            if (currentChar == Source.EOF)
                throw new UnexpectedEndOfFileException();
            else
                valueString += Character.toString(currentChar);
        }
        type = JavaTokenType.CHARACTER;
        value = valueString;
        nextChar(); // Consume ending quote.
    }
}
