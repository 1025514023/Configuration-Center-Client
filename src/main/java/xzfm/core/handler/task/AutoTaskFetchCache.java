package xzfm.core.handler.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import xzfm.core.handler.FetchConfigurationCenter;

/**
 * Created by wangxizhong on 2017/5/9.
 */
@Component
public class AutoTaskFetchCache extends AbstractAutoTaskManager implements Job, ApplicationContextAware {

    private static FetchConfigurationCenter fetchConfigurationCenter;

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        fetchConfigurationCenter.fetchConfigurationToCache();
        logger.info("Fetch all configuration!");
    }

    @Override
    protected JobDetail buildJobDetail() {
        return JobBuilder.newJob().ofType(getClass()).build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AutoTaskFetchCache.fetchConfigurationCenter = applicationContext.getBean(FetchConfigurationCenter.class);
    }
}
