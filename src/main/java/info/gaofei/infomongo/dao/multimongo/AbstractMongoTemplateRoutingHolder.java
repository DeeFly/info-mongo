package info.gaofei.infomongo.dao.multimongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public abstract class AbstractMongoTemplateRoutingHolder implements InitializingBean{
    private Map<String, MongoTemplate> mongoTemplates;
    private MongoTemplate defaultMongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mongoTemplates == null) {
            throw new RuntimeException("mongoTemplates shouldn't be empty !");
        }
        if (defaultMongoTemplate == null) {
            throw new RuntimeException("haven't configure default mongoTemplate!");
        }
    }

    public MongoTemplate getDefaultMongoTempate() {
         return this.defaultMongoTemplate;
    }

    public MongoTemplate getMongoTemplateByKey(String key) {
        logger.info("test info : get mongo template by key :{}" , key);
        MongoTemplate result = mongoTemplates.get(key);
        if (result == null) {
            logger.info("couldn't find mongoTemplate by specified key :{}, default template will be used." + key);
            result = defaultMongoTemplate;
        }
        return result;
    }

    public void setMongoTemplates(Map<String, MongoTemplate> mongoTemplates) {
        this.mongoTemplates = mongoTemplates;
    }

    public void setDefaultMongoTempate(MongoTemplate defaultMongoTempate) {
        this.defaultMongoTemplate = defaultMongoTempate;
    }
}
