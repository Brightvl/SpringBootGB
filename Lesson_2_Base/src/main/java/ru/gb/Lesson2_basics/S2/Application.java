package ru.gb.Lesson2_basics.S2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.Lesson2_basics.S2.core.EmailSender;
import ru.gb.Lesson2_basics.S2.scope.Prototype;
import ru.gb.Lesson2_basics.S2.scope.Singleton;

@SpringBootApplication
public class Application {

    /*
    1. Bean - объект, жизненным циклом которого управляет Spring. (Все объекты созданные Spring)
    ApplicationContext - контейнер для Bean

    Жизненный цикл Bean
        @PostConstruct
        public void init()
    вызывается в самом начале когда bean создается
        @PreDestroy
        public void destroy()
    вызывается в самом конце когда, например закрывается контекст contest.close()
    В целом когда контекст открывается Spring ищет у бинов @PostConstruct а при закрытии @PreDestroy

    bean1, bean2, bean3(prototype), ...
    каждый раз когда мы делаем getBean это по сути тоже что map.get("emailSender") достаем по сути
    AppContext[bean1, bean2, bean3(prototype)] HashMap<String, Object>
    ...
    context.close()
    -> bean1.preDestroy()
    когда вызывается @PreDestroy у нас не уничтожается объект (он в хипе/оперативке)
    уничтожится просто bean из контекста (из HashMap)
    Spring работает с Java а не напрямую с оперативкой

    2. Когда spring запускается он сначала все сканирует (метки) но пока ничего не создает.
    HibernateConfiguration про BeanDefinition -> BeanFactory -> Bean

    3. Scope - зона видимости Bean [Singleton, Prototype, | Session, Request, ...]
     */


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // 1. Тут реализован класс Singleton, достали bean из контекста и вызвали метод sendEmail()
        EmailSender emailSender = context.getBean(EmailSender.class);
        emailSender.sendEmail(
                "test", "asfdad", "inchestnov.dump@gmail.com"
        );


        /*
        3. Scope - [Singleton, Prototype, | Session, Request, ...]
        output:
            Singleton #1 = 1
            Singleton #2 = 1
            Singleton #3 = 1
        Потому что Singleton
         */
        System.out.println("Singleton #1 = " + context.getBean(Singleton.class).getId());
        System.out.println("Singleton #2 = " + context.getBean(Singleton.class).getId());
        System.out.println("Singleton #3 = " + context.getBean(Singleton.class).getId());
        /*
        Бывают ситуации когда на каждый вызов конструктора нужен новый объект.
        Singleton нужно обходить с помощью scope prototype

         Singleton сохраняется в контексте до вызова
         Prototype работает по вызову

         Вопрос. Что будет если inject prototype в singleton какой жизненный цикл? 1:30 2-семинар
         Prototype будет жить в singleton
         */
        System.out.println("Prototype #1 = " + context.getBean(Prototype.class).getId()); // 2
        System.out.println("Prototype #2 = " + context.getBean(Prototype.class).getId()); // 3
        System.out.println("Prototype #3 = " + context.getBean(Prototype.class).getId()); // 4

        Singleton singleton = context.getBean(Singleton.class);
        singleton.printIds();
        singleton.printIds();
        singleton.printIds();
    }
}
