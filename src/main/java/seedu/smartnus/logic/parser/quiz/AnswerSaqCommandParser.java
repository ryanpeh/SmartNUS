package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_SAQ_ANSWER_FORMAT;

import seedu.smartnus.logic.commands.quiz.AnswerSaqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.quiz.QuizManager;

public class AnswerSaqCommandParser implements QuizParser<AnswerSaqCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AnswerSaqCommand
     * and returns a AnswerSaqCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerSaqCommand parse(String args, QuizManager quizManager) throws ParseException {
        if (!args.isBlank()) {
            return new AnswerSaqCommand(args, quizManager);
        } else {
            throw new ParseException(MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
        }
    }
}
