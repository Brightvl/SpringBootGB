package ru.gb.Lesson2_basics.hw2_electronicQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope (ConfigurableListableBeanFactory.SCOPE_SINGLETON)
public class DisplayBoard {

    private TicketNumberGenerator generator;

    @Autowired
    public DisplayBoard(TicketNumberGenerator generator) {
        this.generator = generator;
    }

    public Ticket newTicket() {
        return new Ticket(generator.createNewNumber());
    }
}
