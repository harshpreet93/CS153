package wci.frontend.java;

public enum JavaErrorCode {
    IDENTIFIER_REDEFINED("Redefined identifier"),
    IDENTIFIER_UNDEFINED("Undefined identifier"),
    INCOMPATIBLE_ASSIGNMENT("Incompatible assignment"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    INVALID_ASSIGNMENT("Invalid assignment statement"),
    INVALID_CHARACTER("Invalid character"),
    INVALID_CONSTANT("Invalid constant"),
    //INVALID_EXPONENT("Invalid exponent"),
    INVALID_EXPRESSION("Invalid expression"),
    INVALID_FIELD("Invalid field"),
    INVALID_FRACTION("Invalid fraction"),
    INVALID_IDENTIFIER_USAGE("Invalid identifier usage"),
    //INVALID_INDEX_TYPE("Invalid index type"),
    INVALID_NUMBER("Invalid number"),
    INVALID_DOUBLE("Invalid double"),
    INVALID_STATEMENT("Invalid statement"),
    INVALID_TARGET("Invalid assignment target"),
    INVALID_TYPE("Invalid type"),
    MIN_GT_MAX("Min limit greater than max limit"),
    MISSING_LEFTBRACE("Missing {"),
    MISSING_COLON("Missing :"),
    MISSING_COMMA("Missing ,"),
    MISSING_DO("Missing DO"),
    MISSING_DOT_DOT("Missing .."),
    MISSING_RIGHTBRACE("Missing }"),
    MISSING_EQUALS("Missing ="),
    MISSING_FOR_CONTROL("Invalid FOR control variable"),
    MISSING_IDENTIFIER("Missing identifier"),
    MISSING_LEFT_BRACKET("Missing ["),
    MISSING_OF("Missing OF"),
    MISSING_PERIOD("Missing ."),
    MISSING_PROGRAM("Missing PROGRAM"),
    MISSING_RIGHT_BRACKET("Missing ]"),
    MISSING_RIGHT_PAREN("Missing )"),
    MISSING_SEMICOLON("Missing ;"),
    MISSING_VARIABLE("Missing variable"),
    NOT_CONSTANT_IDENTIFIER("Not a constant identifier"),
    NOT_RECORD_VARIABLE("Not a record variable"),
    NOT_TYPE_IDENTIFIER("Not a type identifier"),
    RANGE_INTEGER("Integer literal out of range"),
    RANGE_REAL("Real literal out of range"),
    STACK_OVERFLOW("Stack overflow"),
    TOO_MANY_LEVELS("Nesting level too deep"),
    TOO_MANY_SUBSCRIPTS("Too many subscripts"),
    UNEXPECTED_EOF("Unexpected end of file"),
    UNEXPECTED_TOKEN("Unexpected token"),
    UNIMPLEMENTED("Unimplemented feature"),
    UNRECOGNIZABLE("Unrecognizable input"),
    WRONG_NUMBER_OF_PARMS("Wrong number of actual parameters"),

    // Fatal errors.
    IO_ERROR(-101, "Object I/O error"),
    TOO_MANY_ERRORS(-102, "Too many syntax errors");

    private int status;      // exit status
    private String message;  // error message
    
    /**
     * Constructor.
     * @param message the error message.
     */
    JavaErrorCode(String message)
    {
        this.status = 0;
        this.message = message;
    }

    /**
     * Constructor.
     * @param status the exit status.
     * @param message the error message.
     */
    JavaErrorCode(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    /**
     * Getter.
     * @return the exit status.
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @return the message.
     */
    public String toString()
    {
        return message;
    }
    
    /** This main function is to do some 
     * basic tests with the PascalErrorCode and
     * how it works. 
     * 
     * @param args
     */
    public static void main(String [] args) {
    	System.out.println(JavaErrorCode.IDENTIFIER_REDEFINED.toString());
    	System.out.println(JavaErrorCode.IO_ERROR.toString());
    	JavaErrorCode x = JavaErrorCode.IDENTIFIER_UNDEFINED;
    }
}
