package xzfm.monitor.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by wangxizhong on 17/4/18.
 */
public class DataSourceFactory {
    @Autowired
    private DruidProperties druidProperties;

    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource createDateSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druidProperties.getDbUrl());
        druidDataSource.setUsername(druidProperties.getUserName());
        druidDataSource.setPassword(druidProperties.getPassword());
        druidDataSource.setDriverClassName(druidProperties.getDriverClassName());
        //configuration
        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMaxWait(druidProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        druidDataSource.setFilters(druidProperties.getFilters());
        druidDataSource.setTimeBetweenLogStatsMillis(300000);
        druidDataSource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        return druidDataSource;
    }
}
