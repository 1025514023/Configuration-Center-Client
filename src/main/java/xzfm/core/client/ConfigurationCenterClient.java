package xzfm.core.client;

/**
 * Created by wangxizhong on 2017/5/8.
 */
public interface ConfigurationCenterClient {
    String getCacheConfigurationValue(String configurationKey);

    String getCurrentConfigurationValue(String configurationKey);

    void refreshCache();
}
