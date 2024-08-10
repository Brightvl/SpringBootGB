package ru.gb.Lesson2_basics.S2.core;

import org.springframework.stereotype.Component;

/*
@Component спринг помещает такие классы в контекст
Имя bean не обязательно, обычно берется имя класса, но
можно и так ("myEmailSender") пишется с маленькой буквы, имя класса будет игнорироваться
 */
@Component("myEmailSender") // myEmailSender
public class EmailSender {

    /*
    1.
    @Autowired сигнал для спринга что нужно создать bean (то есть инициализировать поле - new EmailServerParameters).
    В общем это называется Inversion Of Control - по сути мы говорим спрингу ЧТО нам нужно, но не КАК.
    Один bean внедряется другой. В данном контексте Field Injection.
    Под капотом! Спринг, с помощью Reflection ищет все поля класса смотрит есть ли там аннотация @Autowired,
    Если есть берет его Bean в контексте и инициализирует
    !!! Но так лучше не делать !!! А использовать Constructor injection !!! Чтобы не использовать reflection

    Если к примеру нам может понадобиться SessionFactory, а он не объект спринга HibernateConfiguration
    нужно объявить как @Component и конструктор пометить аннотацией @Bean.
     */
//    @Autowired (не бро)
    private final EmailServerParameters parameters;
//  private final SessionFactory sessionFactory;

    /*
    2.
    Лучше использовать Constructor injection или Dependency injection.
    По сути мы в параметры конструктора должны перечислить те Bean которые нам нужны
    Если конструктор 1 - то @Autowired необязательна
    Если более одного - то @Autowired должны быть только одна

    Бывают случаи когда у переданных аргументов. А это по сути конструкторы у которых тоже могут быть параметры ()
    Пример в HibernateConfiguration()
     */

    // @Autowired (бро)
    public EmailSender(EmailServerParameters parameters) {
        this.parameters = parameters;
//    this.sessionFactory = sessionFactory;
    }

    /*
    3.
    Method injection тоже не желательно. Нужно бивает например создать и сразу выкинуть bean

    @Autowired
    public void setParameters(EmailServerParameters parameters) {
        this.parameters = parameters;
    }
     */

    public void sendEmail(String subject, String body, String recipient) {
        // ...
        System.out.println("Письмо [" + subject + "] отправлено получателю (" + recipient + ")");
        System.out.println("Адрес smtp: " + parameters.getEmailServerAddress());
    }

}
