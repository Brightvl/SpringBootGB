package ru.gb.Lesson2_basics.hw2_electronicQueue;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)

public class TicketNumberGenerator {
    public String createNewNumber() {

        return UUID.randomUUID().toString();
    }
}
