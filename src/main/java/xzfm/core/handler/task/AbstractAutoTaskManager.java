package xzfm.core.handler.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xzfm.core.handler.FetchConfigurationCenter;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public abstract class AbstractAutoTaskManager {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public void addAutoTask(String taskName, int intervalSeconds) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail taskDetail = buildJobDetail();
        Trigger trigger = TriggerBuilder.newTrigger().
                withSchedule(
                        CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(intervalSeconds)
                ).startNow().build();
        scheduler.scheduleJob(taskDetail, trigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    protected abstract JobDetail buildJobDetail();
}
