package seedu.smartnus.model.tag;

import org.junit.jupiter.api.Test;
import seedu.smartnus.model.question.Statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;

public class TagStatisticTest {

    @Test
    public void isEqualTagStatistic() {
        Tag tag = new Tag("abc");
        Statistic stat = new Statistic();
        TagStatistic ts = new TagStatistic(tag, stat);
        TagStatistic ts2 = new TagStatistic(tag, stat);
        TagStatistic ts3 = new TagStatistic(new Tag("def"), stat);

        assertTrue(ts.equals(ts2));
        assertFalse(ts.equals(ts3));
    }

    @Test
    public void get_test() {
        Tag tag = new Tag("abc");
        Statistic stat = new Statistic();
        TagStatistic ts = new TagStatistic(tag, stat);

        assertEquals("abc", ts.getTitle());
        assertEquals(0, ts.getAttemptCount());
        assertEquals(0, ts.getCorrectCount());
        assertEquals(0, ts.getCorrectPercentage());
    }




}
