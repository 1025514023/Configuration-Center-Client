package xzfm.monitor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import xzfm.monitor.datasource.DataSourceFactory;
import xzfm.monitor.datasource.DruidInterceptorFactory;
import xzfm.monitor.datasource.DruidProperties;

import java.lang.annotation.*;

/**
 * Created by wangxizhong on 2017/4/19.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({DataSourceFactory.class, DruidInterceptorFactory.class})
@EnableConfigurationProperties(DruidProperties.class)
public @interface EnableDataMonitor {
}
