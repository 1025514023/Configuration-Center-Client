package xzfm.core.handler.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzfm.core.data.ConfigurationCenterDao;
import xzfm.core.domain.entity.ConfigurationCenter;
import xzfm.core.handler.FetchConfigurationCenter;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxizhong on 2017/5/8.
 */
public class InitializationConfigurationCenterToCache implements Serializable, InitializingBean {
    @Autowired
    private FetchConfigurationCenter fetchConfigurationCenter;

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Initialization handler configuration executing!");
        fetchConfigurationCenter.fetchConfigurationToCache();
        logger.info("Initialization handler configuration executed!");
    }
}
