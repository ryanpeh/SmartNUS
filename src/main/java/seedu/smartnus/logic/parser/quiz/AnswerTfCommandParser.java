package seedu.smartnus.logic.parser.quiz;

import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.commands.quiz.AnswerTfCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.quiz.QuizManager;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_MCQ_ANSWER_FORMAT;

public class AnswerTfCommandParser implements QuizParser<AnswerTfCommand> {

    private static final String TF_REGEX = "^[a-bA-B]$";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerTfCommand parse(String args, QuizManager quizManager) throws ParseException {
        if (args.matches(TF_REGEX)) {
            return new AnswerTfCommand(args, quizManager);
        } else {
            throw new ParseException(MESSAGE_INVALID_MCQ_ANSWER_FORMAT
                    + quizManager.currQuestion().getQuestionAndOptions());
        }
    }
}
