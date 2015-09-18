package wci.frontend.java.tokens;

import wci.frontend.Source;
import wci.frontend.java.JavaToken;
import wci.frontend.java.JavaTokenType;

import static wci.frontend.java.JavaErrorCode.INVALID_CHARACTER;
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
            char escapedCharacter = nextChar();
            switch (escapedCharacter){
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
                default:
                    text = Character.toString(escapedCharacter);
                    type = ERROR;
                    value = INVALID_CHARACTER;
                    return;
            }
        }
        else {
            textString = "\'"+currentChar+ "\'";
            valueString += currentChar;
        }
        valueString = "\'"+valueString+"\'";
//        System.out.println("value: "+valueString);
        text = textString;
        currentChar = nextChar();
        if(currentChar != '\''){
            type = ERROR;
            value = INVALID_CHARACTER;
        }
        else {
            nextChar();
            type = JavaTokenType.CHARACTER;
            value = valueString;
        }
    }
}
