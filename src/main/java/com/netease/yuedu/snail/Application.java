package com.netease.yuedu.snail;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.netease.yuedu.snail.common.config.multidatasource.DynamicDataSource;
import com.netease.yuedu.snail.common.config.multidatasource.DynamicDataSourceTransactionManager;
import com.netease.yuedu.snail.common.config.multidatasource.DynamicPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@MapperScan(value = "com.netease.yuedu.snail.biz.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(value = "snailMaster")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.snail-master")
    public DataSource snailMasterDataSource() {
        return new DruidDataSource();
    }


    @Bean(value = "snailSlave")
    @ConfigurationProperties(prefix = "spring.datasource.snail-slave")
    public DataSource snailSlaveDataSource() {
        return new DruidDataSource();
    }

    @Bean(value = "yueduSlave")
    @ConfigurationProperties(prefix = "spring.datasource.yuedu-slave")
    public DataSource yueduSlave() {
        return new DruidDataSource();
    }

    @Bean(value = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        return new DynamicDataSource(snailMasterDataSource(), snailSlaveDataSource(), yueduSlave());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
        //添加分页插件,数据源切换插件
        Interceptor[] plugins = {pageHelper(), new DynamicPlugin()};
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DynamicDataSourceTransactionManager transactionManager = new DynamicDataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDataSource());
        return transactionManager;
    }
}
