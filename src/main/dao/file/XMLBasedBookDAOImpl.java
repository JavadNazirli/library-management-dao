package main.dao.file;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.dao.BookDAO;
import main.model.BookEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class XMLBasedBookDAOImpl implements BookDAO {
    private final static String FILE_PATH = "book_records/book_records.xml";
    private final XmlMapper xmlMapper;

    public XMLBasedBookDAOImpl() {
        this.xmlMapper = new XmlMapper();
        xmlMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void addBook(BookEntity book) {
        List<BookEntity> books = readBooksFromFile();
        String isbn = UUID.randomUUID().toString();
        BookEntity bookEntity = new BookEntity(isbn, book.getName(), book.getAuthor(), book.getPublicationYear());
        books.add(bookEntity);
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
        try {
            BookListWrapper wrapper = xmlMapper.readValue(file, BookListWrapper.class);
            if (wrapper != null && wrapper.getBooks() != null) {
                books = wrapper.getBooks();
            }
        } catch (IOException e) {
            System.out.println("XML reading failed: " + e.getMessage());
        }
        return books;
    }

    private void writeBooksToFile(List<BookEntity> books) {
        try {
            BookListWrapper wrapper = new BookListWrapper(books);
            xmlMapper.writeValue(new File(FILE_PATH), wrapper);
        } catch (IOException e) {
            System.out.println("XML writing failed: " + e.getMessage());
        }
    }

    public List<BookEntity> getAllBooks() {
        return readBooksFromFile();
    }

    public void saveAllBooks(List<BookEntity> books) {
        writeBooksToFile(books);
    }


    @JacksonXmlRootElement(localName = "books")
    private static class BookListWrapper {
        @JacksonXmlElementWrapper(useWrapping = false)
        List<BookEntity> books;

        public List<BookEntity> getBooks() {
            return books;
        }

        public void setBooks(List<BookEntity> books) {
            this.books = books;
        }

        public BookListWrapper() {
        }

        public BookListWrapper(List<BookEntity> books) {
            this.books = books;
        }
    }
}
