package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_SAQ_ANSWER_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_SAQ_KEYWORD_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_SAQ_KEYWORD_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_SECOND_QUESTION;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.testutil.EditQuestionDescriptorBuilder;
import seedu.smartnus.testutil.QuestionBuilder;
import seedu.smartnus.testutil.TypicalQuestions;
import seedu.smartnus.ui.panel.StatisticListPanel;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListEditMcq_success() {
        Question editedQuestion = new QuestionBuilder().build();
        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder(editedQuestion).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_QUESTION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion);

        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setQuestion(model.getFilteredQuestionList().get(0), editedQuestion);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListEditSaq_success() {
        Index indexLastQuestion = Index.fromOneBased(TypicalQuestions.SAQ_ONE_BASED_INDEX);
        Question lastQuestion = model.getFilteredQuestionList().get(indexLastQuestion.getZeroBased());

        QuestionBuilder questionInList = new QuestionBuilder(lastQuestion);
        Choice validSaqAnswer = new Choice(VALID_SAQ_ANSWER_1, true,
                new HashSet<>(List.of(VALID_SAQ_KEYWORD_1, VALID_SAQ_KEYWORD_2)));
        Set<Choice> validSaqChoiceSet = new HashSet<>(List.of(validSaqAnswer));

        Question editedQuestion = questionInList.withName(VALID_NAME_BOB).withImportance(VALID_IMPORTANCE_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .withChoices(validSaqAnswer).buildSaq();

        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withName(VALID_NAME_BOB)
                .withImportance(VALID_IMPORTANCE_BOB).withTags(VALID_TAG_HUSBAND)
                .withSaqChoices(validSaqChoiceSet).build();
        EditCommand editCommand = new EditCommand(indexLastQuestion, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion);

        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setQuestion(lastQuestion, editedQuestion);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListEditTf_success() {
        Index indexSecondLastQuestion = Index.fromOneBased(TypicalQuestions.TF_ONE_BASED_INDEX);
        Question lastQuestion = model.getFilteredQuestionList().get(indexSecondLastQuestion.getZeroBased());

        QuestionBuilder questionInList = new QuestionBuilder(lastQuestion);
        Question editedQuestion = questionInList.withName(VALID_NAME_BOB).withImportance(VALID_IMPORTANCE_BOB)
                .withTags(VALID_TAG_HUSBAND).buildTrueFalse();

        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withName(VALID_NAME_BOB)
                .withImportance(VALID_IMPORTANCE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexSecondLastQuestion, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion);

        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setQuestion(lastQuestion, editedQuestion);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_QUESTION, new EditQuestionDescriptor());
        Question editedQuestion = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion);

        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Question questionInFilteredList = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        Question editedQuestion = new QuestionBuilder(questionInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_QUESTION,
                new EditQuestionDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion);

        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setQuestion(model.getFilteredQuestionList().get(0), editedQuestion);
        model.resetFilteredQuestionList();
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateQuestionUnfilteredList_failure() {
        Question firstQuestion = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        EditCommand.EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder(firstQuestion).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_QUESTION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_QUESTION);
    }

    @Test
    public void execute_duplicateQuestionFilteredList_failure() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        // edit question in filtered list into a duplicate in SmartNus
        Question questionInList = model.getSmartNus().getQuestionList().get(INDEX_SECOND_QUESTION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_QUESTION,
                new EditQuestionDescriptorBuilder(questionInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_QUESTION);
    }

    @Test
    public void execute_invalidQuestionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredQuestionList().size() + 1);
        EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of the {@code SmartNus} question list
     */
    @Test
    public void execute_invalidQuestionIndexFilteredList_failure() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);
        Index outOfBoundIndex = INDEX_SECOND_QUESTION;
        // ensures that outOfBoundIndex is still in bounds of SmartNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartNus().getQuestionList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditQuestionDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void createEditedMcq_optionsButNoAnswer_failure() {
        Index mcqIndex = Index.fromOneBased(TypicalQuestions.MCQ_ONE_BASED_INDEX);
        EditCommand editCommand = new EditCommand(mcqIndex,
                new EditQuestionDescriptorBuilder().withWrongChoices("1", "2", "3").build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_ANSWER);
    }

    @Test
    public void createEditedMcq_answerNoOptions_failure() {
        Index mcqIndex = Index.fromOneBased(TypicalQuestions.MCQ_ONE_BASED_INDEX);
        EditCommand editCommand = new EditCommand(mcqIndex,
                new EditQuestionDescriptorBuilder().withAnswer("1").build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_OPTIONS);
    }

    @Test
    public void createEditedMcq_invalidMcqChoices_failure() {
        Index mcqIndex = Index.fromOneBased(TypicalQuestions.MCQ_ONE_BASED_INDEX);
        // only two wrong, one correct choice. MCQs should have three wrong, one correct choice.
        EditCommand editCommand = new EditCommand(mcqIndex,
                new EditQuestionDescriptorBuilder().withAnswer("1").withWrongChoices("2", "3").build());

        assertCommandFailure(editCommand, model, MultipleChoiceQuestion.MESSAGE_VALID_MCQ);
    }

    @Test
    public void createEditedTf_wrongChoicesPresent_failure() {
        Index tfIndex = Index.fromOneBased(TypicalQuestions.TF_ONE_BASED_INDEX);
        EditCommand editCommand = new EditCommand(tfIndex,
                new EditQuestionDescriptorBuilder().withWrongChoices("F").build());

        assertCommandFailure(editCommand, model, TrueFalseQuestion.MESSAGE_OPTIONS_INVALID);
    }

    @Test
    public void createEditedTf_invalidAnswer_failure() {
        // TrueFalseQuestions can only have "True" or "False" as choice titles
        Index tfIndex = Index.fromOneBased(TypicalQuestions.TF_ONE_BASED_INDEX);
        Set<Choice> choices = new HashSet<>();
        choices.addAll(List.of(new Choice("abc", true), new Choice("False", false)));
        EditCommand editCommand = new EditCommand(tfIndex,
                new EditQuestionDescriptorBuilder().withTfqChoices(choices).build());
        assertCommandFailure(editCommand, model, TrueFalseQuestion.MESSAGE_ANSWER_INVALID);
    }

    @Test
    public void createEditedSaq_hasWrongOptionsPresent_failure() {
        Index saqIndex = Index.fromOneBased(TypicalQuestions.SAQ_ONE_BASED_INDEX);
        EditCommand editCommand = new EditCommand(saqIndex,
                new EditQuestionDescriptorBuilder().withWrongChoices("F").build());

        assertCommandFailure(editCommand, model, ShortAnswerQuestion.MESSAGE_OPTIONS_INVALID);
    }

    @Test
    public void createEditedSaq_answerHasNoKeywords_failure() {
        Index saqIndex = Index.fromOneBased(TypicalQuestions.SAQ_ONE_BASED_INDEX);
        EditCommand editCommand = new EditCommand(saqIndex,
                new EditQuestionDescriptorBuilder()
                        .withSaqChoices(new HashSet<>(List.of(new Choice("F", true))))
                        .build());

        assertCommandFailure(editCommand, model, ShortAnswerQuestion.MESSAGE_VALID_SAQ);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_QUESTION, DESC_AMY);

        // same values -> returns true
        EditCommand.EditQuestionDescriptor copyDescriptor = new EditCommand.EditQuestionDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_QUESTION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_QUESTION, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_QUESTION, DESC_BOB)));
    }

    @Test
    public void edit_wrongPanel() {
        ModelStub model = new EditCommandTest.ModelStub();
        EditCommand qnCmd = new EditCommand(INDEX_FIRST_QUESTION, DESC_AMY);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_QUESTION_PANEL, () -> qnCmd.execute(model));
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
