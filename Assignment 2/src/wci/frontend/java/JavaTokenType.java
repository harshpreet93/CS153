package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;

/**
 * <h1>PascalTokenType</h1>
 *
 * <p>Pascal token types.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public enum JavaTokenType implements TokenType
{
    // Reserved words.
    ABSTRACT, BREAK, CASE, CHAR, CLASS, CONST, CONTINUE, DO, DOUBLE, ELSE,
    ENUM, EXTENDS, FLOAT, FOR, GOTO, IF, INT, LONG, NATIVE, RETURN,
    SHORT, PACKAGE, PROTECTED, STATIC, SWITCH, SUPER, THIS, THROW,
    VOID, VOLATILE, WHILE,

    // Special symbols.
    
    TILDA("~"), EXCLAIMATION("!"), AT("@"), PERCNTAGE("%"), AMPERSAND("&"),
    HAT("^"), STAR("*"), DASH("-"), PLUS("+"), EQUALS("="), BAR("|"),
    BACKWARD_SLASH("/"), COLOR(":"), SEMICOLON(";"), QUESTION_MARK("?"),
    LEFT_ARROW("<"), RIGHT_ARROW(">"), PERIOD("."), COMMA(","),
    SINGLE_QUOTATION("'"), DOUBLE_QUOTATION("\""), LEFT_PAREN("("),
    RIGHT_PAREN(")"), LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"),
    RIGHT_BRACE("}"), PLUS_PLUS("++"), MINUS_MINUS("--"), SHIFT_LEFT("<<"),
    SHIFT_RIGHT(">>"), LESS_THAN_OR_EQUAL("<="), GREATER_THAN_OR_EQUAL(">="),
    PLUS_EQUALS("+="), MINUS_EQUALS("-="), TIMES_EQUALS("*="), DIVIDE_EQUALS("/="),
    IS_EQUAL("=="), OR_EQUAL("|="), MOD_EQUAL("&="), BITWISE_EQUAL("^="), NOT_EQUAL("!="),
    SHIFT_LEFT_EQUALS("<<="), SHIFT_RIGHT_EQUALS(">>="), OR("||"), AND("&&"),
    SINGLE_LINE_COMMENT("//"), BEGIN_COMMENT("/*"), END_COMMENT("*/"),

    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

//    private static final int FIRST_RESERVED_INDEX = AND.ordinal();
//    private static final int LAST_RESERVED_INDEX  = WITH.ordinal();
//
//    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
//    private static final int LAST_SPECIAL_INDEX  = DOT_DOT.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Pascal reserved word text strings.
//    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
//    static {
//        JavaTokenType values[] = JavaTokenType.values();
//        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
//            RESERVED_WORDS.add(values[i].getText().toLowerCase());
//        }
//    }
//
//    // Hash table of Pascal special symbols.  Each special symbol's text
//    // is the key to its Pascal token type.
//    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
//        new Hashtable<String, JavaTokenType>();
//    static {
//        JavaTokenType values[] = JavaTokenType.values();
//        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
//            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
//        }
//    }
}
