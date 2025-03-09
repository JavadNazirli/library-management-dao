package main.dao.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import main.dao.BookDAO;
import main.model.BookEntity;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CSVBasedBookDAOImpl implements BookDAO {
    private static final String FILE_PATH = "book_records/book_records.csv";
    private static final String[] HEADER = {"isbn", "name", "author", "publicationYear"};


    @Override
    public void addBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        BookEntity bookEntity = new BookEntity(UUID.randomUUID().toString(), book.getName(), book.getAuthor(), book.getPublicationYear());
        books.add(bookEntity);
        writeBooksToFile(books);
        System.out.println("Book added. ID: " + book.getIsbn());
    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        List<BookEntity> books = readBooksFromFile();
        for (BookEntity book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(book.getIsbn())) {
                books.set(i, new BookEntity(UUID.randomUUID().toString(), book.getName(), book.getAuthor(), book.getPublicationYear()));
                writeBooksToFile(books);
                return;
            }
        }
    }

    @Override
    public void deleteBook(String isbn) {
        List<BookEntity> books = readBooksFromFile();
        books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
        writeBooksToFile(books);

    }

    private List<BookEntity> readBooksFromFile() {
        List<BookEntity> books = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> allRaws = csvReader.readAll();
            for (int i = 1; i < allRaws.size(); i++) {
                String[] row = allRaws.get(i);
                if (row.length == HEADER.length) {
                    String isbn = row[0];
                    String name = row[1];
                    String author = row[2];
                    int publicationYear = Integer.parseInt(row[3]);
                    books.add(new BookEntity(isbn, name, author, publicationYear));
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private void writeBooksToFile(List<BookEntity> books) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_PATH))) {
            csvWriter.writeNext(HEADER);
            for (BookEntity book : books) {
                String[] row = {
                        book.getIsbn(),
                        book.getName(),
                        book.getAuthor(),
                        String.valueOf(book.getPublicationYear())
                };
                csvWriter.writeNext(row);
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public List<BookEntity> getAllBooks() {
        return readBooksFromFile();
    }

    public void saveAllBooks(List<BookEntity> books) {
        writeBooksToFile(books);
    }
}
