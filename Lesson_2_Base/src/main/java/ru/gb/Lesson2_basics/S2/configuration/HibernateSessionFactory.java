package ru.gb.Lesson2_basics.S2.configuration;

import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Connection;

/*
HibernateSessionFactory
Ситуация когда 2 bean одного типа прим: Connection и нам нужно положить в конструктор только один

используем @Qualifier("h2Connection")
В нее мы вставляем идентификатор Bean который нужен "h2Connection" либо "postgresConnection"
 */
public class HibernateSessionFactory {

  public HibernateSessionFactory(@Qualifier("h2Connection") Connection h2Connection) {

  }

}
