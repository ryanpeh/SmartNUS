package seedu.smartnus.logic.parser;

import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.NOTE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_1;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.AddNoteCommand;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;

public class AddNoteCommandParserTest {
    private final AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    void parse_allFieldsValid_success() {

        Note expectedNote = new Note(VALID_NOTE_1);

        AddNoteCommand expectedCommand = new AddNoteCommand(expectedNote);

        // normal command with preamble whitespace
        assertParseSuccess(parser, NOTE_DESC_1, expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = NoteName.MESSAGE_CONSTRAINTS;
        // note prefix missing
        assertParseFailure(parser, INVALID_NOTE_DESC , expectedMessage);
    }

    /*
    @Test
    void parse_fieldsInvalid_failure() {
        // TODO: Add tests for duplicate options and answer
        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_1 + OPTIONS_DESC_1 + INVALID_ANSWER_DESC + IMPORTANCE_DESC_1,
                Choice.MESSAGE_CONSTRAINTS);
        // 1 invalid option with 2 valid
        assertParseFailure(parser, QUESTION_DESC_1 + OPTION_DESC_1 + INVALID_OPTION_DESC_1 + OPTION_DESC_3
                + ANSWER_DESC_1 + IMPORTANCE_DESC_1, Choice.MESSAGE_CONSTRAINTS);
        // not enough options
        assertParseFailure(parser,
                QUESTION_DESC_1 + OPTION_DESC_1 + OPTION_DESC_3 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
        // too many options
        assertParseFailure(parser, QUESTION_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1 + OPTIONS_DESC_2,
                MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + OPTIONS_DESC_1 + ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                Name.MESSAGE_CONSTRAINTS);
        // invalid importance
        assertParseFailure(parser,
                QUESTION_DESC_1 + OPTIONS_DESC_1 + ANSWER_DESC_1 + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS);
    }
    */
}
