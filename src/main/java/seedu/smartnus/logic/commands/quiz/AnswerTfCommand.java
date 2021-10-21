package seedu.smartnus.logic.commands.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.quiz.AnswerMcqCommand.CONTINUE_QUIZ_MESSAGE;
import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;

import java.util.ArrayList;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;

public class AnswerTfCommand extends Command {

    private final String input;
    private final Question currentQuestion;
    private final QuizManager quizManager;

    /**
     * Creates
     */
    public AnswerTfCommand(String input, QuizManager quizManager) {
        this.input = input;
        this.currentQuestion = quizManager.currQuestion();
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        // TODO: Some additional logic here for the UI
        requireNonNull(model);

        ArrayList<Choice> choices = currentQuestion.getOrderedChoices();
        Choice choice = null;
        switch (input) {
        case "T":
        case "t":
            choice = currentQuestion.findChoiceByTitle(TRUE_CHOICE_TITLE);
            break;
        case "F":
        case"f":
            choice = currentQuestion.findChoiceByTitle(FALSE_CHOICE_TITLE);
            break;
        default:
            break;
        }

        assert choice != null : "Choice should not be null";

        if (quizManager.attemptAndCheckAnswer(choice)) {
            return new CommandResult("Correct!\n" + CONTINUE_QUIZ_MESSAGE);
        } else {
            return new CommandResult("Incorrect. The correct answer is: "
                    + currentQuestion.getCorrectChoice().getTitle() + "\n" + CONTINUE_QUIZ_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof AnswerTfCommand; // instanceof handles nulls
    }
}
