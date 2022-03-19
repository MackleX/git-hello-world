package com.java.tips.and.tricks.patchmapping.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;



/**
 * <h1>Hibernate configuration class</h1>
 * this class contain hibernate configuration; it's configured to use an H2 in memory database called tipdb whit an JDBC driver for connection
 * @author  El batouri badr-eddine
 * @author https://www.linkedin.com/in/badr-eddine-elbatouri-3298a21b3/
 * @version 1.0
 * @since   2022-03-1
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    /**
     * create and configure a <a href="https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html">Datasource</a>
     * bean that represent A factory for connections to the physical data source,
     * which will be provided by SpringFactory whenever this bean is needed; generally when we need to establish a connection.
     * <br>
     * <br>
     * <h2> Steps for configuring Datasource bean in our case:</h2>
     * <h3><strong>1</strong>-Create a <a href="https://tomcat.apache.org/tomcat-9.0-doc/api/org/apache/tomcat/dbcp/dbcp2/BasicDataSource.html">BasicDataSource</a>  instance:</h3>
     * <code>BasicDataSource dataSource = new BasicDataSource();</code>
     * <br>
     * <br>
     * <h3><strong>2-</strong> set the driver to work whit h2 database(since we are going to be using h2 base for our article when persisting data)</h3>
     * <code>dataSource.setDriverClassName("org.h2.Driver");</code>
     * <br>
     * <br>
     *
     * <h3><strong>3-</strong> configuring the url</h3>
     * <code>dataSource.setUrl("jdbc:h2:mem:tipdb;DB_CLOSE_DELAY=-1");</code>
     * <br>
     * <br>
     * which means:
     * <br>
     * <strong>jdbc</strong>; we will use jdbc driver for establishing a connection with the database
     * <br>
     * <strong>h2</strong>; our database is a h2 database
     * <br>
     * <strong>mem</strong>; database will be embedded whit application(lives in memory)
     * <br>
     * <strong>tipdb</strong>; name of the database
     * <br>
     * <strong>DB_CLOSE_DELAY=-1</strong>; we don't shut down the connection when we finish executing an operation in the database,
     * since shutting down connection mean shutting down our in memory database thus all of our persisted data will be deleted, this only apply to in memory database.
     * <br>
     * <br>
     * @return a configured <a href="https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html">Datasource</a> bean.
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }


    /**
     * create and configure a hibernate Proprties
     * bean that represent a container for hibernate properties,
     * which will be provided by SpringFactory whenever this bean is needed via dependency injection; generally when creating a shared hibernate session, main configurations for our tip are:
     * <br>
     * 1-the dialect that hibernate will be using
     * <br>
     * 2-application bootstrapping configuration; dropping the database when our application goes off and creating the database when the application is bootstrapping
     * <br>
     * <h2>Steps for configuring Hibernate Properties bean in our case:</h2>
     * <h3><strong>1-</strong> Create a Properties object</h3>
     * <code>Properties hibernateProperties = new Properties();</code>
     * <br>
     * <br>
     * <h3><strong>2-</strong>configure hibernate properties to drop database on application shutdown and create it when application is bootstrapping</h3>
     * <code>hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");</code>
     * <br>
     * <br>
     * <p>which means:</p><strong> hibernate.hbm2ddl.auto=create-drop;</strong> so every time our application start it create the database and when it close it drops the database
     * <br>
     * <br>
     * @return a Properties object that contain our hibernate properties
     */
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return hibernateProperties;
    }

    /**
     * this method create and configure our <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/orm/hibernate5/LocalSessionFactoryBean.html">LocalSessionFactoryBean</a>:
     * that represents a shared session factory in our spring application context;that can be provided by springFactory whenever it's needed.
     * <br>
     * <br>
     * <h2>Steps for configuring <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/orm/hibernate5/LocalSessionFactoryBean.html">LocalSessionFactoryBean</a> in our case:</h2>
     * <h3><strong>1-Create a LocalSessionFactoryBean istance:</strong></h3>
     * <code>LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();</code>
     * <br>
     * <br>
     * <h3><strong>2-</strong>Specify the data source factory of type <a href="https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html">Datasource</a>
     * that will be used bu our LocalSessionFactoryBean bean to establish connections whit the database,
     * for our use case we have a bean that creates and configures a data source factory see {@link HibernateConfig#dataSource()}</h3>
     * <code>sessionFactory.setDataSource(dataSource());</code>
     * <br>
     * <br>
     * <h3><strong>3-Now we should specify what packages hibernate will start scanning for entities to autodetect them and apply hibernate ORM entity managing features on them:</strong></h3>
     * <code>sessionFactory.setPackagesToScan(new String[]{"com.java.tips.and.tricks.patchmapping.domain"});</code>\
     * <p>Which means:</p>
     * <stong>com.java.tips.and.tricks.patchmapping.domain:</stong> is the package where hibernate will start scanning for entities component in our case entities are located in com.java.tips.and.tricks.patchmapping.domain
     * @return a configured <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/orm/hibernate5/LocalSessionFactoryBean.html">LocalSessionFactoryBean</a>
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.example.springtraining.entities"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }


    /**
     * this methode start by creating a <p>transactionManager</p> and then ask for a session using the LocalSessionFactoryBean
     * and this session is set to our transaction manager thus the transaction manager can use it to access the data source
     * @return a transactionManager that will be used by data access objects as the interface for CRUD operation in the datasource
     */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


}