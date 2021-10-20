package seedu.smartnus.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.model.question.Question;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Question> PREDICATE_SHOW_ALL_QUESTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' SmartNus file path.
     */
    Path getSmartNusFilePath();

    /**
     * Sets the user prefs' SmartNus file path.
     */
    void setSmartNusFilePath(Path smartNusFilePath);

    /**
     * Replaces SmartNus data with the data in {@code smartNus}.
     */
    void setSmartNus(ReadOnlySmartNus smartNus);

    /** Returns the SmartNus */
    ReadOnlySmartNus getSmartNus();

    /**
     * Returns true if a question with the same identity as {@code question} exists in SmartNus.
     */
    boolean hasQuestion(Question question);

    /**
     * Deletes the given question.
     * The question must exist in SmartNus.
     */
    void deleteQuestion(Question target);

    /**
     * Adds the given question.
     * {@code question} must not already exist in SmartNus.
     */
    void addQuestion(Question question);

    /**
     * Replaces the given question {@code target} with {@code editedQuestion}.
     * {@code target} must exist in SmartNus.
     * The question identity of {@code editedQuestion} must not be the same as another existing question
     * in SmartNus.
     */
    void setQuestion(Question target, Question editedQuestion);

    /** Returns an unmodifiable view of the filtered question list */
    ObservableList<Question> getFilteredQuestionList();

    /** Returns an unmodifiable view of the filtered quiz question list */
    ObservableList<Question> getFilteredQuizQuestionList();

    /**
     * Updates the filter of the filtered question list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuestionList(Predicate<Question> predicate);

    /**
     * Updates the filter of the filtered quiz question list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuizQuestionList(Predicate<Question> predicate);
}
