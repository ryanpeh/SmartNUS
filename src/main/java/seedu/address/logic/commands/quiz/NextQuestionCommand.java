package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Command to go to the next question
 */
public class NextQuestionCommand extends Command {
    public static final String COMMAND_WORD = "next";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quizes the next question, takes no arguments";

    public static final String MESSAGE_SUCCESS = "Got Next Quiz!";
    public NextQuestionCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: use the QuizManager.nextQuestion() method
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof NextQuestionCommand; // instanceof handles nulls
    }
}
