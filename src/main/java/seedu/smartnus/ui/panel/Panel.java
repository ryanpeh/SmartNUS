package seedu.smartnus.ui.panel;

import seedu.smartnus.logic.Logic;

public interface Panel {
    Panel render(Logic logic);

    Panel disable(Logic logic);
}
