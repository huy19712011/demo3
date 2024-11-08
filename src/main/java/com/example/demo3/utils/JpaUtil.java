package com.example.demo3.utils;

import com.example.demo3.entity.Product;
import com.example.demo3.entity.Student5;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //Properties settings = getProperties();
                Properties settings = getProperties("test1", "sa", "123456");

                configuration.setProperties(settings);

                // Here we need register entities to use!!!
                configuration.addAnnotatedClass(Student5.class);
                configuration.addAnnotatedClass(Product.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                entityManagerFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return entityManagerFactory;
    }

    public static Properties getProperties(String databaseName, String username, String password) {
        // JPA settings equivalent to persistence.xml
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //settings.put(Environment.URL, "jdbc:sqlserver://localhost;database=test1;encrypt=true;trustServerCertificate=true;");
        settings.put(Environment.URL, "jdbc:sqlserver://localhost;database=" + databaseName + ";encrypt=true;trustServerCertificate=true;");
        //settings.put(Environment.USER, "sa");
        settings.put(Environment.USER, username);
        //settings.put(Environment.PASS, "123456");
        settings.put(Environment.PASS, password);
        //settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect"); // not need, auto-detect here

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        // Change options here: create-drop, create, update, validate
        /*
            https://stackoverflow.com/questions/438146/what-are-the-possible-values-of-the-hibernate-hbm2ddl-auto-configuration-and-wha
            none - No action is performed. The schema will not be generated.
            create-only - The database schema will be generated.
            drop - The database schema will be dropped.
            create - The database schema will be dropped and created afterward.
            create-drop - The database schema will be dropped and created afterward. Upon closing the SessionFactory, the database schema will be dropped.
            validate - The database schema will be validated using the entity mappings.
            update - The database schema will be updated by comparing the existing database schema with the entity mappings.
        */
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        return settings;
    }
}
