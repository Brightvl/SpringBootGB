package ru.gb.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gb.interfaces.iEngin;

@Component
@Primary
public class DiselEngin implements iEngin {
    @Override
    public void startEngine() {
        System.out.println("DiselEngin");
    }
}
