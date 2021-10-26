package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NO_TAGS_AND_INDEXES;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.quiz.QuizCommandParser;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicate.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicate.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.predicate.TagsContainKeywordsPredicate;


class QuizCommandParserTest {

    public static final String INVALID_ARGUMENT = "@TASedg";
    private final QuizCommandParser parser = new QuizCommandParser();
    private ArrayList<Predicate<Question>> filterPredicates;

    @BeforeEach
    public void setUp() {
        filterPredicates = new ArrayList<>();
        filterPredicates.add(new ShowAllQuestionsPredicate());
    }

    @Test
    void parse_withInvalidArgs_throwParseException() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));

        // Users selecting more than 1 quiz types
        assertParseFailure(parser, " n/1 2 t/abcdef t/defg",
                String.format(MESSAGE_NO_TAGS_AND_INDEXES, QuizCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withValidArgs_success() {
        assertParseSuccess(parser, "      ", new QuizCommand(filterPredicates, null));
        assertParseSuccess(parser, "", new QuizCommand(filterPredicates, null));

        filterPredicates.add(new TagsContainKeywordsPredicate(Arrays.asList("CS2103T", "ST2334")));

        assertParseSuccess(parser, " t/CS2103T t/ST2334",
                new QuizCommand(filterPredicates, null));
    }

    @Test
    void parse_withIndex() {
        filterPredicates.add(new ShowQuestionIndexPredicate(Index.fromOneBased(1)));
        assertParseSuccess(parser, " n/1", new QuizCommand(filterPredicates, null));
    }
}
