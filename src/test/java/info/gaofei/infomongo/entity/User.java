package info.gaofei.infomongo.entity;

import info.gaofei.infomongo.bean.Entity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by GaoQingming on 2018/11/16 0016.
 */
@Document(collection = "user")
public class User extends Entity {
    @Field("name")
    private String name;
    @Field("sta")
    private Integer status = 1;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
