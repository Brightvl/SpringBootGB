package ru.gb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.gb.domain.Car;
import ru.gb.domain.Engine;

@Configuration
@ComponentScan(basePackages = "ru.gb.domain")
public class ProjectConfig {

//    @Bean
//    Engine engine()
//    {
//        Engine engine = new Engine();
//        return engine;
//    }
//
//    @Bean
//    Car car()
//    {
//        Car myCar = new Car();//engine());
//        return  myCar;
//    }

}
