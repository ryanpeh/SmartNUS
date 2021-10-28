package seedu.smartnus.logic.parser.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_SAQ_ANSWER_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;

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
        requireNonNull(args);
        String answerNoWhiteSpace = args.trim();
        if (!answerNoWhiteSpace.startsWith(PREFIX_ANSWER.getPrefix())) {
            throw new ParseException(MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
        }
        // saq answers allow more flexibility by allowing ans/ to appear in the actual answer after the first prefix
        String parsedAnswer = answerNoWhiteSpace.substring(4).trim(); //remove first occurrence of "ans/"
        return new AnswerSaqCommand(parsedAnswer, quizManager);
    }
}
