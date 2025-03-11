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
            }
            uniqueBooks.add(book);
        }
        return uniqueBooks;
    }

}


