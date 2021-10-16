package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.model.question.TagsContainKeywordsPredicate;

public class FindByTagCommandParserTest {

    private FindByTagCommandParser parser = new FindByTagCommandParser();

    @Test
    public void parse_noKeywords_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByTagCommand() {
        // no leading and trailing whitespaces
        FindByTagCommand expectedFindByTagCommand =
                new FindByTagCommand(new TagsContainKeywordsPredicate(Arrays.asList("math", "english")));
        assertParseSuccess(parser, "math english", expectedFindByTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n math \n \t english  \t", expectedFindByTagCommand);
    }

}
