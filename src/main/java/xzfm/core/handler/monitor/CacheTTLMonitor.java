package xzfm.core.handler.monitor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import xzfm.common.util.TimeUtil;
import xzfm.core.domain.dto.ConfigurationCenterDto;
import xzfm.core.handler.ConfigurationCenterProperties;
import xzfm.core.handler.FetchConfigurationCenter;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxizhong on 17/5/10.
 */
@Component
public class CacheTTLMonitor implements Callable, ApplicationContextAware {

    private static ConfigurationCenterProperties centerProperties;

    private static FetchConfigurationCenter fetchConfigurationCenter;

    private ReentrantLock reentrantLock = new ReentrantLock(false);

    private final static long WAIT_ITERATOR_TIME_EMPTY = 60000;

    private final static long WAIT_ITERATOR_TIME_REDUCE = 1000;

    @Override
    public Object call() throws Exception {
        iteratorEmptyCacheTTLMap();// empty iterator map
        iteratorCacheTTLMap();// handler cache map

        return threadExitMessage();
    }

    private void iteratorEmptyCacheTTLMap() throws InterruptedException {
        while (centerProperties.getCacheTTLMap().isEmpty()) {
            System.out.println("------empty");
            Thread.sleep(WAIT_ITERATOR_TIME_EMPTY);
        }
    }

    private void iteratorCacheTTLMap() throws InterruptedException {
        while (!centerProperties.getCacheTTLMap().isEmpty()) {
            System.out.println("--------ttl");
            Thread.sleep(WAIT_ITERATOR_TIME_REDUCE);
            cacheTTLReduce();//reduce

        }
    }

    private void cacheTTLReduce() {
        for (Object key : centerProperties.getCacheTTLMap().keySet()) {
            reentrantLock.lock();//需要原子性操作
            ConfigurationCenterProperties.CacheTTL cache = centerProperties.getCacheTTLMap().get(key);
            cache.setTtl(cache.getTtl() - 1);//TTL减少1/S

            if (cache.getTtl() <= 0) {//TTL失效   重新获取最新配置
                ConfigurationCenterDto centerDto = fetchConfigurationCenter.getCurrentConfigurationValueForTTL(key.toString());
                centerProperties.getCacheTTLMap().replace(
                        (String) key,
                        new ConfigurationCenterProperties.CacheTTL(centerDto.getConfigurationValue(), centerDto.getTtl()
                        )
                );
                centerProperties.getCacheTTLMap().replace((String) key, cache);
            }

            reentrantLock.unlock();
        }
    }

    private String threadExitMessage() {
        return "cache executed! time:" + TimeUtil.currentTimestamp();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CacheTTLMonitor.fetchConfigurationCenter = applicationContext.getBean(FetchConfigurationCenter.class);
        CacheTTLMonitor.centerProperties = applicationContext.getBean(ConfigurationCenterProperties.class);
    }
}
