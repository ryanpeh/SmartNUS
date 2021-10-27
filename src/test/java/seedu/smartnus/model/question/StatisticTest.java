package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatisticTest {

    private Statistic defaultStat;

    @BeforeEach
    void setUp() {
        defaultStat = new Statistic();
    }

    @Test
    public void equals_test() {
        Statistic tenAttempts = new Statistic(10, 5);

        assertEquals(0, defaultStat.getAttemptCount());
        assertEquals(0, defaultStat.getCorrectCount());
        assertEquals(0.0, defaultStat.getCorrectPercentage());

        assertEquals(10, tenAttempts.getAttemptCount());
        assertEquals(5, tenAttempts.getCorrectCount());
        assertEquals(50.0, tenAttempts.getCorrectPercentage());
    }

    @Test
    public void update_test() {
        Statistic defaultStat = new Statistic();
        defaultStat.addAttempt();
        defaultStat.addCorrect();
        assertEquals(1, defaultStat.getAttemptCount());
        assertEquals(1, defaultStat.getCorrectCount());
        assertEquals(100.0, defaultStat.getCorrectPercentage());

        defaultStat.addAttempt();
        assertEquals(2, defaultStat.getAttemptCount());
        assertEquals(1, defaultStat.getCorrectCount());
        assertEquals(50.0, defaultStat.getCorrectPercentage());
    }

    @Test
    public void string_test() {
        Statistic defaultStat = new Statistic();
        assertNotEquals(null, defaultStat.toString());
    }

    @Test
    void compareTo() {
        Statistic testStatistic = new Statistic(0, 0);

        // same stats
        assertEquals(testStatistic.compareTo(defaultStat), 0);

        testStatistic.addAttempt();
        // same percentage but testStatistic has more attempts
        assertEquals(testStatistic.compareTo(defaultStat), -1);

        testStatistic.addAttempt();
        testStatistic.addCorrect();
        // testStatistic is 50% while default is 0%
        assertEquals(testStatistic.compareTo(defaultStat), 1);

        defaultStat.addCorrect();
        defaultStat.addAttempt();
        // default is 100%, test is 0%
        assertEquals(testStatistic.compareTo(defaultStat), -1);
    }
}
