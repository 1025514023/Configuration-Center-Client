package xzfm.core.handler.task;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import xzfm.core.handler.ConfigurationCenterProperties;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public class InitializationAutoTask implements InitializingBean {
    @Autowired
    private ConfigurationCenterProperties configurationCenterProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        autoTaskStartForIntervalFetch();
    }

    private void autoTaskStartForIntervalFetch() throws SchedulerException {
        new AutoTaskFetchCache().addAutoTask(ConfigurationCenterProperties.TASK_FETCH, configurationCenterProperties.getInterval());
    }
}
