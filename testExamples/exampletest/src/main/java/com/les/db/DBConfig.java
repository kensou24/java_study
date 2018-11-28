package com.les.db;

import com.les.db.repository.UserRepositoryInterfaceImpl;
import com.les.db.repository.UserRepositoryInterface;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * @ClassName: DBConfig
 * @Description:
 *  1、数据库配置类，必须开启并且返回TransactionManager用于事务的处理
 *  2、JPA配置时候，EnableJpaRepositories,指定使用的entityManagerFactoryRef
 * @Author: king
 * @CreateDate: 2018/11/28 8:42
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "localContainerEntityManagerFactoryBean")
public class DBConfig{

    @Bean
    public ComboPooledDataSource comboPooledDataSource()
    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.11.103:3306/test?autoReconnect=true&useUnicode=true&" +
                    "characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT");
            comboPooledDataSource.setUser("wangjun");
            comboPooledDataSource.setPassword("wangjun");
            comboPooledDataSource.setInitialPoolSize(5);
            comboPooledDataSource.setMaxIdleTime(10);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean("localSessionFactoryBean")
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource)
    {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(new String[]{"com.les.db.entity"});
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.MariaDB103Dialect");
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(LocalSessionFactoryBean localSessionFactoryBean) {
        System.out.println(localSessionFactoryBean);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MariaDB103Dialect");
        return hibernateJpaVendorAdapter;
    }

    @Bean("localContainerEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter)
    {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"com.les.db.entity"});
        return localContainerEntityManagerFactoryBean;
    }


    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

}
