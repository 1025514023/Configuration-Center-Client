package xzfm.core.handler.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import xzfm.core.handler.CacheConfiguration;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Lazy
public class AutoTaskUpdateCache implements Job{
    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
