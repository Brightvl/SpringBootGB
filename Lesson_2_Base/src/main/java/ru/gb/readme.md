## Описание

`Общее`
[Application.java](Lesson2_basics/Application.java)

`@Component`
`@Autowired`
[EmailSender.java](core%2FEmailSender.java)

`@Bean` `BeanDefinition` `@Primary` || `arg()`
[HibernateConfiguration.java](configuration%2FHibernateConfiguration.java)

`@Qualifier`
[HibernateSessionFactory.java](Lesson2_basics%2Fconfiguration%2FHibernateSessionFactory.java)

`@Scope`
[Application.java](Lesson2_basics/Application.java)
- SINGLETON [Singleton.java](Lesson2_basics%2Fscope%2FSingleton.java) 
- PROTOTYPE [Prototype.java](Lesson2_basics%2Fscope%2FPrototype.java) 
  
Жизненный цикл Bean 
`@PostConstruct` `@PreDestroy`
[Application.java](Lesson2_basics%2FApplication.java)