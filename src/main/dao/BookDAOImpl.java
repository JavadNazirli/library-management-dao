package main.dao;

import main.model.BookEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO {
    List<BookEntity> books = new ArrayList<BookEntity>();

    @Override
    public void addBook(BookEntity book) {
        books.add(book);
        System.out.println("Book added. ID: " + book.getId());
    }

    @Override
    public Optional<BookEntity> getBook(int id) {
        for (BookEntity book : books) {
            if (book.getId() == id) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBook(BookEntity book) {
        for (BookEntity book1 : books) {
            if (book1.getId() == book.getId()) {
                book1.setName(book.getName());
                book1.setAuthor(book.getAuthor());
                book1.setPublicationYear(book.getPublicationYear());
                return;
            }
        }
        System.out.println("Book Not Found. ID: " + book.getId());
    }

    @Override
    public void deleteBook(int id) {
        books.removeIf(book1 -> book1.getId() == id);

    }
}
