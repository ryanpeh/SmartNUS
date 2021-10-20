package seedu.smartnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_QUESTIONS;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.ALICE;
import static seedu.smartnus.testutil.TypicalQuestions.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.model.question.predicate.NameContainsKeywordsPredicate;
import seedu.smartnus.testutil.SmartNusBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SmartNus(), new SmartNus(modelManager.getSmartNus()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSmartNusFilePath(Paths.get("smart/nus/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSmartNusFilePath(Paths.get("new/smart/nus/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setSmartNusFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSmartNusFilePath(null));
    }

    @Test
    public void setSmartNusFilePath_validPath_setsSmartNusFilePath() {
        Path path = Paths.get("smart/nus/file/path");
        modelManager.setSmartNusFilePath(path);
        assertEquals(path, modelManager.getSmartNusFilePath());
    }

    @Test
    public void hasQuestion_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasQuestion(null));
    }

    @Test
    public void hasQuestion_questionNotInSmartNus_returnsFalse() {
        assertFalse(modelManager.hasQuestion(ALICE));
    }

    @Test
    public void hasQuestion_questionInSmartNus_returnsTrue() {
        modelManager.addQuestion(ALICE);
        assertTrue(modelManager.hasQuestion(ALICE));
    }

    @Test
    public void getFilteredQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredQuestionList().remove(0));
    }

    @Test
    public void equals() {
        SmartNus smartNus = new SmartNusBuilder().withQuestion(ALICE).withQuestion(BENSON).build();
        SmartNus differentSmartNus = new SmartNus();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(smartNus, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(smartNus, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different smartNus -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSmartNus, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredQuestionList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(smartNus, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSmartNusFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(smartNus, differentUserPrefs)));
    }
}
