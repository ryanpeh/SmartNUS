package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.quiz.QuizCommandParser;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;


class QuizCommandParserTest {

    public static final String INVALID_ARGUMENT = "@TASedg";
    private final QuizCommandParser parser = new QuizCommandParser();


    @Test
    void parse_withInvalidArgs_throwParseException() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));

        // Users selecting more than 1 quiz types
        assertParseFailure(parser, " n/1 2 t/abcdef t/defg",
                String.format(MESSAGE_TOO_MANY_ARGUMENTS, QuizCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withValidArgs_success() {
        assertParseSuccess(parser, "      ", new QuizCommand(new ShowAllQuestionsPredicate()));
        assertParseSuccess(parser, "", new QuizCommand(new ShowAllQuestionsPredicate()));
        assertParseSuccess(parser, " t/CS2103T t/ST2334",
                new QuizCommand(new TagsContainKeywordsPredicate(Arrays.asList("CS2103T", "ST2334"))));
    }

    @Test
    void parse_withIndex() {
        assertParseSuccess(parser, " n/1", new QuizCommand(new ShowQuestionIndexPredicate(Index.fromOneBased(1))));
    }
}
