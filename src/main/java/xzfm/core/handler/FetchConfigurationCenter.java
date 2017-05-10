package xzfm.core.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzfm.common.boot.exception.ASS;
import xzfm.common.domain.copier.BCC;
import xzfm.core.EnableConfigurationCenterClient;
import xzfm.core.data.ConfigurationCenterDao;
import xzfm.core.domain.dto.ConfigurationCenterDto;
import xzfm.core.domain.entity.ConfigurationCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class FetchConfigurationCenter {
    @Autowired
    private ConfigurationCenterDao centerDao;

    @Autowired
    private ConfigurationCenterProperties configurationCenterProperties;

    private ReentrantLock reentrantLock = new ReentrantLock(false);

    public void fetchConfigurationToCache() {
        List<ConfigurationCenter> centerList = centerDao.findAll();
        if (centerList != null && centerList.size() > 0) {
            fetchCacheConfigurationToSafeList(centerList);
        }
    }

    private void fetchCacheConfigurationToSafeList(List<ConfigurationCenter> targetConfig) {
        List<ConfigurationCenterDto> centerDtoList = new ArrayList<>();
        reentrantLock.lock();
        for (ConfigurationCenter config : targetConfig) {
            centerDtoList.add(BCC.build(config, ConfigurationCenterDto.class));
        }
        configurationCenterProperties.setCacheConfiguration(centerDtoList);
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

    public ConfigurationCenterDto getCurrentConfigurationValueForTTL(String configurationKey) {
        ASS.validateStringNotEmpty(configurationKey, "configuration key not null!");

        ConfigurationCenter center = centerDao.findByConfigurationKey(configurationKey);
        return BCC.build(center, ConfigurationCenterDto.class);
    }

    private class FetchCache {

        private String getCacheConfigurationValue(String configurationKey) {
            if (configurationCenterProperties.getCacheConfiguration() != null) {

                for (ConfigurationCenterDto centerDto : configurationCenterProperties.getCacheConfiguration()) {

                    //fetch cache
                    if (configurationCenterProperties.getCacheTTLMap().get(configurationKey) != null) {

                        return configurationCenterProperties.getCacheTTLMap().get(configurationKey).getConfigurationValue();
                    }

                    //如果没有二级缓存，则取List中程序初始化缓存，然后添加需要二级缓存配置
                    if (configurationKey.equals(centerDto.getConfigurationKey())) {

                        reentrantLock.lock();

                        cacheFetchConfigurationTTL(
                                centerDto.getConfigurationKey(),
                                centerDto.getConfigurationValue(),
                                centerDto.getTtl()
                        );//cache
                        reentrantLock.unlock();

                        return centerDto.getConfigurationValue();
                    }

                }
            }
            return null;
        }

        // 添加二级缓存配置
        private void cacheFetchConfigurationTTL(String configurationKey, String configurationValue, int ttl) {

            configurationCenterProperties.getCacheTTLMap().put(
                    configurationKey,
                    new ConfigurationCenterProperties.CacheTTL(configurationValue, ttl)
            );
        }
    }
}
