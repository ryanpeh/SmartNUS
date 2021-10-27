package seedu.smartnus.model.question;

/**
 * Class to keep track of question statistics.
 */
public class Statistic implements Comparable<Statistic> {

    private int attemptCount;
    private int correctCount;

    /**
     * Default constructor.
     */
    public Statistic() {
        this.attemptCount = 0;
        this.correctCount = 0;
    }

    /**
     * Constructor to specify the attempt count and correct count.
     * @param attemptCount The attempt count.
     * @param correctCount The correct count.
     */
    public Statistic(int attemptCount, int correctCount) {
        this.attemptCount = attemptCount;
        this.correctCount = correctCount;
    }

    /**
     * Returns the numbers of attempts.
     * @return The number of attempts done.
     */
    public int getAttemptCount() {
        return attemptCount;
    }

    /**
     * Returns the number of correct answers.
     * @return The number of correct answers.
     */
    public int getCorrectCount() {
        return correctCount;
    }

    /**
     * Updates the number of attempt (+1)
     */
    public void addAttempt() {
        attemptCount++;
    }

    /**
     * Updates the number of correct attempt (+1)
     */
    public void addCorrect() {
        correctCount++;
    }

    /**
     * Returns the percentage of correct attempts over total attempts.
     * @return The percentage of correct attempts.
     */
    public double getCorrectPercentage() {
        if (attemptCount == 0) {
            return 0;
        }
        double correct = correctCount * 1.0;
        double attempt = attemptCount * 1.0;
        return correct / attempt * 100;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        appendAttempts(builder);
        appendCorrect(builder);
        appendPercentage(builder);

        return builder.toString();
    }

    private StringBuilder appendAttempts(StringBuilder builder) {
        builder.append("Attempts: ")
                .append(getAttemptCount());
        return builder;
    }

    private StringBuilder appendCorrect(StringBuilder builder) {
        builder.append("; Corrects: ")
                .append(getCorrectCount());
        return builder;
    }

    private StringBuilder appendPercentage(StringBuilder builder) {
        builder.append("; Performance: ")
                .append(getCorrectPercentage())
                .append("%");
        return builder;
    }

    @Override
    public int compareTo(Statistic o) {
        int res = 0;
        Double percentage = this.getCorrectPercentage();
        Double otherPercentage = o.getCorrectPercentage();
        res = percentage.compareTo(otherPercentage);
        if (res != 0) {
            return res;
        }

        // if percentage is the same, use attempts as tiebreaker
        Integer attempts = this.attemptCount;
        Integer otherAttempts = o.attemptCount;
        return -attempts.compareTo(otherAttempts);
    }
}
