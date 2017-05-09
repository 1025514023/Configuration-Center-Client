package xzfm.core.handler.task;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public class AutoTaskManager extends AbstractAutoTaskManager {

    public static void addAutoTaskForFetch(String taskName, int intervalSeconds) throws SchedulerException {
        addAutoTask(taskName, intervalSeconds);
    }

    public JobDetail buildJobDetail() {
        return JobBuilder.newJob().ofType(AutoTaskFetchCache.class).build();
    }
}
