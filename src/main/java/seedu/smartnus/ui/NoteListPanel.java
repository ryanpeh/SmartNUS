package seedu.smartnus.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.model.note.Note;

public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @javafx.fxml.FXML
    private ListView<Note> noteListView;

    /**
     * Creates a {@code QuestionListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Note> noteList) {
        super(FXML);
        noteListView.setItems(noteList);
        noteListView.setCellFactory(listView -> new NoteListPanel.NoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Question} using a {@code QuestionCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }

}
