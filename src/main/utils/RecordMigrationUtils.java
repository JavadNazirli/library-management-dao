package main.utils;

import main.dao.BookDAO;
import main.dao.file.CSVBasedBookDAOImpl;
import main.dao.file.TXTBasedBookDAOImpl;
import main.model.BookEntity;

import java.util.List;

public class RecordMigrationUtils {
    public void migrateTxtToCsv() {
        BookDAO txtDAO = new TXTBasedBookDAOImpl();
        BookDAO csvDAO = new CSVBasedBookDAOImpl();

        List<BookEntity> txtBooks = ((TXTBasedBookDAOImpl) txtDAO).getAllBooks();
        List<BookEntity> csvBooks = ((CSVBasedBookDAOImpl) csvDAO).getAllBooks();

        for (BookEntity book : txtBooks) {
            boolean exist = csvBooks.stream()
                    .anyMatch(csvBook -> csvBook.getIsbn().equals(book.getIsbn()));
            if (!exist) {
                csvBooks.add(book);
            }
        }
        ((CSVBasedBookDAOImpl) csvDAO).saveAllBooks(csvBooks);
        System.out.println("Migrated TXT to CSV successfully.");
    }

    public void migrateCsvToTxt() {
        BookDAO csvDAO = new CSVBasedBookDAOImpl();
        BookDAO txtDAO = new TXTBasedBookDAOImpl();
        List<BookEntity> csvBooks = ((CSVBasedBookDAOImpl) csvDAO).getAllBooks();
        List<BookEntity> txtBooks = ((TXTBasedBookDAOImpl) txtDAO).getAllBooks();
        for (BookEntity book : csvBooks) {
            boolean exist = txtBooks.stream()
                    .anyMatch(txtBook -> txtBook.getIsbn().equals(book.getIsbn()));
            if (!exist) {
                txtBooks.add(book);
            }
        }

        ((TXTBasedBookDAOImpl) txtDAO).saveAllBooks(txtBooks);
        System.out.println("Migrated CSV to TXT successfully");
    }

}


