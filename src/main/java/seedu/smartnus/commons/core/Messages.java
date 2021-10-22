package seedu.smartnus.commons.core;

import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.quiz.NextQuestionCommand;
import seedu.smartnus.logic.commands.quiz.PrevQuestionCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX = "The question index provided is invalid";
    public static final String MESSAGE_QUESTIONS_LISTED_OVERVIEW = "%1$d questions listed!";
    public static final String MESSAGE_INVALID_MCQ_ANSWER_FORMAT = "Invalid answer format!"
            + " Acceptable answers are 'a', 'b', 'c', 'd' (Case insensitive)\n";
    public static final String MESSAGE_INVALID_TF_ANSWER_FORMAT = "Invalid answer format!"
            + " Acceptable answers are 't', 'f' (Case insensitive)\n";
    public static final String MESSAGE_END_OF_QUIZ = "You have reached the end of the quiz, enter '"
            + ExitCommand.COMMAND_WORD + "' to exit the quiz, or enter " + PrevQuestionCommand.COMMAND_WORD
            + "to view the previous question.";
    public static final String MESSAGE_CONTINUE_QUIZ = "Enter '" + NextQuestionCommand.COMMAND_WORD
            + "' to proceed with the next question, or '" + ExitCommand.COMMAND_WORD + "' to exit the quiz";

}
