package wci.frontend.java.tokens;

import wci.frontend.Source;
import wci.frontend.error.UnexpectedEndOfFileException;
import wci.frontend.java.JavaToken;
import wci.frontend.java.JavaTokenType;

import static wci.frontend.java.JavaErrorCode.UNEXPECTED_EOF;
import static wci.frontend.java.JavaTokenType.ERROR;

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
//        char consumed = nextChar(); // Consume starting quote.
//        System.out.println("consumed: "+consumed);
        String valueString = "";
        String textString = "\'";
//        char currentChar = nextChar();
//        while (currentChar != CHARACTER_END) {
//            if (currentChar == Source.EOF)
//                throw new UnexpectedEndOfFileException();
//            else
//                valueString += Character.toString(currentChar);
//            currentChar = nextChar();
//        }
//        type = JavaTokenType.CHARACTER;
//        value = valueString;
//        nextChar(); // Consume ending quote.

        char currentChar = nextChar();
        if(currentChar == '\\'){
//            valueString += currentChar;
//            valueString += nextChar();
            switch (nextChar()){
                case 't': valueString += '\t';
                    textString += "\\t\'";
                    break;
                case 'n': valueString += '\n';
                    textString += "\\n\'";
                    break;
                case 'r': valueString += '\r';
                    textString += "\\r\'";
                    break;
                case 'f': valueString += '\f';
                    textString += "\\f\'";
                    break;
                case '\'': valueString += '\'';
                    textString += "\\\'\'";
                    break;
                case '\"': valueString += '\"';
                    textString += "\\\"\'";
                    break;
                case '\\': valueString += '\\';
                    textString += "\\\\\'";
                    break;
                case 'b': valueString += '\b';
                    textString += "\\b\'";
                    break;
                default: //TODO: add error handling for unknown characters
                    break;

            }
        }
        else {
            textString = "\'"+currentChar+ "\'";
            valueString += currentChar;
        }
//        System.out.println("value: "+valueString);
        text = textString;
        currentChar = nextChar();
        if(currentChar != '\''){
            type = ERROR;
            value = UNEXPECTED_EOF;
        }
        else {
            nextChar();
        }
        type = JavaTokenType.CHARACTER;
        value = valueString;
    }
}
