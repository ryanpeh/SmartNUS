package seedu.smartnus.logic.commands.questions;

import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.model.question.Question;

/**
 * Adds a Short Answer Question to SmartNUS
 */
public class AddSaqCommand extends AddQuestionCommand {

    public static final String COMMAND_WORD = "saq";
    public static final String MESSAGE_NO_ANSWER = "Must have exactly one correct answer";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an short answer question to SmartNUS. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER INCLUDING [" + PREFIX_KEYWORD + "KEYWORD]..." + "]"
            + PREFIX_IMPORTANCE + "IMPORTANCE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "You're a wizard, Harry. Which famous series is this quote from? "
            + PREFIX_ANSWER + " "
            + PREFIX_KEYWORD + "Harry "
            + PREFIX_KEYWORD + "Potter "
            + "and the Philosopher's Stone "
            + PREFIX_IMPORTANCE + "1 ";

    public AddSaqCommand(Question question) {
        super(question);
    }
}
