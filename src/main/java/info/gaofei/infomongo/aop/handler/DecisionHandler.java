package info.gaofei.infomongo.aop.handler;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public interface DecisionHandler<T> {
    /**
     * 该处理器是否支持该参数的处理逻辑,支持,则后续调用handle方法.
     *
     * @param obj 传入的参数对象,比如DecisionInfo
     * @return 是否支持.
     */
    boolean support(T obj);

    /**
     * @param obj 数据
     * @return 数据库名字.
     */
    String handle(T obj);
}
