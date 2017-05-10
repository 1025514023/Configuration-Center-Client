package xzfm.core;

import org.springframework.context.annotation.Import;
import xzfm.core.handler.task.InitializationAutoTask;

import java.lang.annotation.*;

/**
 * Created by wangxizhong on 2017/5/8.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({InitializationAutoTask.class})
public @interface EnableConfigurationCenterClient {

}
