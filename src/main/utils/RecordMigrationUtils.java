package main.utils;

import main.dao.BookDAO;
import main.dao.file.CSVBasedBookDAOImpl;
import main.dao.file.JSONBasedBookDAOImpl;
import main.dao.file.TXTBasedBookDAOImpl;
import main.dao.file.XMLBasedBookDAOImpl;
import main.model.BookEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordMigrationUtils {
    private static List<BookEntity> getAllBooksFromSources() {

        TXTBasedBookDAOImpl txtDAO = new TXTBasedBookDAOImpl();
        CSVBasedBookDAOImpl csvDAO = new CSVBasedBookDAOImpl();
        XMLBasedBookDAOImpl xmlDAO = new XMLBasedBookDAOImpl();
        JSONBasedBookDAOImpl jsonDAO = new JSONBasedBookDAOImpl();

        List<BookEntity> allBooks = new ArrayList<>();
        allBooks.addAll(txtDAO.getAllBooks());
        allBooks.addAll(csvDAO.getAllBooks());
        allBooks.addAll(xmlDAO.getAllBooks());
        allBooks.addAll(jsonDAO.getAllBooks());

        Set<String> isbnSet = new HashSet<>();
        List<BookEntity> uniqueBooks = new ArrayList<>();
        for (BookEntity book : allBooks) {
            if (isbnSet.add(book.getIsbn())) {
                uniqueBooks.add(book);
            }

        }
        return uniqueBooks;
    }

    public static void migrateAllFilesToTXT() {
        TXTBasedBookDAOImpl txtDAO = new TXTBasedBookDAOImpl();
        List<BookEntity> booksInTxt = txtDAO.getAllBooks();
        List<BookEntity> booksInAllOfFiles = getAllBooksFromSources();

        Set<String> isbnSet = new HashSet<>();
        List<BookEntity> uniqueBooks = new ArrayList<>();
        for (BookEntity book : booksInTxt){
            if (isbnSet.add(book.getIsbn())) {
                uniqueBooks.add(book);
            }
        }
        for (BookEntity book : booksInAllOfFiles){
            if (isbnSet.add(book.getIsbn())) {
                uniqueBooks.add(book);
            }
        }
        txtDAO.saveAllBooks(uniqueBooks);
    }

    public static void migrateAllFilesToCSV() {
        List<BookEntity> uniqueBooks = getAllBooksFromSources();
        CSVBasedBookDAOImpl csvDAO = new CSVBasedBookDAOImpl();
        csvDAO.saveAllBooks(uniqueBooks);
        System.out.println("All books was migrated to CSV file");
    }

    public static void migrateAllFilesToJSON() {
        List<BookEntity> uniqueBooks = getAllBooksFromSources();
        JSONBasedBookDAOImpl jsonDAO = new JSONBasedBookDAOImpl();
        jsonDAO.saveAllBooks(uniqueBooks);
        System.out.println("All books was migrated to JSON file");
    }

    public static void migrateAllFilesToXML() {
        List<BookEntity> uniqueBooks = getAllBooksFromSources();
        XMLBasedBookDAOImpl xmlDAO = new XMLBasedBookDAOImpl();
        xmlDAO.saveAllBooks(uniqueBooks);
        System.out.println("All books was migrated to XML file");
    }

    public static List<BookEntity> migrateAllFilesToMemory() {
        List<BookEntity> uniqueBooks = getAllBooksFromSources();
        System.out.println("All books was migrated to memory");
        return uniqueBooks;
    }

}


