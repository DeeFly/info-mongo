package info.gaofei.infomongo.tests;

import info.gaofei.infomongo.dao.UserDao;
import info.gaofei.infomongo.entity.User;
import info.gaofei.infomongo.testBase.MongoTestBase;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by GaoQingming on 2018/11/19 0019.
 */
public class DateBasedMultiMongoTest extends MongoTestBase {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Resource
    private UserDao userDao;

    @Test
    public void insertUserTest() {
        User user = new User();
        Date date = null;
        try {
            date = simpleDateFormat.parse("2015-10-1 10:20:23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setCreateTime(date);
        user.setName("gaofei");
        userDao.insertUserWhitDate(date, user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findUsersTest() {
        Date date = null;
        try {
            date = simpleDateFormat.parse("2015-10-1 10:20:23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<User> users = userDao.findUsersWithDate(date, 1);
        Assert.assertNotNull(users);
        users.forEach(System.out::println);
    }
}
