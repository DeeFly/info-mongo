package info.gaofei.infomongo.dao.multimongo;

import info.gaofei.infomongo.bean.Entity;
import info.gaofei.infomongo.dao.AbstractEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public abstract class AbstractMultiMongoEntityDao<E extends Entity> extends AbstractEntityDao<E> {
    @Autowired
    private MongoTemplateHolder templateHolder;

    @Override
    public MongoTemplate getMongoTemplate() {
        return templateHolder.getMongoTemplate();
    }

    public MongoTemplateHolder getTemplateHolder() {
        return templateHolder;
    }

    public void setTemplateHolder(MongoTemplateHolder templateHolder) {
        this.templateHolder = templateHolder;
    }
}
