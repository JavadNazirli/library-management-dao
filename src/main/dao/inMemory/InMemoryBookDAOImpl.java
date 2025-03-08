package main.dao.inMemory;

import main.dao.BookDAO;
import main.model.BookEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookDAOImpl implements BookDAO {
    List<BookEntity> books = new ArrayList<>();

    @Override
    public void addBook(BookEntity book) {
        books.add(book);
        System.out.println("Book added. ID: " + book.getIsbn());
    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        for (BookEntity book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBook(BookEntity book) {
        for (BookEntity book1 : books) {
            if (book1.getIsbn().equalsIgnoreCase(book.getIsbn())) {
                book1.setName(book.getName());
                book1.setAuthor(book.getAuthor());
                book1.setPublicationYear(book.getPublicationYear());
                return;
            }
        }
        System.out.println("Book Not Found. ID: " + book.getIsbn());
    }

    @Override
    public void deleteBook(String isbn) {
        books.removeIf(book1 -> book1.getIsbn().equalsIgnoreCase(isbn));

    }
}
