package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.quiz.QuizCommandParser;

class QuizCommandParserTest {

    public static final String INVALID_ARGUMENT = "@TASedg";
    private final QuizCommandParser parser = new QuizCommandParser();


    @Test
    void parse_withInvalidArgs_throwParseException() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withValidArgs_success() {
        assertParseSuccess(parser, "      ", new QuizCommand());
        assertParseSuccess(parser, "", new QuizCommand());
    }
}
