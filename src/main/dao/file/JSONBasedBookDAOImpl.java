package main.dao.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.dao.BookDAO;
import main.model.BookEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JSONBasedBookDAOImpl implements BookDAO {
    private static final String FILE_PATH = "book_records/book_records.json";
    private final Gson gson;

    public JSONBasedBookDAOImpl() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void addBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        String isbn = UUID.randomUUID().toString();
        BookEntity newBook = new BookEntity(isbn, book.getName(), book.getAuthor(), book.getPublicationYear());
        books.add(newBook);
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
       for(int i=0;i<books.size();i++) {
           if(books.get(i).getIsbn().equals(book.getIsbn())) {
               books.set(i, new BookEntity(book.getIsbn(), book.getName(), book.getAuthor(), book.getPublicationYear()));
               writeBooksToFile(books);
               return;
           }
       }
    }

    @Override
    public void deleteBook(String isbn) {
        List<BookEntity> books = readBooksFromFile();
        books.removeIf(book -> book.getIsbn().equals(isbn));
        writeBooksToFile(books);
    }

    private List<BookEntity> readBooksFromFile() {
        List<BookEntity> books = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return books;
        }
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type bookListType = new TypeToken<ArrayList<BookEntity>>() {
            }.getType();
            books = gson.fromJson(reader, bookListType);
            if (books == null) {
                books = new ArrayList<>();
            }

        } catch (IOException e) {
            System.out.println("JSON file could not be read: " + e.getMessage());
        }
        return books;
    }

    private void writeBooksToFile(List<BookEntity> books) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.out.println("JSON file could not be written: " + e.getMessage());
        }
    }

    public List<BookEntity> getAllBooks() {
        return readBooksFromFile();
    }

    public void saveAllBooks(List<BookEntity> books) {
        writeBooksToFile(books);
    }

}
