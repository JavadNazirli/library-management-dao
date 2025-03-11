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

        return removeDuplicates(allBooks);
    }


    public static void migrateAllFilesToTXT() {
        TXTBasedBookDAOImpl txtDAO = new TXTBasedBookDAOImpl();
        List<BookEntity> allBooks = getAllBooksFromSources();
        txtDAO.saveAllBooks(allBooks);
        System.out.println("All books was migrated to TXT file: " + allBooks.size() + " records");
    }

    public static void migrateAllFilesToCSV() {
        CSVBasedBookDAOImpl csvDAO = new CSVBasedBookDAOImpl();
        List<BookEntity> allBooks = getAllBooksFromSources();
        csvDAO.saveAllBooks(allBooks);
        System.out.println("All books was migrated to CSV file: " + allBooks.size() + " records");
    }

    public static void migrateAllFilesToJSON() {
        JSONBasedBookDAOImpl jsonDAO = new JSONBasedBookDAOImpl();
        List<BookEntity> allBooks = getAllBooksFromSources();
        jsonDAO.saveAllBooks(allBooks);
        System.out.println("All books was migrated to JSON file: " + allBooks.size() + " records");
    }

    public static void migrateAllFilesToXML() {
        XMLBasedBookDAOImpl xmlDAO = new XMLBasedBookDAOImpl();
        List<BookEntity> allBooks = getAllBooksFromSources();
        xmlDAO.saveAllBooks(allBooks);
        System.out.println("All books was migrated to XML file: " + allBooks.size() + " records");
    }

    public static List<BookEntity> migrateAllFilesToMemory() {
        List<BookEntity> uniqueBooks = getAllBooksFromSources();
        System.out.println("All books was migrated to memory: " + uniqueBooks.size() + " records");
        return uniqueBooks;
    }

    private static List<BookEntity> removeDuplicates(List<BookEntity> books) {
        Set<String> isbnSet = new HashSet<>();
        List<BookEntity> uniqueBooks = new ArrayList<>();
        for (BookEntity book : books) {
            if (isbnSet.add(book.getIsbn())) {
                uniqueBooks.add(book);
            }
        }
        return uniqueBooks;
    }

}


