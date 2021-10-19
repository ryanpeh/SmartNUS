package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.AddNoteCommand;
import seedu.smartnus.model.note.Note;

public class AddNoteCommandParserTest {
    private final AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    void parse_allFieldsValid_success() {
        String expectedName = "random note";

        Note expectedNote = new Note(expectedName, new HashSet<>());

        AddNoteCommand expectedCommand = new AddNoteCommand(expectedNote);

        // normal command with preamble whitespace
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_NOTE_1, expectedCommand);

        assertParseSuccess(parser, VALID_NOTE_2, expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);
        // note prefix missing
        assertParseFailure(parser, INVALID_NOTE_DESC , expectedMessage);
    }

//    @Test
//    void parse_fieldsInvalid_failure() {
//        // TODO: Add tests for duplicate options and answer
//        // invalid answer
//        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + INVALID_ANSWER_DESC + IMPORTANCE_DESC_1,
//                Choice.MESSAGE_CONSTRAINTS);
//        // 1 invalid option with 2 valid
//        assertParseFailure(parser, QUESTION_DESC_1 + OPTION_DESC_1 + INVALID_OPTION_DESC + OPTION_DESC_3
//                + ANSWER_DESC_1 + IMPORTANCE_DESC_1, Choice.MESSAGE_CONSTRAINTS);
//        // not enough options
//        assertParseFailure(parser,
//                QUESTION_DESC_1 + OPTION_DESC_1 + OPTION_DESC_3 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
//                MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
//        // too many options
//        assertParseFailure(parser, QUESTION_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1 + OPTIONS_DESC_2,
//                MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
//        // invalid question
//        assertParseFailure(parser, INVALID_QUESTION_DESC + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
//                Name.MESSAGE_CONSTRAINTS);
//        // invalid importance
//        assertParseFailure(parser,
//                QUESTION_DESC_1 + OPTIONS_DESC_1 + ANSWER_DESC_1 + INVALID_IMPORTANCE_DESC,
//                Importance.MESSAGE_CONSTRAINTS);
//    }
}
