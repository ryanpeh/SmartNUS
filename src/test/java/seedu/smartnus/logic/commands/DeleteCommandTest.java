package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_SECOND_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_THIRD_QUESTION;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.ui.panel.StatisticListPanel;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredQuestionList_success() {
        Question questionToDelete = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete);

        ModelManager expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredQuestionList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand("question", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Question questionToDelete = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete);

        Model expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDelete);
        showNoQuestion(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Index outOfBoundIndex = INDEX_SECOND_QUESTION;
        // ensures that outOfBoundIndex is still in bounds of the SmartNus question list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartNus().getQuestionList().size());

        DeleteCommand deleteCommand = new DeleteCommand("question", outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        DeleteCommand deleteSecondCommand = new DeleteCommand("question", INDEX_SECOND_QUESTION);
        DeleteCommand deleteThirdCommand = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        DeleteCommand deleteFourthCommand = new DeleteCommand("note", INDEX_THIRD_QUESTION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        DeleteCommand deleteThirdCommandCopy = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        assertTrue(deleteThirdCommand.equals(deleteThirdCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different question -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different deleteItems, same index -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));

        // different index, deleteItems -> returns false
        assertFalse(deleteFirstCommand.equals(deleteFourthCommand));
    }

    @Test
    public void delete_wrongPanel() {
        // Delete notes
        ModelStub model = new DeleteCommandTest.ModelStub();
        DeleteCommand noteCmd = new DeleteCommand("note", INDEX_FIRST_QUESTION);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_NOTE_PANEL, () -> noteCmd.execute(model));

        // Delete question
        DeleteCommand qnCmd = new DeleteCommand("question", INDEX_FIRST_QUESTION);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> qnCmd.execute(model));
    }

    /**
     * Updates {@code model}'s filtered list to show no question.
     */
    private void showNoQuestion(Model model) {
        model.updateFilteredQuestionList(p -> false);

        assertTrue(model.getFilteredQuestionList().isEmpty());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSmartNusFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSmartNusFilePath(Path smartNusFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addQuestion(Question question) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSmartNus(ReadOnlySmartNus smartNus) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySmartNus getSmartNus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasQuestion(Question question) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteQuestion(Question target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setQuestion(Question target, Question editedQuestion) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Question> getFilteredQuestionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Question> getFilteredQuizQuestionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredQuestionList(Predicate<Question> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetFilteredQuestionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredQuizQuestionList(Comparator<Question> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(Theme theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Theme getTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TagStatistic> getTagStatistic() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagStatistic(Predicate<TagStatistic> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPanel(String panel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getPanel() {
            return StatisticListPanel.STATISTIC_PANEL;
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredQuizQuestionList(Predicate<Question> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
