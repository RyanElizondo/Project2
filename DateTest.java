import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void thirtyDayMonth_hasMoreThanThirty() {
        Date date = new Date("4/31/2019");
        assertFalse(date.isValid());
    }
    @org.junit.Test

    public void thirtyOneDayMonth_hasMoreThanThirtyOne() {
        Date date = new Date("3/35/2019");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void febValidDays_inLeapYear() {
        Date date = new Date("2/29/2020");
        assertTrue(date.isValid());
    }

    @org.junit.Test
    public void febValidDays_notLeapYear() {
        Date date = new Date("2/29/2019");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void  monthToLarge() {
        Date date = new Date("13/29/2019");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void  monthToSmall() {
        Date date = new Date("0/29/2019");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void dateIsValid() {
        Date date = new Date("4/4/2020");
        assertTrue(date.isValid());
    }

}