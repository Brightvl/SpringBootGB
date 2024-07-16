package ru.gb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import ru.gb.domain.Car;

@Configuration
public class ProjectConfig {

    @Bean("MYCAR")
    @Lazy
    //@Scope("prototype")
    Car car1()
    {
        Car myCar = new Car();
        myCar.setModel("CHERY");
        myCar.setMade("H7");
        return myCar;
    }

    @Bean(name = "FATHERCAR")
        //@Scope("prototype")
    Car car2()
    {
        Car myCar = new Car();
        myCar.setModel("Lada");
        myCar.setMade("Vesta");
        return myCar;
    }
}
