package seedu.smartnus.logic.commands.questions;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.AddCommand.MESSAGE_DUPLICATE_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;

/**
 * Adds an MCQ question
 */
public class AddMcqCommand extends AddQuestionCommand {

    public static final String COMMAND_WORD = "mcq";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an mcq question to SmartNUS. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_ANSWER + "ANSWER "
            + PREFIX_IMPORTANCE + "IMPORTANCE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What is 1+1? "
            + PREFIX_OPTION + "3 "
            + PREFIX_OPTION + "4 "
            + PREFIX_OPTION + "5 "
            + PREFIX_ANSWER + "2 "
            + PREFIX_IMPORTANCE + "1 ";

    public AddMcqCommand(Question question) {
        super(question);
    }
}
