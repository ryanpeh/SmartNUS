package seedu.smartnus.model;

import java.nio.file.Path;

import seedu.smartnus.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getSmartNusFilePath();

}
