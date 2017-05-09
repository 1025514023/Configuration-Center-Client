package xzfm.core.data;

import xzfm.common.domain.core.BaseDao;
import xzfm.core.domain.entity.ConfigurationCenter;
import xzfm.monitor.datasource.SpringMonitor;

import java.util.List;

/**
 * Created by wangxizhong on 17/4/24.
 */
public interface ConfigurationCenterDao extends SpringMonitor, BaseDao<ConfigurationCenter, String> {

    ConfigurationCenter findByConfigurationKey(String configurationKey);
}
