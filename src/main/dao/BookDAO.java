package main.dao;

import main.model.BookEntity;

import java.util.Optional;

public interface BookDAO {
    void addBook(BookEntity book);

    Optional<BookEntity> getBook(int id);

    void updateBook(BookEntity book);

    void deleteBook(int id);
}
