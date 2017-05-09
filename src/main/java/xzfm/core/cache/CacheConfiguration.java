package xzfm.core.cache;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class CacheConfiguration implements Serializable {
    private boolean refresh;
    private int interval;
    public static volatile boolean modifying = false;
    public static ConcurrentHashMap cacheConfiguration = new ConcurrentHashMap();

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
}
