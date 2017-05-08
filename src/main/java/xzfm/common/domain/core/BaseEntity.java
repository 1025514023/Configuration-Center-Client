package xzfm.common.domain.core;


import xzfm.common.util.UUIDUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangxizhong on 2016/12/1.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Version
    @Column
    private int ver;

    @Column
    private Timestamp ctime;

    @Column
    private Timestamp utime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVer() {
        return ver;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public BaseEntity() {
        id = UUIDUtil.getUUIDToUpperCase();
    }

    protected static EntityManager session() {
        return new NativeQuery().getEntityManager();
    }
}
