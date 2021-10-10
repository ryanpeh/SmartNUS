package seedu.address.logic.commands.quiz;


import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Starts a quiz
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes no arguments";

    public static final String MESSAGE_SUCCESS = "Quiz started!";
    public QuizCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: Update state (model) with Quiz object?
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof QuizCommand; // instanceof handles nulls
    }

}
