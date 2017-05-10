package xzfm.core.handler.task;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import xzfm.core.handler.ConfigurationCenterProperties;
import xzfm.core.handler.monitor.CacheTTLMonitor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public class InitializationAutoTask implements InitializingBean {
    @Autowired
    private ConfigurationCenterProperties configurationCenterProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        autoTaskStartForIntervalFetch();
        autoTaskStartForCacheTTLFetch();
    }

    private void autoTaskStartForIntervalFetch() throws SchedulerException, ExecutionException, InterruptedException {
        if (configurationCenterProperties.isRefresh()) {
            new AutoTaskFetchCache().addAutoTask(ConfigurationCenterProperties.TASK_FETCH, configurationCenterProperties.getInterval());
        }
    }

    private void autoTaskStartForCacheTTLFetch() throws SchedulerException {
       // new AutoTaskUpdateCache().addAutoTask(ConfigurationCenterProperties.TASK_UPDATE, -1);
        FutureTask<Integer> future = new FutureTask<Integer>(new CacheTTLMonitor());
        new Thread(future).start();
    }
}
