package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Logger;
import java.util.List;
import java.util.function.Predicate;

import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.ArgumentMultimap;
import seedu.smartnus.logic.parser.ArgumentTokenizer;
import seedu.smartnus.logic.parser.Parser;
import seedu.smartnus.logic.parser.ParserUtil;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.TagsContainKeywordsPredicate;

public class QuizCommandParser implements Parser<QuizCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QuizCommand
     * and returns a QuizCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QuizCommand parse(String args) throws ParseException {

        try {
            // If the command is `quiz INDEX`
            Index index = ParserUtil.parseIndex(args);
            return new QuizCommand(isCorrectIndex(index));
        } catch (ParseException pe) {
            Logger logger = LogsCenter.getLogger(getClass());
            logger.info("Argument is not an Index. Pass...");
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NAME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
        }

        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);


        return new QuizCommand(getTagPredicate(tagKeywords));
    }

    private Predicate<Question> getTagPredicate(List<String> tagKeywords) {
        return !tagKeywords.isEmpty()
                ? new TagsContainKeywordsPredicate(tagKeywords)
                : new ShowAllQuestionsPredicate();
    }

    private Predicate<Question> isCorrectIndex(Index index) {
        return new ShowQuestionIndexPredicate(index);
    }
}
