package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.pascal.PascalTokenType.*;
import static wci.frontend.pascal.PascalErrorCode.*;

/**
 * <h1>PascalSpecialSymbolToken</h1>
 *
 * <p> Pascal special symbol tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaSpecialSymbolToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaSpecialSymbolToken(Source source)
            throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal special symbol token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
            throws Exception
    {
        char currentChar = currentChar();

        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {


//            TILDA("~"), EXCLAIMATION("!"), AT("@"), PERCNTAGE("%"), AMPERSAND("&"),
//                    HAT("^"), STAR("*"), DASH("-"), PLUS("+"), EQUALS("="), BAR("|"),
//                    BACKWARD_SLASH("/"), COLOR(":"), SEMICOLON(";"), QUESTION_MARK("?"),
//                    LEFT_ARROW("<"), RIGHT_ARROW(">"), PERIOD("."), COMMA(","),
//                    SINGLE_QUOTATION("'"), DOUBLE_QUOTATION("\""), LEFT_PAREN("("),
//                    RIGHT_PAREN(")"), LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"),
//                    RIGHT_BRACE("}"), PLUS_PLUS("++"), MINUS_MINUS("--"), SHIFT_LEFT("<<"),
//                    SHIFT_RIGHT(">>"), LESS_THAN_OR_EQUAL("<="), GREATER_THAN_OR_EQUAL(">="),
//                    PLUS_EQUALS("+="), MINUS_EQUALS("-="), TIMES_EQUALS("*="), DIVIDE_EQUALS("/="),
//                    IS_EQUAL("=="), OR_EQUAL("|="), MOD_EQUAL("&="), BITWISE_EQUAL("^="), NOT_EQUAL("!="),
//                    SHIFT_LEFT_EQUALS("<<="), SHIFT_RIGHT_EQUALS(">>="), OR("||"), AND("&&"),
//                    SINGLE_LINE_COMMENT("//"), BEGIN_COMMENT("/*"), END_COMMENT("*/"),


            // Single-character special symbols.
//            case '~':  case '!':  case '@':  case '%':  case '&':
//            case '^':  case '*': case '-':  case '+':  case '=':
//            case '|':  case '/':  case ':':  case ';':  case '?':
//            case '<':  case '>': case {
//                nextChar();  // consume character
//                break;
//            }

            // : or :=
            case ':': {
                currentChar = nextChar();  // consume ':';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // < or <= or <>
            case '<': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '>') {
                    text += currentChar;
                    nextChar();  // consume '>'
                }

                break;
            }

            // > or >=
            case '>': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // . or ..
            case '.': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '.') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }

                break;
            }

            default: {
                nextChar();  // consume bad character
                type = ERROR;
                value = INVALID_CHARACTER;
            }
        }

        // Set the type if it wasn't an error.
        if (type == null) {
            type = SPECIAL_SYMBOLS.get(text);
        }
    }
}
