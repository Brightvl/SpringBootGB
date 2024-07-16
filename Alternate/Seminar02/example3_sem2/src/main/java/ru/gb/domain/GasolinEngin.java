package ru.gb.domain;

import org.springframework.stereotype.Component;
import ru.gb.interfaces.iEngin;

@Component
public class GasolinEngin implements iEngin {
    @Override
    public void startEngine() {
        System.out.println("GasolinEngin");
    }
}
