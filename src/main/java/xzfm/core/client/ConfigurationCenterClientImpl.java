package xzfm.core.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzfm.common.boot.exception.ASS;
import xzfm.core.handler.FetchConfigurationCenter;

/**
 * Created by wangxizhong on 2017/5/8.
 */
@Service
public class ConfigurationCenterClientImpl implements ConfigurationCenterClient {
    @Autowired
    private FetchConfigurationCenter fetchConfiguration;

    @Override
    public String getCacheConfigurationValue(String configurationKey) {

        return fetchConfiguration.getCacheConfigurationValue(configurationKey);
    }

    @Override
    public String getCurrentConfigurationValue(String configurationKey) {

        return fetchConfiguration.getCurrentConfigurationValue(configurationKey);
    }

    @Override
    public void refreshCache() {

        fetchConfiguration.fetchConfigurationToCache();
    }
}
