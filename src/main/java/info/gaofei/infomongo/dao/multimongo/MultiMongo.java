package info.gaofei.infomongo.dao.multimongo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MultiMongo {
    /**
     * 策略需要的附属信息,比如直接指定某个数据库
     */
    String specify() default "";

    /**
     * 策略需要的参数值的位置,-1代表非必须
     */
    int paramLocation() default -1;

    /**
     * 使用的策略
     *
     * @return 策略id
     */
    String strategy();
}
