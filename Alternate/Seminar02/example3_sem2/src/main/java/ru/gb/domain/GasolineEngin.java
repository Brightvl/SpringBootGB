package ru.gb.domain;

import org.springframework.stereotype.Component;
import ru.gb.interfaces.iEngin;

@Component
public class GasolineEngin implements iEngin {
    @Override
    public void startEngine() {
        System.out.println("GasolineEngin");
    }
}
