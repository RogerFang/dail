package com.dail.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Roger on 2016/12/11.
 */
@Configuration
@MapperScan("com.dail.dao")
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // sqlSessionFactoryBean.setTypeAliasesPackage("com.dail.model");
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:/mapper/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
