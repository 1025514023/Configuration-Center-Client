package xzfm.core.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xzfm.core.domain.dto.ConfigurationCenterDto;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class ConfigurationCenterProperties implements Serializable {
    @Value("${task.interval}")
    private int interval;

    @Value("${task.refresh}")
    private boolean refresh;

    private volatile boolean modifying = false;

    private List<ConfigurationCenterDto> cacheConfiguration = new CopyOnWriteArrayList<>();

    public static final String TASK_UPDATE = "task_update";

    public static final String TASK_FETCH = "task_fetch";

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public int getInterval() {
        if (interval <= 0) interval = 86400;
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
}
