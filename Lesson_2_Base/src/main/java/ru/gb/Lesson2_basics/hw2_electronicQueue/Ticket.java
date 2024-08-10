package ru.gb.Lesson2_basics.hw2_electronicQueue;

import java.time.LocalDateTime;

public class Ticket {
    String number;

    LocalDateTime createdAt;

    public Ticket(String number) {
        this.number = number;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number='" + number + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
