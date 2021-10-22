package seedu.smartnus.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;

import java.util.ArrayList;


/**
 * An UI component that displays information of a {@code Question}.
 */
public class TfqChoiceGrid extends UiPart<Region> {

    private static final String FXML = "TfqChoiceGrid.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private GridPane tfqGrid;
    @FXML
    private Button optionTrue;
    @FXML
    private Button optionFalse;


    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public TfqChoiceGrid(Question question, Choice selectedChoice) {
        super(FXML);

        ArrayList<Choice> choices = question.getOrderedChoices();
        boolean isQuestionAnswered = selectedChoice != null;

        if (isQuestionAnswered) {
            showCorrectOption(choices, question.getCorrectChoice());
            if (!selectedChoice.equals(question.getCorrectChoice())) {
                showIncorrectOption(choices, selectedChoice);
            }
        }

        // TODO: Remove function below if we're enabling click to select choice.
        disableButtons();
    }

    private void showCorrectOption(ArrayList<Choice> choices, Choice correctChoice) {
        int selectedIndex = choices.indexOf(correctChoice);

        switch (selectedIndex) {
        case 0:
            optionTrue.getStyleClass().add("quiz-choice-correct");
            break;
        case 1:
            optionFalse.getStyleClass().add("quiz-choice-correct");
            break;
        default:
            break;
        }
    }

    private void showIncorrectOption(ArrayList<Choice> choices, Choice selectedChoice) {
        int selectedIndex = choices.indexOf(selectedChoice);

        switch (selectedIndex) {
        case 0:
            optionTrue.getStyleClass().add("quiz-choice-incorrect");
            break;
        case 1:
            optionFalse.getStyleClass().add("quiz-choice-incorrect");
            break;
        default:
            break;
        }
    }

    private void disableButtons() {
        optionTrue.setDisable(true);
        optionFalse.setDisable(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TfqChoiceGrid)) {
            return false;
        }

        // state check
        TfqChoiceGrid card = (TfqChoiceGrid) other;
        return card == other;
    }
}
