package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.quiz.NextQuestionCommand;
import seedu.address.logic.commands.quiz.PrevQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.quiz.QuizManager;
import seedu.address.storage.JsonSmartNusStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;


public class QuizInputParserTest {

    @TempDir
    public Path temporaryFolder;

    private QuizManager quizManager;

    private final QuizInputParser parser = new QuizInputParser();

    @BeforeEach
    public void setUp() {
        JsonSmartNusStorage addressBookStorage =
                new JsonSmartNusStorage(temporaryFolder.resolve("typicalQuestionsAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder
                .resolve("TypicalUserPref.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        Logic logic = new LogicManager(new ModelManager(), storage);
        quizManager = new QuizManager(logic.getFilteredQuestionList());
    }

    @Test
    public void parseCommand_next() throws Exception {
        NextQuestionCommand command =
                (NextQuestionCommand) parser.parseCommand(NextQuestionCommand.COMMAND_WORD, quizManager);
        assertEquals(new NextQuestionCommand(quizManager), command);
    }

    @Test
    public void parseCommand_prev() throws Exception {
        PrevQuestionCommand command =
                (PrevQuestionCommand) parser.parseCommand(PrevQuestionCommand.COMMAND_WORD, quizManager);
        assertEquals(new PrevQuestionCommand(quizManager), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, quizManager) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", quizManager) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, quizManager) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", quizManager) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", quizManager));
    }

}
