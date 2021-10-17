package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatisticTest {

    @Test
    public void equals_test() {
        Statistic defaultStat = new Statistic();
        Statistic tenAttempts = new Statistic(10, 5);

        assertEquals(0, defaultStat.getAttemptCount());
        assertEquals(0, defaultStat.getCorrectCount());
        assertEquals(0.0, defaultStat.getCorrectPercentage());

        assertEquals(10, tenAttempts.getAttemptCount());
        assertEquals(5, tenAttempts.getCorrectCount());
        assertEquals(50.0, tenAttempts.getCorrectPercentage());
    }
}
