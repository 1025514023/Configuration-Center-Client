package xzfm.common.util;

import xzfm.common.boot.exception.ASS;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Created by wangxizhong on 16/12/26.
 */
public class ExceptionUtil implements Serializable {

    public static <T, R> R getSingleResult(Class<R> convert, Supplier<T> supplier) {
        try {
            return (R) supplier.get();
        } catch (NoResultException e) {
            try {
                return convert.newInstance();
            } catch (InstantiationException e1) {
                ASS.validateFalse(false, "Instantiation:" + e1.getMessage());
                return null;
            } catch (IllegalAccessException e2) {
                ASS.validateFalse(false, "IllegalAccess:" + e2.getMessage());
                return null;
            }
        }

    }
}
