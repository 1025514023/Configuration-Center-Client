package xzfm.core.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzfm.core.data.ConfigurationCenterDao;
import xzfm.core.domain.entity.ConfigurationCenter;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxizhong on 2017/5/8.
 */
@Component
public class CacheConfigurationCenter implements Serializable, InitializingBean {
    @Autowired
    private ConfigurationCenterDao centerDao;

    private volatile boolean modifying = false;

    private Log logger = LogFactory.getLog(getClass());

    ConcurrentHashMap cacheConfiguration = new ConcurrentHashMap();


    @Override
    public void afterPropertiesSet() throws Exception {
        initializationCacheConfiguration();
    }

    private void initializationCacheConfiguration() {
        List<ConfigurationCenter> centerList = centerDao.findAll();
        if (centerList != null && centerList.size() > 0) {
            initializationCacheConfigurationToSafeMap(centerList);
        }
    }

    private void initializationCacheConfigurationToSafeMap(List<ConfigurationCenter> targetConfig) {
        synchronized (this) {
            for (ConfigurationCenter config : targetConfig) {
                cacheConfiguration.put(config.getConfigurationKey(), config.getConfigurationValue());
            }
        }
    }
}
