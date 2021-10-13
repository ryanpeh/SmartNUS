package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.quiz.QuizManager;


public class PrevQuestionCommand extends Command {
    public static final String COMMAND_WORD = "prev";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quizzes the previous question, takes no arguments";

    public static final String MESSAGE_SUCCESS = "Got Previous Quiz!";
    private final QuizManager quizManager;

    public PrevQuestionCommand(QuizManager quizManager) {
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        quizManager.prevQuestion();
        // TODO: Change the displayed message once UI is done.
        // return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(quizManager.currQuestion().getQuestionAndOptions());
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof PrevQuestionCommand; // instanceof handles nulls
    }
}
