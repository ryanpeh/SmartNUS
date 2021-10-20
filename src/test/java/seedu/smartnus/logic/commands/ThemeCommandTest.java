package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalQuestions.getTypicalSmartNus;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.theme.DarkTheme;
import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.UserPrefs;

public class ThemeCommandTest {

    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());


    @Test
    public void execute_dark_test() {
        ThemeCommand themeCommand = new ThemeCommand(new DarkTheme());
        String expectedMessage = ThemeCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setTheme(new DarkTheme());
        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel);
        assertEquals(new DarkTheme(), model.getTheme());
    }

    @Test
    public void execute_light_test() {
        ThemeCommand themeCommand = new ThemeCommand(new LightTheme());
        String expectedMessage = ThemeCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(new SmartNus(model.getSmartNus()), new UserPrefs());
        expectedModel.setTheme(new LightTheme());
        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel);
        assertEquals(new LightTheme(), model.getTheme());
    }

}
