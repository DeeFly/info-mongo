package info.gaofei.infomongo.aop.handler;

import info.gaofei.infomongo.aop.DecisionInfo;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public class DefaultDecisionHandler<T extends DecisionInfo> implements DecisionHandler<T> {
    public static final String SPECIFIED = "SPECIFIED";

    /**
     * 该处理器是否支持该参数的处理逻辑,支持,则后续调用handle方法.
     *
     * @param decisionInfo 传入的参数对象,比如DecisionInfo
     * @return 是否支持.
     */
    @Override
    public boolean support(T decisionInfo) {
        if (decisionInfo.getStrategy().equals(SPECIFIED)) {
            return true;
        }
        return false;
    }

    /**
     * @param decisionInfo 数据
     * @return 数据库名字.
     */
    @Override
    public String handle(T decisionInfo) {
        return decisionInfo.getSpecify();
    }
}
