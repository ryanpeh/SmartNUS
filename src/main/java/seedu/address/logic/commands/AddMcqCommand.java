package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import java.util.Arrays;

import seedu.address.model.Model;

/**
 * Adds an MCQ question
 */
public class AddMcqCommand extends Command {

    public static final String COMMAND_WORD = "/mcq";
    public static final String MESSAGE_SUCCESS = "New question added: question: %s options: %s answer: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a question to SmartNUS. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_ANSWER + "ANSWER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What is 1+1? "
            + PREFIX_OPTION + "3 "
            + PREFIX_OPTION + "4 "
            + PREFIX_OPTION + "5 "
            + PREFIX_ANSWER + "2 ";

    // Temp attributes before implementation of Question Object
    private final String question;
    private final String[] options;
    private final String answer;

    /**
     * Temporary constructor
     */
    public AddMcqCommand(String question, String[] options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, question, Arrays.toString(options), answer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMcqCommand // instanceof handles nulls
                && question.equals(((AddMcqCommand) other).question) // check if the attributes are the same
                && Arrays.equals(options, ((AddMcqCommand) other).options)
                && answer.equals(((AddMcqCommand) other).answer));
    }
}
