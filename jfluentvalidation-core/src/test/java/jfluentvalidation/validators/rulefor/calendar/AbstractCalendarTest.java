package jfluentvalidation.validators.rulefor.calendar;

import java.util.Calendar;

public abstract class AbstractCalendarTest {

    protected final Calendar ACTUAL;
    protected final Calendar BEFORE;
    protected final Calendar AFTER;

    AbstractCalendarTest() {
        ACTUAL = Calendar.getInstance();

        BEFORE = Calendar.getInstance();
        BEFORE.add(Calendar.DATE, -1);

        AFTER = Calendar.getInstance();
        AFTER.add(Calendar.DATE, 1);
    }

}
