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
    public TfqChoiceGrid(QuizManager quizManager) {
        super(FXML);
        Question question = quizManager.currQuestion();
        // TODO: Remove function below if we're enabling click to select choice.
        disableButtons();
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
