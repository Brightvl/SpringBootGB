package ru.gb.Lesson2_basics.S2.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Configuration
public class HibernateConfiguration {

    /*
    circular dependency - циклическая зависимость (гонка)
    когда один должен был создать другой и наоборот BeanA <-> BeanB
    ParentBean -> (BeanA, BeanB) - костыль, чтобы исключить подобное, но лучше не допускать изначально
    BeanA -> BeanB
    BeanB -> BeanA
     */


    /*
    1.
    @Bean Указывает, что метод создает компонент, управляемый контейнером Spring.
    Короче спринг если ему понадобится hibernate, провалится в этот класс найдет аннотацию @Bean, вызовет этот метод

    Если у конструктора так же есть аргументы sessionFactory(Connection connection) этот Connection по сути является
    @Autowired который spring сам сделает из контекста. К примеру @Bean Connection h2Connection() мы добавили в
    этот самый контекст

    2.
    BeanDefinition -> BeanFactory -> Bean
    BeanDefinition - описание Bean, что за класс у него, какие метод и тд
    BeanFactory по описанию BeanDefinition создает Bean


    3.
    Бывает такое что нам нужно работать с несколькими DB, для этого у каждого Bean должно быть свое имя
    либо название метода
    Connection postgresConnection() Вызов: context.getBean("postgresConnection", EmailSender.class);
    Connection h2Connection() Вызов: context.getBean("h2Connection", EmailSender.class);
    либо так, название метода игнорируется
    @Bean(name = "postgresConnection")
    Вызов: EmailSender emailSender = context.getBean("postgresConnection", EmailSender.class);

    Ситуация когда 2 bean одного типа прим: Connection и нам нужно положить в конструктор только один

    1 - вариант решения в HibernateSessionFactory @Qualifier
    2 - @Primary - мы как бы говорим какой основной из двух а какой вспомогательный
    во всех местах где есть конфликт будет браться этот, либо ставим @Qualifier
     */

    @Primary
    @Bean(name = "postgresConnection")
    public Connection postgresConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
    }


    @Bean
    public Connection h2Connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }

    @Bean
    public SessionFactory sessionFactory(Connection connection) {
        // это hibernate
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();
        // это нами созданный
//    return new MySessionFactory(connection);
    }

}
