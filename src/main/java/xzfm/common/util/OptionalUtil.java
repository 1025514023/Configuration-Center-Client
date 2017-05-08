package xzfm.common.util;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by wangxizhong on 17/1/8.
 */
public class OptionalUtil {
    public static <T, R> Optional<R> stringConvert(Function<? extends T, ? extends R> mapper) {
        try {
            return Optional.of(mapper.apply(null));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public static Optional<Number> stringConvert(String source, Class<?> genericClass) {
        try {
            if (genericClass == Integer.class) {
                return Optional.of(Integer.parseInt(source));
            } else if (genericClass == Double.class) {
                return Optional.of(Double.parseDouble(source));
            } else if (genericClass == Long.class) {
                return Optional.of(Long.parseLong(source));
            }
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
