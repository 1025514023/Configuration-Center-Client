package xzfm.core.domain.entity;

import xzfm.common.boot.annotation.HibernateDynamic;
import xzfm.common.domain.core.BaseEntity;
import xzfm.monitor.datasource.SpringMonitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wangxizhong on 2017/4/24.
 */
@Entity
@HibernateDynamic
@Table(name = "configuration_center")
public class ConfigurationCenter extends BaseEntity implements SpringMonitor {

    @Column
    private int ttl;

    @Column
    private String type;

    @Column
    private String status;

    @Column
    private String remark;

    @Column(name = "configuration_key")
    private String configurationKey;

    @Column(name = "configuration_value")
    private String configurationValue;


    public static final String STATUS_NORMAL = "normal";

    public static final String STATUS_DISABLE = "disable";

    public static final String TYPE_SERVER = "server";

    public static final String TYPE_WEB = "web";

    public static final String TYPE_WECHAT = "weChat";

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(String configurationKey) {
        this.configurationKey = configurationKey;
    }

    public String getConfigurationValue() {
        return configurationValue;
    }

    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ConfigurationCenter create(String configurationKey, String configurationValue, String type,
                                             String status, int ttl, String remark) {

        ConfigurationCenter configurationCenter = new ConfigurationCenter();
        configurationCenter.setTtl(ttl);
        configurationCenter.setType(type);
        configurationCenter.setStatus(status);
        configurationCenter.setConfigurationKey(configurationKey);
        configurationCenter.setConfigurationValue(configurationValue);
        session().persist(configurationCenter);
        return configurationCenter;
    }
}
