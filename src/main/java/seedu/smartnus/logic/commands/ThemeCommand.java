package seedu.smartnus.logic.commands;

import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.commons.core.theme.Theme;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String LIGHT_KEYWORD = "light";
    public static final String DARK_KEYWORD = "dark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " light/dark";
    public static final String MESSAGE_SUCCESS = "Changed the theme";

    private Theme theme;

    public ThemeCommand(Theme theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setTheme(theme);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
