package info.gaofei.infomongo.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by GaoQingming on 2018/11/15 0015.
 */
public class Entity implements Comparable<Entity> {
    @Id
    @Field(IdKey.id)
    private String id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Entity other = (Entity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
            return EqualsBuilder.reflectionEquals(this, obj);
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public int compareTo(Entity other) {
        if(isTransient() && other.isTransient()) {
            int cmp = hashCode() - other.hashCode();
            return cmp == 0 ? 0 : cmp > 0 ? 1 : -1;
        }
        if(isTransient()) {
            return -1;
        }
        if(other.isTransient()) {
            return 1;
        }
        return id.compareTo(other.getId());
    }

    public boolean isTransient() {
        return id == null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
