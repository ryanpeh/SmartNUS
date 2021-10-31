package seedu.smartnus.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class QuizProgressBarFooter extends UiPart<Region> {

    private static final String FXML = "QuizProgressBarFooter.fxml";

    @FXML
    private Label quizProgress;

    /**
     * Creates a {@code QuizProgressBarFooter} with the given {@code String}.
     */
    public QuizProgressBarFooter(String text) {
        super(FXML);
        quizProgress.setText(text);
    }

}
