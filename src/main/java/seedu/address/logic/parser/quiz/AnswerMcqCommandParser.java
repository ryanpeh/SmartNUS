package seedu.address.logic.parser.quiz;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MCQ_ANSWER_FORMAT;

import seedu.address.logic.commands.quiz.AnswerMcqCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.quiz.QuizManager;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnswerMcqCommandParser implements QuizParser<AnswerMcqCommand> {

    private static final String MCQ_REGEX = "^[a-dA-D]$";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerMcqCommand parse(String args, QuizManager quizManager) throws ParseException {
        if (args.matches(MCQ_REGEX)) {
            return new AnswerMcqCommand(args, quizManager);
        } else {
            throw new ParseException(MESSAGE_INVALID_MCQ_ANSWER_FORMAT
                    + quizManager.currQuestion().getQuestionAndOptions());
        }
    }

}
