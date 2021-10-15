package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.commons.util.JsonUtil;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.testutil.TypicalQuestions;

public class JsonSerializableSmartNusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSmartNusTest");
    private static final Path TYPICAL_QUESTIONS_FILE = TEST_DATA_FOLDER.resolve("typicalQuestionsAddressBook.json");
    private static final Path INVALID_QUESTION_FILE = TEST_DATA_FOLDER.resolve("invalidQuestionAddressBook.json");
    private static final Path DUPLICATE_QUESTION_FILE = TEST_DATA_FOLDER.resolve("duplicateQuestionAddressBook.json");

    @Test
    public void toModelType_typicalQuestionsFile_success() throws Exception {
        JsonSerializableSmartNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_QUESTIONS_FILE,
                JsonSerializableSmartNus.class).get();
        SmartNus addressBookFromFile = dataFromFile.toModelType();
        SmartNus typicalQuestionsAddressBook = TypicalQuestions.getTypicalSmartNus();
        assertEquals(addressBookFromFile, typicalQuestionsAddressBook);
    }

    @Test
    public void toModelType_invalidQuestionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSmartNus dataFromFile = JsonUtil.readJsonFile(INVALID_QUESTION_FILE,
                JsonSerializableSmartNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateQuestions_throwsIllegalValueException() throws Exception {
        JsonSerializableSmartNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_QUESTION_FILE,
                JsonSerializableSmartNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSmartNus.MESSAGE_DUPLICATE_QUESTION,
                dataFromFile::toModelType);
    }

}
