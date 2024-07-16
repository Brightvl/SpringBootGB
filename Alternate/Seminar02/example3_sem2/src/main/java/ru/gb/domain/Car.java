package ru.gb.domain;

import org.springframework.stereotype.Component;
import ru.gb.interfaces.iEngin;

@Component
public class Car {

    private String model = "BMW";
    private String made = "X1";

    private iEngin carEngin;

    public Car(iEngin carEngin) {
        this.carEngin = carEngin;
    }


    public void go()
    {
        carEngin.startEngine();
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", made='" + made + '\'' +
                ", carEngin=" + carEngin +
                '}';
    }
}
