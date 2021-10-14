package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.quiz.QuizManager;

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Acceptable answers are 'a', 'b', 'c', 'd'"));
        }
    }

}
