package ru.gb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private String model = "X1";
    private String made = "BMW";

    @Autowired
    private Engine carEngine;

    public Car() {//Engine carEngine
       // this.carEngine = carEngine;
        System.out.println("Bin Car Created!");
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", made='" + made + '\'' +
                ", carEngine=" + carEngine +
                '}';
    }
}
