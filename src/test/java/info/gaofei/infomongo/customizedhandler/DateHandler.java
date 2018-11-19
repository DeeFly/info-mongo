package info.gaofei.infomongo.customizedhandler;

import info.gaofei.infomongo.aop.DecisionInfo;
import info.gaofei.infomongo.aop.handler.DecisionHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by GaoQingming on 2018/11/19 0019.
 */
public class DateHandler implements DecisionHandler<DecisionInfo> {
    public static final String DATE_ARRANGED = "DATE";
    private final Date DATE_2017;
    private final Date DATE_2016;
    private final Date DATE_2015;
    public DateHandler() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 00, 00, 00, 00, 00);
        DATE_2015 = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        DATE_2016 = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        DATE_2017 = calendar.getTime();
    }
    @Override
    public boolean support(DecisionInfo obj) {
        if (Objects.equals(DATE_ARRANGED, obj.getStrategy())) {
            return true;
        }
        return false;
    }

    @Override
    public String handle(DecisionInfo decisionInfo) {
        if (decisionInfo.getSpecifyValue() instanceof Date) {
            Date date = (Date) decisionInfo.getSpecifyValue();
            if (date.after(DATE_2017)) {
                return "mongoMain";
            } else if (date.after(DATE_2016)) {
                return "mongo2016";
            } else if (date.after(DATE_2015)) {
                return "mongo2015";
            }
        }
        return null;
    }
}
