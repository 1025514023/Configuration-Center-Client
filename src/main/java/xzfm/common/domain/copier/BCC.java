package xzfm.common.domain.copier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxizhong on 2016/11/1.
 */
public class BCC<T extends Serializable> {
    private static Map<Class<?>, Map<Class<?>, SimpleBeanCopier>> copierMap = new HashMap<>();
    private static Map<Class<?>, String> lockKeyMap = new HashMap<>();
    private static final Object lock = new Object();

    public static <T> T build(Object object, Class<T> T) {
        Class<?> sourceClass = object.getClass();

        if (!copierMap.containsKey(sourceClass)) {
            synchronized (getLockKey(sourceClass)) {
                if (!copierMap.containsKey(sourceClass)) {
                    copierMap.put(sourceClass, new HashMap<>());
                }
            }
        }

        if (!copierMap.get(sourceClass).containsKey(T)) {
            synchronized (getLockKey(T)) {
                if (!copierMap.get(sourceClass).containsKey(T)) {
                    SimpleBeanCopier simpleBeanCopier = new SimpleBeanCopier(object.getClass(), T);
                    copierMap.get(sourceClass).put(T, simpleBeanCopier);
                }
            }
        }

        return (T) copierMap.get(sourceClass).get(T).copy(object);
    }

    private static String getLockKey(Class<?> clazz) {
        if (!lockKeyMap.containsKey(clazz)) {
            synchronized (lock) {
                if (!lockKeyMap.containsKey(clazz)) {
                    lockKeyMap.put(clazz, clazz.getName() + "_lock");
                }
            }
        }
        return lockKeyMap.get(clazz);
    }
}
