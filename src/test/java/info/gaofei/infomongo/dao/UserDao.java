package info.gaofei.infomongo.dao;

import info.gaofei.infomongo.aop.handler.DefaultDecisionHandler;
import info.gaofei.infomongo.dao.multimongo.AbstractMultiMongoEntityDao;
import info.gaofei.infomongo.dao.multimongo.MultiMongo;
import info.gaofei.infomongo.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GaoQingming on 2018/11/19 0019.
 */
@Repository
public class UserDao extends AbstractMultiMongoEntityDao<User> {
    public void insertUser(User user) {
        insert(user);
    }

    @MultiMongo(strategy = DefaultDecisionHandler.SPECIFIED, specify = "mongoMain")
    public List<User> findUsers(Integer status) {
        Criteria criteria = Criteria.where("sta").is(1);
        Query query = Query.query(criteria);
        return find(query);
    }
}
