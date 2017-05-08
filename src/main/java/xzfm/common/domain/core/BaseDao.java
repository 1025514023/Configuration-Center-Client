package xzfm.common.domain.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxizhong on 2016/11/4.
 */

/**
 * 命名可以为read find get count
 * Distince开头不包含重复记录
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends Repository<T, ID> {
    T save(T entity);

    List<T> findAll();

    List<T> save(Iterable<? extends T> iterableEntity);

    void flush();

    T saveAndFlush(T entity);

    void deleteInBatch(Iterable<T> iterableEntity);

    T findOne(ID primaryKey);

    long count();

    boolean exists(ID primaryKey);

    void delete(T entity);

    void delete(ID primaryKey);

    void delete(Iterable<? extends T> iterableEntity);

    Page<T> findAll(Pageable pageInfo);

    List<T> findAll(Sort sort);


}
