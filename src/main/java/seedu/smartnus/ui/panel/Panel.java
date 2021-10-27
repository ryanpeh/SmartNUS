package seedu.smartnus.ui.panel;

import seedu.smartnus.logic.Logic;
import seedu.smartnus.ui.StatusBarFooter;

public interface Panel {
    Panel render(Logic logic);

    Panel disable(Logic logic);
}
