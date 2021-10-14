package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.Model;
import seedu.address.model.choice.Choice;
import seedu.address.model.question.Question;
import seedu.address.model.quiz.QuizManager;


/**
 * Starts a quiz
 */
public class AnswerMcqCommand extends Command {

    public static final String COMMAND_WORD = "quiz";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes no arguments";
    public static final String MESSAGE_SUCCESS = "Quiz started!";
    public static final String CONTINUE_QUIZ_MESSAGE = "Enter '" + NextQuestionCommand.COMMAND_WORD
            + "' to proceed with the next question, or '" + ExitCommand.COMMAND_WORD + "' to exit the quiz";

    private final String input;
    private final Question currentQuestion;

    /**
     * Creates
     */
    public AnswerMcqCommand(String input, QuizManager quizManager) {
        this.input = input;
        this.currentQuestion = quizManager.currQuestion();
    }

    @Override
    public CommandResult execute(Model model) {
        // TODO: Some additional logic here for the UI
        requireNonNull(model);

        ArrayList<Choice> choices = currentQuestion.getRandomisedChoices();
        Choice choice = null;
        switch (input) {

        case "a":
        case "A":
            choice = choices.get(0);
            break;

        case "b":
        case "B":
            choice = choices.get(1);
            break;

        case "c":
        case "C":
            choice = choices.get(2);
            break;

        case "d":
        case "D":
            choice = choices.get(3);
            break;

        default:
            break;

        }

        assert choice != null : "Choice should not be null";

        if (choice.getIsCorrect()) {
            return new CommandResult("Correct!\n" + CONTINUE_QUIZ_MESSAGE);
        } else {
            return new CommandResult("Incorrect. The correct answer is: "
                    + currentQuestion.getCorrectChoice().getTitle() + CONTINUE_QUIZ_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof AnswerMcqCommand; // instanceof handles nulls
    }

}
