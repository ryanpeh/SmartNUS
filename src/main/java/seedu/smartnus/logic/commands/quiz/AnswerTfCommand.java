package seedu.smartnus.logic.commands.quiz;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.quiz.AnswerMcqCommand.CONTINUE_QUIZ_MESSAGE;

public class AnswerTfCommand extends Command {

    private final String input;
    private final Question currentQuestion;
    private final QuizManager quizManager;

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

            case "a":
            case "A":
                choice = choices.get(0);
                break;

            case "b":
            case "B":
                choice = choices.get(1);
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
                || other instanceof AnswerMcqCommand; // instanceof handles nulls
    }
}
