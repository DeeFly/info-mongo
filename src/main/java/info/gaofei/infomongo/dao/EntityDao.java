package info.gaofei.infomongo.dao;

import info.gaofei.infomongo.bean.Entity;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
     * 根据查询对象删除文档
     */
    void delete(Query query);

    /**
     * 更新文档
     */
    void updateMulti(Query query, Update update);
}
