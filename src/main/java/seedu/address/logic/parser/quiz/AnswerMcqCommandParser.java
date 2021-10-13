package seedu.address.logic.parser.quiz;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.quiz.AnswerMcqCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.quiz.QuizManager;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnswerMcqCommandParser implements QuizParser<AnswerMcqCommand> {

    String MCQ_REGEX = "^[a-dA-D]{1}$";

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
