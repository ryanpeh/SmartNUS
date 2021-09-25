package seedu.address.model.choice;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Choice {
    public static final String MESSAGE_CONSTRAINTS = "Choices can take any values, and it should not be blank";

    /*
     * The first character of the choice must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    
    private final String title;
    private final boolean isCorrect;

    /**
     * Constructs a {@code Choice}.
     *
     * @param title Title of the Choice.
     * @param isCorrect Whether the Choice is the correct answer to a Question.
     */
    public Choice(String title, boolean isCorrect) {
        requireNonNull(title);
        checkArgument(isValidChoiceTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
        this.isCorrect = isCorrect;
    }

    /**
     * Returns true if a given string is a valid choice title.
     */
    public static boolean isValidChoiceTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Choice)) {
            return false;
        }

        Choice otherChoice = (Choice) other;
        return otherChoice.getTitle().equals(getTitle())
                && otherChoice.getIsCorrect() == getIsCorrect();
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
