package jfluentvalidation.validators.rulefor.calendar;

import java.util.Calendar;

public abstract class AbstractCalendarTest {

    protected final Calendar reference;
    protected final Calendar before;
    protected final Calendar after;

    AbstractCalendarTest() {
        reference = Calendar.getInstance();

        before = (Calendar) reference.clone();
        before.add(Calendar.DATE, -1);

        after = Calendar.getInstance();
        after.add(Calendar.DATE, 1);
    }

}
