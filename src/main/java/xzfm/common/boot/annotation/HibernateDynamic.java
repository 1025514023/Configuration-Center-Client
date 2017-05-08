package xzfm.common.boot.annotation;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.lang.annotation.*;

/**
 * Created by wangxizhong on 16/12/17.
 */
@DynamicInsert
@DynamicUpdate
@Target(value = ElementType.TYPE)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HibernateDynamic {
}
