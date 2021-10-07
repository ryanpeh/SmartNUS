package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_QUESTION = new Prefix("/qns");
    public static final Prefix PREFIX_OPTION = new Prefix("/opt");
    public static final Prefix PREFIX_ANSWER = new Prefix("/ans");

}
