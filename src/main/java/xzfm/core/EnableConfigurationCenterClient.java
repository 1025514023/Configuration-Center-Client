package xzfm.core;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;
import xzfm.core.cache.CacheConfigurationCenter;

import java.lang.annotation.*;

/**
 * Created by wangxizhong on 2017/5/8.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CacheConfigurationCenter.class)
public @interface EnableConfigurationCenterClient {

    boolean refresh() default true;//主动刷新

    int interval() default 3600;//second
}
