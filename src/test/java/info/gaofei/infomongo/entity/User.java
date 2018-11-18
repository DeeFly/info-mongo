package info.gaofei.infomongo.entity;

import info.gaofei.infomongo.bean.Entity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by GaoQingming on 2018/11/16 0016.
 */
@Document(collection = "user")
public class User extends Entity {
    @Field("name")
    private String name;
    @Field("crtTm")
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
