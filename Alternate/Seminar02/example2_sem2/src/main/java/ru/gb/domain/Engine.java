package ru.gb.domain;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    private String typeEngin = "Бензиновый";


    public Engine() {
        System.out.println("Bin engine created!");
    }

    public String getTypeEngin() {
        return typeEngin;
    }

    public void setTypeEngin(String typeEngin) {
        this.typeEngin = typeEngin;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "typeEngin='" + typeEngin + '\'' +
                '}';
    }
}
