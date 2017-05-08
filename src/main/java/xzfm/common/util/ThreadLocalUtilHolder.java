package xzfm.common.util;

/**
 * Created by wangxizhong on 2016/12/6.
 */
public abstract class ThreadLocalUtilHolder<T> {
    private ThreadLocal<T> threadLocal = new ThreadLocal<T>();

    public T get() {
        T instance = threadLocal.get();
        if (instance == null) {
            instance = newInstance();
            threadLocal.set(instance);
        }
        return instance;

    }

    protected abstract T newInstance();
}
