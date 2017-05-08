package xzfm.common.boot.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by wangxizhong on 16/12/20.
 */
@Data
public class PageResult<T> implements Serializable {
    private List<T> content;
    private int pageIndex;
    private int pageSize;
    private int pageCount;

    public PageResult(List<T> content, int pageIndex, int pageSize, int pageCount) {
        this.content = content;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }


    public <R> PageResult(List<R> content, int pageIndex, int pageSize, int pageCount, Function<? super R, ? extends T> mapper) {
        this.content =
                content.stream()
                        .map(mapper)
                        .collect(Collectors.toList());
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }


}
