package xzfm.common.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by wangxizhong on 16/12/27.
 */
public class ConllectorUtil<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {//收集器
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (present, next) -> {
            present.addAll(next);
            return present;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(
                EnumSet.of(Characteristics.CONCURRENT
                        , Characteristics.IDENTITY_FINISH));
    }
}
