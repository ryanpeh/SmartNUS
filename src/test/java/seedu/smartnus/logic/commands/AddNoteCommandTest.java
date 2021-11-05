package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.testutil.NoteBuilder;
import seedu.smartnus.testutil.QuestionBuilder;

public class AddNoteCommandTest {
    @Test
    public void constructor_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null));
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        AddNoteCommandTest.ModelStubAcceptingNoteAdded modelStub = new AddNoteCommandTest.ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute(modelStub);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validNote), modelStub.notesAdded);
    }
    // todo: if duplicate note checker is implemented, add a test here to check if it works properly

    @Test
    public void equals() {
        Note alice = new NoteBuilder().withName("Alice").build();
        Note bob = new NoteBuilder().withName("Bob").build();
        AddNoteCommand addAliceCommand = new AddNoteCommand(alice);
        AddNoteCommand addBobCommand = new AddNoteCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddNoteCommand addAliceCommandCopy = new AddNoteCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different question -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

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
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNote(Note note) {
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
        public void setPanel(String panel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getPanel() {
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
    }

    /**
     * A Model stub that always accept the note being added.
     */
    private class ModelStubAcceptingNoteAdded extends ModelStub {
        final ArrayList<Note> notesAdded = new ArrayList<>();

        @Override
        public void addNote(Note note) {
            requireNonNull(note);
            notesAdded.add(note);
        }

        @Override
        public ReadOnlySmartNus getSmartNus() {
            return new SmartNus();
        }
    }
}
