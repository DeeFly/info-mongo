package info.gaofei.infomongo.dao;

import info.gaofei.infomongo.aop.handler.DefaultDecisionHandler;
import info.gaofei.infomongo.customizedhandler.DateHandler;
import info.gaofei.infomongo.dao.multimongo.AbstractMultiMongoEntityDao;
import info.gaofei.infomongo.dao.multimongo.MultiMongo;
import info.gaofei.infomongo.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    //一般插入不需要指定分库规则，默认插入最新库，这里为了生成测试数据，顺便测试一下这个功能
    @MultiMongo(strategy = DateHandler.DATE_ARRANGED, paramLocation = 0)
    public String insertUserWhitDate(Date date, User user) {
        return insert(user);
    }

    @MultiMongo(strategy = DateHandler.DATE_ARRANGED, paramLocation = 0)
    public List<User> findUsersWithDate(Date date, Integer status) {
        Criteria criteria = Criteria.where("sta").is(1);
        Query query = Query.query(criteria);
        return find(query);
    }
}
