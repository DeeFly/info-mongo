package info.gaofei.infomongo.tests;

import info.gaofei.infomongo.dao.UserDao;
import info.gaofei.infomongo.entity.User;
import info.gaofei.infomongo.testBase.MongoTestBase;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by GaoQingming on 2018/11/19 0019.
 */
public class SimpleMultiMongoTest extends MongoTestBase {
    @Resource
    private UserDao userDao;

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setCreateTime(new Date());
        user.setName("gaofei");
        userDao.insert(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findUsersTest() {
        List<User> users = userDao.findUsers(1);
        Assert.assertNotNull(users);
        users.forEach(System.out::println);
    }
}
