package wci.frontend.java;

import wci.frontend.*;
import wci.message.Message;

import static wci.message.MessageType.SYNTAX_ERROR;

/**
 * <h1>JavaErrorHandler</h1>
 *
 * <p>Error handler Java syntax errors.</p>
 *
 */
public class JavaErrorHandler
{
    private static final int MAX_ERRORS = 25;

    private static int errorCount = 0;   // count of syntax errors

    /**
     * Getter.
     * @return the syntax error count.
     */
    public int getErrorCount()
    {
        return errorCount;
    }

    /**
     * Flag an error in the source line.
     * @param token the bad token.
     * @param errorCode the error code.
     * @param parser the parser.
     * @return the flagger string.
     */
    public void flag(Token token, JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners.
        parser.sendMessage(new Message(SYNTAX_ERROR,
                                       new Object[] {token.getLineNumber(),
                                                     token.getPosition(),
                                                     token.getText(),
                                                     errorCode.toString()}));

        if (++errorCount > MAX_ERRORS) {
            abortTranslation(token, JavaErrorCode.TOO_MANY_ERRORS, parser);
        }
    }

    /**
     * Abort the translation.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void abortTranslation(Token token, JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners and then abort.
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(SYNTAX_ERROR,
                new Object[] {token.getLineNumber(),
                              token.getPosition(),
                              token.getText(),
                              fatalText}));
        // exit program is error code is < 0 
        System.exit(errorCode.getStatus());
    }
    
    /**
     * Aborts the translation if no token was given
     * @param errorCode the error code
     * @param parser the parser
     */
    public void abortNoToken(JavaErrorCode errorCode, Parser parser)
    {
    	String fatalText = "FATAL ERROR: " + errorCode.toString();
    	System.exit(errorCode.getStatus());
    }
}
