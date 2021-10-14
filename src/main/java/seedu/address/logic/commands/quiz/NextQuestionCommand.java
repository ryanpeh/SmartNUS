package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.Model;
import seedu.address.model.quiz.QuizManager;
import seedu.address.model.quiz.exceptions.QuizOutOfBoundException;

/**
 * Command to go to the next question
 */
public class NextQuestionCommand extends Command {
    public static final String COMMAND_WORD = "next";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quizes the next question, takes no arguments";
    public static final String MESSAGE_SUCCESS = "Got Next Question!";
    public static final String MESSAGE_END_OF_QUIZ = "You have reached the end of the quiz, enter '"
            + ExitCommand.COMMAND_WORD + "' to exit the quiz.";

    private final QuizManager quizManager;

    public NextQuestionCommand(QuizManager quizManager) {
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            quizManager.nextQuestion();
        } catch (QuizOutOfBoundException e) {
            return new CommandResult(MESSAGE_END_OF_QUIZ);
        }

        // TODO: Change the displayed message once UI is done.
        //return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(quizManager.currQuestion().getQuestionAndOptions());
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof NextQuestionCommand; // instanceof handles nulls
    }
}
