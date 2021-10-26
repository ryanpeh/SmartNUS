package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NUMBER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.ArgumentMultimap;
import seedu.smartnus.logic.parser.ArgumentTokenizer;
import seedu.smartnus.logic.parser.Parser;
import seedu.smartnus.logic.parser.ParserUtil;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicate.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicate.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.predicate.TagsContainKeywordsPredicate;
import seedu.smartnus.model.tag.Tag;

public class QuizCommandParser implements Parser<QuizCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QuizCommand
     * and returns a QuizCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QuizCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NUMBER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
        }

        Collection<String> indexes = argMultimap.getAllValues(PREFIX_NUMBER);
        Collection<String> tags = argMultimap.getAllValues(PREFIX_TAG);

        // Check if the number of arguments is correct
        if (indexes.size() > 0 && tags.size() > 0) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_ARGUMENTS, QuizCommand.MESSAGE_USAGE));
        }

        // Returns the command
        if (indexes.size() > 0) {
            Set<Index> indexSet = ParserUtil.parseQuizIndexes(argMultimap.getAllValues(PREFIX_NUMBER));
            return new QuizCommand(getIndexPredicate(indexSet));
        } else if (tags.size() > 0) {
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            List<String> tagKeywords = new ArrayList<>();
            tagList.stream().forEach(tag -> tagKeywords.add(tag.getTagName()));
            return new QuizCommand(getTagPredicate(tagKeywords));
        }

        return new QuizCommand(new ShowAllQuestionsPredicate());
    }

    private Predicate<Question> getTagPredicate(List<String> tagKeywords) {
        return new TagsContainKeywordsPredicate(tagKeywords);
    }

    private Predicate<Question> getIndexPredicate(Set<Index> indexes) {
        return new ShowQuestionIndexPredicate(indexes);
    }
}
