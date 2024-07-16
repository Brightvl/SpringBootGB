package org.example.SpringHomework8.Service;

import lombok.AllArgsConstructor;
import org.example.SpringHomework8.Aspect.TrackUserAction;
import org.example.SpringHomework8.Repository.Repository;
import org.example.SpringHomework8.Model.Book;

import java.util.List;

//сервис - методы бизнес-логики
@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {
    private final Repository repository;

    //Просмотр всех книг
    @TrackUserAction
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    //Получение книги по названию
    @TrackUserAction
    public Book getBookByName(String name) {
        return repository.findById(name).orElse(null);
    }

    //Добавление книги
    @TrackUserAction
    public Book createBook(Book book) {
        return repository.save(book);
    }

    //Продажа книги
    @TrackUserAction
    public void sellBook(String name) {
        repository.deleteById(name);
    }
}