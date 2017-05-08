package xzfm.common.domain.core;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by wangxizhong on 16/12/18.
 *
 * @author wangxizhong
 * @return Map
 */

public class NativeQuery {

    private static EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public static void setEntityManager(EntityManager entityManager) {
        if (entityManager == null || NativeQuery.entityManager != entityManager)
            NativeQuery.entityManager = entityManager;
    }

    public Query createQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query;
    }
}

