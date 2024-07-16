package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.ProjectConfig;
import ru.gb.domain.Car;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

//        Car myCar = new Car();
//        myCar.setModel("CRV");
//        myCar.setMade("HONDA");
//        System.out.println(myCar);

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Car auto1 = context.getBean("MYCAR",Car.class);
        System.out.println(auto1);
        Car auto2 = context.getBean("FATHERCAR",Car.class);
        System.out.println(auto2);
    }
}