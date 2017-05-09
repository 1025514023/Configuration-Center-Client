package xzfm.core.handler.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import xzfm.core.handler.FetchConfigurationCenter;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Lazy
public class AutoTaskFetchCache implements Job {
    @Autowired
    private FetchConfigurationCenter fetchConfigurationCenter;

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        fetchConfigurationCenter.fetchConfigurationToCache();
        logger.info("Fetch all configuration!");
    }
}
