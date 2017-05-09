package xzfm.core.handler.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by wangxizhong on 2017/5/9.
 */
public abstract class AbstractAutoTaskManager {
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public static void addAutoTask(String taskName, int intervalSeconds) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail taskDetail = buildJobDetail();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(taskName).
                withSchedule(
                        CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(intervalSeconds)
                ).startNow().build();
        scheduler.scheduleJob(taskDetail, trigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    protected JobDetail buildJobDetail() {
        return null;
    }
}
