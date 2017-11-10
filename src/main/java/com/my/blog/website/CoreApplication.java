package com.my.blog.website;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@MapperScan("com.my.blog.website.dao")   //扫描mybatis-mapper
@SpringBootApplication
@EnableTransactionManagement  //开启事务管理
public class CoreApplication extends SpringBootServletInitializer {
    /*
     *  关于继承  SpringBootServletInitializer
     *  需要注意一下几点：
     *
     *     1.jar包中的打包方式根据自己的需要进行修改
     *     2.若打包成war包,则需要继承 org.springframework.boot.context.web.SpringBootServletInitializer类,
     *     覆盖其config(SpringApplicationBuilder)方法
     *     3.打包成war的话,如果打包之后的文件中没有web.xml文件的话
     *     自己可以加进去一个最简单的web.xml(只有根节点的定义,而没有子元素)，防止因缺乏web.xml文件而部署失败
     *
     *  参考：https://www.cnblogs.com/jiaoyiping/p/4251718.html
     *       http://blog.csdn.net/songhaifengshuaige/article/details/54138023
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }


}
