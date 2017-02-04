package com.mycompany.meowcrm.config;

import com.mycompany.meowcrm.model.Cdr;
import com.mycompany.meowcrm.model.CdrRec;
import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.client.ClientNotice;
import com.mycompany.meowcrm.model.client.ClientState;
import com.mycompany.meowcrm.model.client.ClientType;
import com.mycompany.meowcrm.model.client.Contact;
import com.mycompany.meowcrm.model.client.ContactType;
import com.mycompany.meowcrm.model.Role;
import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.model.task.TaskState;
import com.mycompany.meowcrm.model.task.TaskType;
import com.mycompany.meowcrm.model.User;
import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.model.deal.DealState;
import com.mycompany.meowcrm.model.deal.DealType;
import com.mycompany.meowcrm.model.deal.Thing;
import com.mycompany.meowcrm.model.deal.ThingType;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:db_connection.properties"})
@ComponentScan(basePackages = {"com.mycompany.meowcrm.dao", "com.mycompany.meowcrm.model"})
@EnableTransactionManagement
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("con.driverClassName"));
        dataSource.setUrl(env.getProperty("con.url"));
        dataSource.setUsername(env.getProperty("con.user"));
        dataSource.setPassword(env.getProperty("con.pass"));

        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("con.testOnBorrow")));

        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hib.showSql"));
        properties.put("hibernate.dialect", env.getProperty("hib.dialect"));
        //properties.put("hibernate.default_schema", env.getProperty("hib.defSchema"));
        //properties.put("hibernate.default_catalog", env.getProperty("hib.defCatalog"));
        properties.put("hibernate.format_sql", env.getProperty("hib.formatSql"));

        properties.put("hibernate.hbm2ddl.auto", "update");//update

        return properties;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
//        sessionBuilder.addPackage("com.miroktell.contactbook.model");

        sessionBuilder.addAnnotatedClasses(
                Client.class,
                ClientState.class,
                ClientType.class,
                ClientNotice.class,
                Contact.class,
                ContactType.class,
                Role.class,
                User.class,
                Task.class,
                TaskType.class,
                TaskState.class,
                Deal.class,
                DealState.class,
                Thing.class,
                ThingType.class,
                DealType.class,
                Cdr.class,
                CdrRec.class
        );
        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

}
