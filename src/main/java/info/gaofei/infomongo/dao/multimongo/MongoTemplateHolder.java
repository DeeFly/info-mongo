package info.gaofei.infomongo.dao.multimongo;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public class MongoTemplateHolder extends AbstractMongoTemplateRoutingHolder {
    public MongoTemplate getMongoTemplate() {
        return getMongoTemplateByKey(MongodbTemplateContextHolder.getMongoTemplateType());
    }
}
