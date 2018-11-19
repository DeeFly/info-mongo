package info.gaofei.infomongo.dao;

import info.gaofei.infomongo.bean.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public abstract class AbstractEntityDao<E extends Entity> implements EntityDao<E> {
    /**
     * 实体类类型
     */
    private ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
    /**
     * 实体类Class
     */
    private Class<E> classType = (Class<E>) parameterizedType.getActualTypeArguments()[0];
    /**
     * 集合名称
     */
    private String collectionName;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractEntityDao() {
        Document document = classType.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("If " + classType.getName() + " is an mongo entity , Document annotation is required !");
        }
        this.collectionName = document.collection();
        if (StringUtils.isEmpty(collectionName)) {
            logger.warn("{} is annotated with Document annotation, but haven't configure the correspond collectionName", classType.getName());
            String preferredCollectionName = MongoCollectionUtils.getPreferredCollectionName(classType);
            this.collectionName = preferredCollectionName;
        }

    }

    protected abstract MongoTemplate getMongoTemplate();

    protected String getCollectionName() {
        return this.collectionName;
    }

    protected Class<E> getClassType() {
        return this.classType;
    }

    public List<E> find(Query query) {
        return find(query, classType);
    }

    @Override
    public <T> List<T> find(Query query, Class<T> clazz) {
        logger.debug("{}", query);
        return getMongoTemplate().find(query, clazz, getCollectionName());
    }

    @Override
    public String insert(E entity) {
        getMongoTemplate().insert(entity, getCollectionName());
        return entity.getId();
    }

    @Override
    public void delete(Query query) {

    }

    @Override
    public void updateMulti(Query query, Update update) {

    }
}
