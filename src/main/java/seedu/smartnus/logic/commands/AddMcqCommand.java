package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.AddCommand.MESSAGE_DUPLICATE_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;

/**
 * Adds an MCQ question
 */
public class AddMcqCommand extends Command {

    public static final String COMMAND_WORD = "mcq";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a question to SmartNUS. "
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

    private final Question toAdd;

    /**
     * Temporary constructor
     */
    public AddMcqCommand(Question question) {
        requireNonNull(question);
        this.toAdd = question;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasQuestion(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        model.addQuestion(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMcqCommand // instanceof handles nulls
                && toAdd.equals(((AddMcqCommand) other).toAdd)); // check if the question to add are the same
    }
}
