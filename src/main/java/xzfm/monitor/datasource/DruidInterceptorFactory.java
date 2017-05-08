package xzfm.monitor.datasource;

import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by wangxizhong on 2017/4/19.
 */
@Configuration
public class DruidInterceptorFactory {

    @Bean(value = "druidStatInterceptor")
    public DruidStatInterceptor druidInterceptor() throws Throwable {
        DruidStatInterceptor druidInterceptorFactory = new DruidStatInterceptor();
        return druidInterceptorFactory;
    }

    @Bean
    @Scope(value = "prototype")
    public BeanTypeAutoProxyCreator jdkRegexpMethodPointcut() {
        BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
        beanTypeAutoProxyCreator.setTargetBeanType(SpringMonitor.class);
        beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanTypeAutoProxyCreator;
    }
}
