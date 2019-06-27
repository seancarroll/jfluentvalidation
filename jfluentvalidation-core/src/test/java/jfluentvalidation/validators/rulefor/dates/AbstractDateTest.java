package jfluentvalidation.validators.rulefor.dates;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractDateTest {

    protected final Date ACTUAL;
    protected final Date BEFORE;
    protected final Date AFTER;

    AbstractDateTest() {
        ACTUAL = Calendar.getInstance().getTime();

        Calendar beforeCal = Calendar.getInstance();
        beforeCal.add(Calendar.DATE, -1);
        BEFORE = beforeCal.getTime();

        Calendar afterCal = Calendar.getInstance();
        afterCal.add(Calendar.DATE, 1);
        AFTER = afterCal.getTime();
    }


}
