package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.pascal.PascalErrorCode.*; //This needs to be changed to JavaErrorCode, but JavaErrorCode is not done yet

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

            //The following symbols are groups that contain multi-character symbols
            // !, !=
            // &, &=, &&
            // ^, ^=
            // *, */, *=
            // -, --, -=
            // +, ++, +=
            // =, ==
            // |, |=, ||
            // /, /=, //, /*
            // <, <<=, <<, <=
            // >, >>=, >=, >>

            //The following symbols are single-character
            // :
            // ;
            // ?
            // @
            // %
            // ~
            // .
            // ,
            // '
            // "
            // (
            // )
            // [
            // ]
            // {
            // }

            // Single-character special symbols.
            case ':':  case ';':  case '?':  case '@':  case '%':
            case '~':  case '.': case ',':  case '\'':  case '"':
            case '(':  case ')': case '[':  case ']':
            case '{':  case '}': {
                nextChar();  // consume character
                break;
            }

            // ! or !=
            case '!': {
                currentChar = nextChar();  // consume ':';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // & or &= or &&
            case '&': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '&') {
                    text += currentChar;
                    nextChar();  // consume '>'
                }

                break;
            }

            // ^ or ^=
            case '^': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // * or */ or *=
            case '*': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '/') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                else if(currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }

                break;
            }

            //- or -- or -=
            case '-': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '-') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                else if(currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }

            // + or ++ or +=
            case '+': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '+') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                else if(currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }

            // = or ==
            case '=': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                break;
            }

            // | or |= or ||
            case '|': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                else if(currentChar == '|') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }

            // / or /= or // or /*
            case '/': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                else if(currentChar == '/') {
                    text += currentChar;
                    nextChar();
                }
                else if(currentChar == '*') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }

            // < or <<= or << or <=
            case '<': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '<') {
                    text += currentChar;
                    nextChar();  // consume '.'
                    if(currentChar == '=') {
                        text += currentChar;
                        nextChar();
                    }
                }
                else if(currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }

            // > or >>= or >= or >>
            case '>': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '>') {
                    text += currentChar;
                    nextChar();  // consume '.'
                    if(currentChar == '=') {
                        text += currentChar;
                        nextChar();
                    }
                }
                else if(currentChar == '=') {
                    text += currentChar;
                    nextChar();
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
