package xzfm.common.util;

/**
 * Created by wangxizhong on 2016/10/27.
 */
public class StringUtil {
    public static boolean validateEmptyOrNull(String inputString) {
        if (inputString == null || inputString.isEmpty() || inputString.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    public static String firstStrToLowerCase(String source) {
        return source.substring(0, 1).toLowerCase() + source.substring(1);
    }

    public static String firstStrToUpperCase(String source) {
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }
}
