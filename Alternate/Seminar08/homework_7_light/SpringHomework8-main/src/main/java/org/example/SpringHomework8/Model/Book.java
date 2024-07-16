package org.example.SpringHomework8.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

//класс книга - модель
@Data
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int shelf;

    public Book() {
    }
}
