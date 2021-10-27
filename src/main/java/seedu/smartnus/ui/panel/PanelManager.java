package seedu.smartnus.ui.panel;

import seedu.smartnus.logic.Logic;
import seedu.smartnus.model.note.NoteList;
import seedu.smartnus.ui.StatusBarFooter;

import java.util.ArrayList;
import java.util.List;

public class PanelManager {
    private List<Panel> panelList;
    private Logic logic;

    public PanelManager(Logic logic) {
        panelList = new ArrayList<>();
        this.logic = logic;
    }

    public void addPanel(Panel panel) {
        panelList.add(panel);
    }

    public void showPanel(Panel panel) {
        for (Panel otherPanel : panelList) {
            if (!otherPanel.equals(panel)) {
                otherPanel.disable(logic);
            } else {
                otherPanel.render(logic);
            }
        }
    }
}
