package xzfm.core.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xzfm.core.domain.dto.ConfigurationCenterDto;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class ConfigurationCenterProperties implements Serializable {
    @Value("${task.interval:86400}")
    private int interval;

    @Value("${task.refresh:true}")
    private boolean refresh;

    private volatile boolean modifying = false;

    private List<ConfigurationCenterDto> cacheConfiguration = new CopyOnWriteArrayList<>();

    private ConcurrentMap cacheTTLMap = new ConcurrentHashMap();

    public static final String TASK_UPDATE = "task_update";

    public static final String TASK_FETCH = "task_fetch";

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isModifying() {
        return modifying;
    }

    public void setModifying(boolean modifying) {
        this.modifying = modifying;
    }

    public ConcurrentMap<String,CacheTTL> getCacheTTLMap() {
        return cacheTTLMap;
    }

    public void setCacheTTLMap(ConcurrentMap cacheTTLMap) {
        this.cacheTTLMap = cacheTTLMap;
    }

    public List<ConfigurationCenterDto> getCacheConfiguration() {
        return cacheConfiguration;
    }

    public void setCacheConfiguration(List<ConfigurationCenterDto> cacheConfiguration) {
        this.cacheConfiguration = cacheConfiguration;
    }

    public void markModifying() {
        synchronized (this) {
            refresh = true;
        }
    }

    public void markModifyed() {
        synchronized (this) {
            refresh = false;
        }
    }


    public static class CacheTTL {
        private String configurationValue;
        private int ttl;

        public String getConfigurationValue() {
            return configurationValue;
        }

        public void setConfigurationValue(String configurationValue) {
            this.configurationValue = configurationValue;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }

        public CacheTTL(String configurationValue, int ttl) {
            this.configurationValue = configurationValue;
            this.ttl = ttl;
        }
    }
}
