package info.gaofei.infomongo.dao.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.LinkedHashMap;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public abstract class AbstractMongoTemplateRoutingHolder implements InitializingBean{
    private LinkedHashMap<String, MongoTemplate> mongoTemplates;
    private MongoTemplate defaultMongoTempate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mongoTemplates == null) {
            throw new RuntimeException("mongoTemplates shouldn't be empty !");
        }
        if (defaultMongoTempate == null) {
            defaultMongoTempate = mongoTemplates.values().iterator().next();
            logger.warn("haven't configure default mongoTemplate , use first element of mongoTemplates as default !");
        }
    }

    public MongoTemplate getDefaultMongoTempate() {
         return this.defaultMongoTempate;
    }

    public MongoTemplate getMongoTemplateByKey(String key) {
        MongoTemplate result = mongoTemplates.get(key);
        if (result == null) {
            throw new RuntimeException("couldn't find mongoTemplate by specified key : " + key);
        }
        return result;
    }

    public LinkedHashMap<String, MongoTemplate> getMongoTemplates() {
        return mongoTemplates;
    }

    public void setMongoTemplates(LinkedHashMap<String, MongoTemplate> mongoTemplates) {
        this.mongoTemplates = mongoTemplates;
    }

    public void setDefaultMongoTempate(MongoTemplate defaultMongoTempate) {
        this.defaultMongoTempate = defaultMongoTempate;
    }
}
