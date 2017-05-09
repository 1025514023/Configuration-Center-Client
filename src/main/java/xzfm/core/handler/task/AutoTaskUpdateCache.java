package xzfm.core.handler.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import xzfm.core.handler.ConfigurationCenterProperties;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public class AutoTaskUpdateCache extends AbstractAutoTaskManager implements Job {
    @Autowired
    private ConfigurationCenterProperties configurationCenterProperties;

    private Log logger = LogFactory.getLog(getClass());


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }

    @Override
    protected JobDetail buildJobDetail() {
        return JobBuilder.newJob().ofType(getClass()).build();
    }
}
