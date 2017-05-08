package xzfm.common.boot.page;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

/**
 * Created by wangxizhong on 16/12/20.
 */
public class PageResultBuilder {

    public static <T> PageResult<T> build(List<T> content, int pageIndex, int pageSize, int pageCount) {
        return new PageResult<>(
                content,
                pageIndex,
                pageSize,
                pageCount
        );
    }

    public static <T, R> PageResult<T> build(Page page, Function<? super R, ? extends T> result) {
        return new PageResult<T>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                result
        );

    }
}
