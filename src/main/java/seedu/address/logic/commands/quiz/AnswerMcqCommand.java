package seedu.address.logic.commands.quiz;


import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Starts a quiz
 */
public class AnswerMcqCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes no arguments";

    public static final String MESSAGE_SUCCESS = "Quiz started!";

    public AnswerMcqCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: Update state (model) with Quiz object?
        // TODO: Find some other way of doing this? Making the constructor so long isn't that good as well
        //       Maybe explore overloading or something I'm not sure
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof QuizCommand; // instanceof handles nulls
    }

}
