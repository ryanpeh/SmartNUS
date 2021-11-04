package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.commons.core.Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;
import static seedu.smartnus.model.util.SampleDataUtil.MCQ_QUESTION_INDEX;
import static seedu.smartnus.model.util.SampleDataUtil.TFQ_QUESTION_INDEX;
import static seedu.smartnus.model.util.SampleDataUtil.getSampleQuestions;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.HasImportancePredicate;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.model.util.SampleDataUtil;
import seedu.smartnus.ui.panel.StatisticListPanel;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(SampleDataUtil.getSampleSmartNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(SampleDataUtil.getSampleSmartNus(), new UserPrefs());

    @Test
    public void equals() {
        // TODO: add tags and importance predicates
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        TagsContainKeywordsPredicate firstTagPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("first"));
        TagsContainKeywordsPredicate secondTagPredicate =
                new TagsContainKeywordsPredicate(List.of("first", "second"));
        HasImportancePredicate firstImportancePredicate =
                new HasImportancePredicate(new Importance("1"));
        HasImportancePredicate secondImportancePredicate =
                new HasImportancePredicate(new Importance("2"));

        ArrayList<Predicate<Question>> firstPredicateArr = new ArrayList<>();
        firstPredicateArr.addAll(List.of(firstNamePredicate, firstTagPredicate, firstImportancePredicate));
        FindCommand findFirstCommand = new FindCommand(firstPredicateArr);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicateArr);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different name predicate -> returns false
        ArrayList<Predicate<Question>> differentArr = new ArrayList<>();
        differentArr.addAll(List.of(secondNamePredicate, firstTagPredicate, firstImportancePredicate));
        assertFalse(findFirstCommand.equals(new FindCommand(differentArr)));

        // different tag predicate -> returns false
        differentArr.clear();
        differentArr.addAll(List.of(firstNamePredicate, secondTagPredicate, firstImportancePredicate));
        assertFalse(findFirstCommand.equals(new FindCommand(differentArr)));

        // different importance predicate -> returns false
        differentArr.clear();
        differentArr.addAll(List.of(firstNamePredicate, firstTagPredicate, secondImportancePredicate));
        assertFalse(findFirstCommand.equals(new FindCommand(differentArr)));
    }

    @Test
    public void execute_multipleKeywords_oneQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("grey-box test approaches");
        ArrayList<Predicate<Question>> predicateArr = new ArrayList<>();
        predicateArr.add(predicate);
        FindCommand command = new FindCommand(predicateArr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(getSampleQuestions()[TFQ_QUESTION_INDEX]), model.getFilteredQuestionList());
        assertEquals(model.getPanel(), QUESTION_KEYWORD);
    }

    @Test
    public void execute_multipleKeywordsNotAllMatch_noQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 0);
        ArrayList<Predicate<Question>> predicateArr = new ArrayList<>();
        NameContainsKeywordsPredicate predicate =
                prepareNamePredicate("grey-box test approaches word-that-does-not-exist");
        predicateArr.add(predicate);
        FindCommand command = new FindCommand(predicateArr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredQuestionList());
    }

    @Test
    public void execute_multipleTagsOnlyOneMatches_fourQuestionsFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 4);
        ArrayList<Predicate<Question>> predicateArr = new ArrayList<>();
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(List.of("CS2103T", "RandomTag"));
        predicateArr.add(predicate);
        FindCommand command = new FindCommand(predicateArr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        Question[] questions = getSampleQuestions();
        assertEquals(Arrays.asList(questions[0], questions[1], questions[4], questions[6]),
                model.getFilteredQuestionList());
    }

    @Test
    public void execute_wordWithPunctuationMarkMatch_oneQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("standard");
        ArrayList<Predicate<Question>> predicateArr = new ArrayList<>();
        predicateArr.add(predicate);
        FindCommand command = new FindCommand(predicateArr);
        expectedModel.updateFilteredQuestionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // question contains "standard?" where "standard" should be taken as a full word match
        assertEquals(Arrays.asList(getSampleQuestions()[MCQ_QUESTION_INDEX]), model.getFilteredQuestionList());
    }

    @Test
    public void execute_multiplePredicates_oneQuestionFound() {
        String expectedMessage = String.format(MESSAGE_QUESTIONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("standard is");
        TagsContainKeywordsPredicate tagPredicate = new TagsContainKeywordsPredicate(List.of("CS2103T", "Java"));
        HasImportancePredicate importancePredicate = new HasImportancePredicate(new Importance("2"));
        ArrayList<Predicate<Question>> predicateArr = new ArrayList<>();
        predicateArr.addAll(List.of(namePredicate, tagPredicate, importancePredicate));
        FindCommand command = new FindCommand(predicateArr);
        expectedModel.updateFilteredQuestionList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(getSampleQuestions()[MCQ_QUESTION_INDEX]), model.getFilteredQuestionList());
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
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.trim()
                .split("([,.?!:;*\"()\\[\\]{}]|\\s)+")));
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
