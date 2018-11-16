package info.gaofei.infomongo.dao.multi;

import info.gaofei.infomongo.bean.Entity;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public interface EntityDao<E extends Entity> {

    /**
     * 通过查询对象查询文档集合，返回元素类型由clazz指定
     */
    <T> List<T> find(Query query, Class<T> clazz);

    /**
     * 新增文档到数据库的集合中
     */
    String insert(E entity);

    /**
     * 新增文档到数据库的集合中
     */
    void insert(Object objectToInsert);
}
