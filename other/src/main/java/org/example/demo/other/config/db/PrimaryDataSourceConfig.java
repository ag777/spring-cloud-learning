package org.example.demo.other.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Description mybatis第一个数据源配置
 * @Date: 2020/11/20 15:12
 */
//@Configuration
//@MapperScan(basePackages = "org.example.demo",
//        sqlSessionTemplateRef = "primarySqlSessionTemplate")
@Deprecated //多数据源配置
public class PrimaryDataSourceConfig {
    @Resource
    @Qualifier("primaryDataSource")
    DataSource dataSource;
//    @Bean(name = "primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")   //数据源primary配置
//    @Primary
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(
            @Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置XML文件存放位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:generator/testdb/*.xml")); //注意这里testdb目录
        return bean.getObject();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
