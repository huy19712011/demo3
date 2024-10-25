package com.example.demo3.utils;

import com.example.demo3.entity.Product;
import com.example.demo3.entity.Student;
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

                Properties settings = getProperties();

                configuration.setProperties(settings);

                // Here we need register entities to use!!!
                configuration.addAnnotatedClass(Student.class);
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

    public static Properties getProperties() {
        // JPA settings equivalent to persistence.xml
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        settings.put(Environment.URL, "jdbc:sqlserver://localhost;database=test1;encrypt=true;trustServerCertificate=true;");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "123456");
        //settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect"); // not need, auto-detect here

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        return settings;
    }
}
