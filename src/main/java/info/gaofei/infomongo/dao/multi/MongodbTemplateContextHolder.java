package info.gaofei.infomongo.dao.multi;

import org.springframework.util.StringUtils;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public class MongodbTemplateContextHolder {
    private static ThreadLocal<String> mongoTemplateTypeHolder = new ThreadLocal<>();

    /**
     * 设置线程级的mongoTemplate的名称
     * @param type
     */
    public static void setMongoTemplateType(String type) {
        if (StringUtils.isEmpty(type)) {
            throw new NullPointerException("mongoTemplateType shouldn't be empty !");
        }
        mongoTemplateTypeHolder.set(type);
    }

    public static String getMongoTemplateType() {
        //TODO 这里是不是可以搞一个默认的？
        return mongoTemplateTypeHolder.get();
    }

    public static void clearMongoTemplateType() {
        mongoTemplateTypeHolder.remove();
    }

}
