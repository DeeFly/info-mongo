package info.gaofei.infomongo.dao.multi;

import info.gaofei.infomongo.bean.Entity;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public abstract class AbstractShardingEntityDao<E extends Entity> implements EntityDao<E> {
    /**
     * The maximum shard count, used if a higher value is implicitly specified.
     * MUST be a power of two <= 1<<10.
     */
    private static final int MAXIMUM_CAPACITY = 1 << 10;
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
    /**
     * 分表Field
     */
    private Field shardingField;
    /**
     * 分表列名
     */
    private String shardingColumn;
    /**
     * 分表数
     */
    private int shardingCount;
    @Autowired
    private MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractShardingEntityDao() {
        String preferedCollectionName = MongoCollectionUtils.getPreferredCollectionName(classType);
        if (classType.isAnnotationPresent(Document.class)) {
            Document document = classType.getAnnotation(Document.class);
            collectionName = document.collection();
            collectionName = StringUtils.isEmpty(collectionName) ? preferedCollectionName : collectionName;
        }

        List<Field> fieldsWithShardingKey = FieldUtils.getFieldsListWithAnnotation(classType, ShardingKey.class);
        Assert.isTrue(fieldsWithShardingKey.size() == 1, "only one field could be annotated with ShardingKey annotation but find multi !");
        this.shardingField = fieldsWithShardingKey.get(0);
        ShardingKey shardingKey = shardingField.getAnnotation(ShardingKey.class);
        this.shardingColumn = shardingKey.value();
        if (StringUtils.isEmpty(shardingColumn)) {
            throw new IllegalArgumentException("ShardingKey should have a specific shardingColumn !");
        }
        int shardingCount = shardingKey.shardingCount();
        this.shardingCount = shardingCountFor(shardingCount);
    }

    private int shardingCountFor(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Illegal initial shard count : " + i);
        if (i > MAXIMUM_CAPACITY)
            i = MAXIMUM_CAPACITY;
        return tableSizeFor(i);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        //减一的目的在于如果cap本身就是2的次幂，保证结果是原值，不减一的话，结果就成了cap * 2
        int n = cap - 1;
        //从最高位的1往低位复制
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        //到这里，从最高位的1到第0位都是1了，再加上1就是2的次幂
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        int result = tableSizeFor(5);
        System.out.println(result);
    }
}
