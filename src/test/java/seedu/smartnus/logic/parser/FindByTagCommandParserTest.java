package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.FindByTagCommand;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;

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
