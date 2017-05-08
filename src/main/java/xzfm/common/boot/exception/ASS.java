package xzfm.common.boot.exception;

import xzfm.common.util.StringUtil;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wangxizhong on 2016/10/27.
 * Custom Exception Class
 */
public class ASS {
    public static void validateFalse(boolean expression, String message) {
        if (!expression) {
            throw new AssertException(message);
        }
    }

    public static void validateClassNotNull(Object object, String message) {
        validateFalse(object != null, message);
    }

    public static void validateStringNotEmpty(String str, String message) {
        validateFalse(!StringUtil.validateEmptyOrNull(str), message);
    }

    public static void validateCollectionNotNull(Collection collection, String message) {
        validateFalse(collection != null && collection.size() > 0 && !collection.isEmpty(), message);
    }

    public static void validateMapNotNull(Map<Object, Object> map, String message) {
        validateFalse(map != null && map.size() > 0 && !map.isEmpty(), message);
    }

    public static void requireEquals(Object fir, Object sec, String message) {
        validateFalse(fir.equals(sec), message);
    }
}
