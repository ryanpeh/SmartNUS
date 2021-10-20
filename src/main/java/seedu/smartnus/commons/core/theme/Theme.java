package seedu.smartnus.commons.core.theme;

import static seedu.smartnus.commons.core.theme.LightTheme.LIGHT_EXTENSION_CSS_FILE;
import static seedu.smartnus.commons.core.theme.LightTheme.LIGHT_THEME_CSS_FILE;

public class Theme {

    private final String THEME_CSS_FILE;
    private final String EXTENSIONS_CSS_FILE;

    public Theme() {
        this.THEME_CSS_FILE = LIGHT_THEME_CSS_FILE;
        this.EXTENSIONS_CSS_FILE = LIGHT_EXTENSION_CSS_FILE;
    }


    /**
     * Constructor for a theme.
     * @param themeCssFile The theme css file name.
     * @param extensionCssFile The extension css file name.
     */
    public Theme(String themeCssFile, String extensionCssFile) {
        this.THEME_CSS_FILE = themeCssFile;
        this.EXTENSIONS_CSS_FILE = extensionCssFile;
    }


    /**
     * Returns the filename of the theme css file.
     * @return The filename of the theme css file.
     */
    public String getThemeCssFile() {
        return THEME_CSS_FILE;
    }

    /**
     * Returns the filename of the extensions css file.
     * @return The filename of the extensions css file.
     */
    public String getExtensionsCssFile() {
        return EXTENSIONS_CSS_FILE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Theme)) { //this handles null as well.
            return false;
        }

        Theme o = (Theme) other;

        return THEME_CSS_FILE.equals(o.THEME_CSS_FILE)
                && EXTENSIONS_CSS_FILE.equals(o.EXTENSIONS_CSS_FILE);
    }

    @Override
    public String toString() {
        return "Theme";
    }
}
