package xzfm.common.domain.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by wangxizhong on 17/5/6.
 */
public class InitializationJpaEntityManager implements InitializingBean {

    @Autowired
    private EntityManager entityManager;

    Log logger = LogFactory.getLog(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        NativeQuery.setEntityManager(entityManager);
        logger.info("Initialization entityManager");
    }
}
