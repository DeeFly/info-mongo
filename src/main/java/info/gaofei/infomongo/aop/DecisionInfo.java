package info.gaofei.infomongo.aop;

/**
 * 决定使用哪个数据库的各种信息
 * Created by GaoQingming on 2018/11/15 0015.
 */
public class DecisionInfo {
    /**
     * 用哪个策略的key值
     */
    private String strategy;
    /**
     * 策略需要的附属信息,比如直接指定某个数据库
     */
    private String specify;
    /**
     * 策略需要的参数值
     */
    private Object specifyValue;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getSpecify() {
        return specify;
    }

    public void setSpecify(String specify) {
        this.specify = specify;
    }

    public Object getSpecifyValue() {
        return specifyValue;
    }

    public void setSpecifyValue(Object specifyValue) {
        this.specifyValue = specifyValue;
    }
}
