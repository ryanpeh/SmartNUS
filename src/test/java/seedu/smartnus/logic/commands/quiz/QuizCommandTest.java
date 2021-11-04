package seedu.smartnus.logic.commands.quiz;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
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
import seedu.smartnus.model.question.comparator.QuestionsDefaultComparator;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowQuestionIndexPredicate;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.ui.panel.StatisticListPanel;


class QuizCommandTest {

    private Model model;
    private Model expectedModel;
    private ArrayList<Predicate<Question>> filterPredicates;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
        filterPredicates = new ArrayList<>();
    }

    @Test
    void execute_startQuizWithoutArguments_success() {
        filterPredicates.add(new ShowAllQuestionsPredicate());
        assertCommandSuccess(new QuizCommand(filterPredicates, null), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizWithComparator() {
        Comparator<Question> defaultComparator = new QuestionsDefaultComparator();
        expectedModel.sortFilteredQuizQuestionList(defaultComparator);
        assertCommandSuccess(new QuizCommand(filterPredicates, defaultComparator), model,
                QuizCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_quizNoQuestions() {
        filterPredicates.add(new ShowQuestionIndexPredicate(Index.fromOneBased(100)));
        assertCommandFailure(new QuizCommand(filterPredicates, null), model,
                QuizCommand.MESSAGE_NO_QUESTIONS);
    }

    @Test
    public void quiz_wrongPanel() {
        ModelStub modelStub = new QuizCommandTest.ModelStub();
        QuizCommand command = new QuizCommand(filterPredicates, null);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> command.execute(modelStub));
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
