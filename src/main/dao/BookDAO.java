package main.dao;

import main.model.BookEntity;

import java.util.Optional;

public interface BookDAO {
    void addBook(BookEntity book);

    Optional<BookEntity> getBook(String isbn);

    void updateBook(BookEntity book);

    void deleteBook(String isbn);

}
