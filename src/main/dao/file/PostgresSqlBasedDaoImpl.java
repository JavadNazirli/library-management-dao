package main.dao.file;

import main.dao.BookDAO;
import main.model.BookEntity;

import java.util.Optional;

public class PostgresSqlBasedDaoImpl implements BookDAO {
    @Override
    public void addBook(BookEntity book) {

    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        return Optional.empty();
    }

    @Override
    public void updateBook(BookEntity book) {

    }

    @Override
    public void deleteBook(String isbn) {

    }
}
