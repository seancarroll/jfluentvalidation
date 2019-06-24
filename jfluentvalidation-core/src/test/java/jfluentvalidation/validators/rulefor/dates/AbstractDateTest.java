package jfluentvalidation.validators.rulefor.dates;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractDateTest {

    protected static final Date ACTUAL;
    protected static final Date BEFORE;
    protected static final Date AFTER;

    static {
        ACTUAL = Calendar.getInstance().getTime();

        Calendar beforeCal = Calendar.getInstance();
        beforeCal.add(Calendar.DATE, -1);
        BEFORE = beforeCal.getTime();

        Calendar afterCal = Calendar.getInstance();
        afterCal.add(Calendar.DATE, 1);
        AFTER = Calendar.getInstance().getTime();
    }


}
