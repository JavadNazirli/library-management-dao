package main.dao.file;

import main.dao.BookDAO;
import main.model.BookEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TXTBasedBookDAOImpl implements BookDAO {
    private static final String FILE_PATH = "book_records/book_records.txt";
    private static final String SEPARATOR = ";";

    @Override
    public void addBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        books.add(new BookEntity(UUID.randomUUID().toString(), book.getName(), book.getAuthor(), book.getPublicationYear()));
        writeBooksToFile(books);
    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        List<BookEntity> books = readBooksFromFile();
        for (BookEntity book : books) {
            if (book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(book.getIsbn())) {
                books.set(i, new BookEntity(UUID.randomUUID().toString(), book.getName(), book.getAuthor(), book.getPublicationYear()));
                writeBooksToFile(books);
            }
        }

    }

    @Override
    public void deleteBook(String isbn) {
        List<BookEntity> books = readBooksFromFile();
        books.removeIf(b -> b.getIsbn().equals(isbn));
        writeBooksToFile(books);
    }

    private List<BookEntity> readBooksFromFile() {
        List<BookEntity> books = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] row = line.split(SEPARATOR);
                if (row.length == 4) {
                    String isbn = row[0];
                    String name = row[1];
                    String author = row[2];
                    int publicationYear = Integer.parseInt(row[3]);
                    books.add(new BookEntity(isbn, name, author, publicationYear));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private void writeBooksToFile(List<BookEntity> books) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (BookEntity book : books) {
                bufferedWriter.write(
                        book.getIsbn() + SEPARATOR
                                + book.getName() + SEPARATOR
                                + book.getAuthor() + SEPARATOR
                                + book.getPublicationYear() + SEPARATOR
                );
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<BookEntity> getAllBooks() {
        return readBooksFromFile();
    }

    public void saveAllBooks(List<BookEntity> books) {
        writeBooksToFile(books);
    }
}
