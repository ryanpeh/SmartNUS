package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.QuizCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class QuizCommandParserTest {

    private final QuizCommandParser parser = new QuizCommandParser();

    @Test
    void parse_withInvalidArgs_throwParseException() {
        String INVALID_ARGUMENTS = "@TASedg";
        assertParseFailure(parser, INVALID_ARGUMENTS, String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withValidArgs_success(){
        assertParseSuccess(parser, "      ", new QuizCommand());
        assertParseSuccess(parser, "", new QuizCommand());
    }
}
