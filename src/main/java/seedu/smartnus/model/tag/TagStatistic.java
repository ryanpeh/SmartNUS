package seedu.smartnus.model.tag;

import seedu.smartnus.model.question.Statistic;

public class TagStatistic {
    private final Tag tag;
    private final Statistic statistic;

    /**
     * Constructor for a tag-statistic.
     * @param tag The tag.
     * @param statistic The statistic.
     */
    public TagStatistic(Tag tag, Statistic statistic) {
        this.tag = tag;
        this.statistic = statistic;
    }

    /**
     * Returns the title of the tag.
     * @return The tag name.
     */
    public String getTitle() {
        return tag.getTagName();
    }

    /**
     * Returns the number of attempts;
     * @return The number of attempts;
     */
    public int getAttemptCount() {
        return statistic.getAttemptCount();
    }

    /**
     * Returns the number of correct attempts;
     * @return The number of attempts;
     */
    public int getCorrectCount() {
        return statistic.getCorrectCount();
    }

    /**
     * Returns the number of correct percentage;
     * @return The number of correct percentage;
     */
    public int getCorrectPercentage() {
        return statistic.getCorrectPercentage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagStatistic
                && tag.equals(((TagStatistic) other).tag)
                && statistic.equals(((TagStatistic) other).statistic));
    }
}
