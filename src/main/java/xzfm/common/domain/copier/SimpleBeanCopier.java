package xzfm.common.domain.copier;


import org.springframework.cglib.beans.BeanCopier;
import xzfm.common.domain.core.BaseDao;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Created by wangxizhong on 2016/11/1.
 */
public class SimpleBeanCopier<R extends BaseDao, T extends Serializable> implements Function<R, T> {
    private Class<T> targetClass;
    private Class<R> sourceClass;
    private BeanCopier beanCopier;

    public SimpleBeanCopier(Class<R> sourceClass, Class<T> targetClass) {
        this.targetClass = targetClass;
        this.sourceClass = sourceClass;
        beanCopier = BeanCopier.create(sourceClass, targetClass, false);
    }

    @Override
    public T apply(R r) {
        return copy(r);
    }

    public T copy(Object object) {
        try {
            T targetClassNewInstance = targetClass.newInstance();
            beanCopier.copy(object, targetClassNewInstance, null);
            return targetClassNewInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <V> Function<V, T> compose(Function<? super V, ? extends R> before) {
        return null;
    }

    @Override
    public <V> Function<R, V> andThen(Function<? super T, ? extends V> after) {
        return null;
    }
}
