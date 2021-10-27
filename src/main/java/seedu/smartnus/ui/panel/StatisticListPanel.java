package seedu.smartnus.ui.panel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.logic.Logic;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Statistic;
import seedu.smartnus.ui.StatusBarFooter;
import seedu.smartnus.ui.UiPart;
import seedu.smartnus.ui.card.NoteCard;

public class StatisticListPanel extends UiPart<Region> implements Panel {
    public static final String STATISTIC_PANEL = "stats";

    private static final String FXML = "StatisticListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(StatisticListPanel.class);

    @FXML
    private ListView<Statistic> statisticListView;

    private StackPane statisticListPanelPlaceholder;
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code NoteListPanel} with the given {@code ObservableList}.
     */
    public StatisticListPanel(StackPane noteListPanelPlaceholder, StackPane status) {
        super(FXML);
        this.statisticListPanelPlaceholder = noteListPanelPlaceholder;
        statusbarPlaceholder = status;
    }

    private StatisticListPanel(ObservableList<Statistic> statisticList) {
        super(FXML);
        statisticListView.setItems(statisticList);
        statisticListView.setCellFactory(listView -> new StatisticListPanel.StatisticListViewCell());
    }

    @Override
    public Panel render(Logic logic) {
        StatisticListPanel statisticListPanel = new StatisticListPanel(logic.getTagStatistic());
        statisticListPanelPlaceholder.setVisible(true);
        statisticListPanelPlaceholder.getChildren().add(statisticListPanel.getRoot());

        StatusBarFooter status = new StatusBarFooter("Notes");
        statusbarPlaceholder.getChildren().add(status.getRoot());

        return statisticListPanel;
    }

    @Override
    public Panel disable(Logic logic) {
        statisticListPanelPlaceholder.setVisible(false);
        statisticListPanelPlaceholder.managedProperty().bind(statisticListPanelPlaceholder.visibleProperty());
        return this;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Question} using a {@code QuestionCard}.
     */
    class StatisticListViewCell extends ListCell<Statistic> {
        @Override
        protected void updateItem(Statistic statistic, boolean empty) {
            super.updateItem(statistic, empty);

            if (empty || statistic == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StatisticCard(statistic, getIndex() + 1).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof StatisticListPanel;
    }
}
