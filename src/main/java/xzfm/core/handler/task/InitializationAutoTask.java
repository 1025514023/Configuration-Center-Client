package xzfm.core.handler.task;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import xzfm.core.handler.CacheConfiguration;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public class InitializationAutoTask implements InitializingBean {
    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Override
    public void afterPropertiesSet() throws Exception {
        autoTaskStartForIntervalFetch();
    }

    private void autoTaskStartForIntervalFetch() throws SchedulerException {
        AutoTaskManager.addAutoTaskForFetch(CacheConfiguration.TASK_FETCH, cacheConfiguration.getInterval());
    }
}
