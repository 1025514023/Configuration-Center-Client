package xzfm.core.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzfm.common.boot.exception.ASS;
import xzfm.common.domain.copier.BCC;
import xzfm.core.data.ConfigurationCenterDao;
import xzfm.core.domain.dto.ConfigurationCenterDto;
import xzfm.core.domain.entity.ConfigurationCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class FetchConfigurationCenter {
    @Autowired
    private ConfigurationCenterDao centerDao;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    private ReentrantLock reentrantLock = new ReentrantLock(true);

    public void fetchConfigurationToCache() {
        List<ConfigurationCenter> centerList = centerDao.findAll();
        if (centerList != null && centerList.size() > 0) {
            fetchCacheConfigurationToSafeMap(centerList);
        }
    }

    private void fetchCacheConfigurationToSafeMap(List<ConfigurationCenter> targetConfig) {
        List<ConfigurationCenterDto> centerDtoList = new ArrayList<>();
        reentrantLock.lock();
        for (ConfigurationCenter config : targetConfig) {
            centerDtoList.add(BCC.build(config, ConfigurationCenterDto.class));
        }
        cacheConfiguration.setCacheConfiguration(centerDtoList);
        reentrantLock.unlock();
    }

    public String getCacheConfigurationValue(String configurationKey) {
        ASS.validateStringNotEmpty(configurationKey, "configuration key not null!");

        return new FetchCache().getCacheConfigurationValue(configurationKey);
    }

    public String getCurrentConfigurationValue(String configurationKey) {
        ASS.validateStringNotEmpty(configurationKey, "configuration key not null!");

        return centerDao.findByConfigurationKey(configurationKey).getConfigurationValue();
    }

    private class FetchCache {
        private String getCacheConfigurationValue(String configurationKey) {
            if (cacheConfiguration.getCacheConfiguration() != null) {
                for (ConfigurationCenterDto centerDto : cacheConfiguration.getCacheConfiguration()) {
                    if (configurationKey.equals(centerDto.getConfigurationKey())) {
                        return centerDto.getConfigurationValue();
                    }
                }
            }
            return null;
        }
    }
}
