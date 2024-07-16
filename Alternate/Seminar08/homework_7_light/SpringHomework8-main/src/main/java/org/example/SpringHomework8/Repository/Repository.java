package org.example.SpringHomework8.Repository;

import org.example.SpringHomework8.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//репозиторий на основе Jpa-репозитория
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Book, String> {}
