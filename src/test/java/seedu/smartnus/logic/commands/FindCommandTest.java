package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.commons.core.Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.FIONA;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.ui.panel.StatisticListPanel;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ArrayList<Predicate<Question>> firstPredicateArr = new ArrayList<>();
        firstPredicateArr.add(firstNamePredicate);
        ArrayList<Predicate<Question>> secondPredicateArr = new ArrayList<>();
        secondPredicateArr.add(secondNamePredicate);
        FindCommand findFirstCommand = new FindCommand(firstPredicateArr);
        FindCommand findSecondCommand = new FindCommand(secondPredicateArr);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicateArr);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different question -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Question>> arr = new ArrayList<>();
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        arr.add(predicate);
        FindCommand command = new FindCommand(arr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredQuestionList());
        assertEquals(model.getPanel(), QUESTION_KEYWORD);
    }

    @Test
    public void execute_multipleKeywords_oneQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Fiona Kunz");
        ArrayList<Predicate<Question>> arr = new ArrayList<>();
        arr.add(predicate);
        FindCommand command = new FindCommand(arr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredQuestionList());
        assertEquals(model.getPanel(), QUESTION_KEYWORD);
    }

    @Test
    public void find_wrongPanel() {
        ModelStub model = new FindCommandTest.ModelStub();
        FindCommand command = new FindCommand(new ArrayList<Predicate<Question>>());
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> command.execute(model));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
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
