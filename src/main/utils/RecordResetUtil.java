package main.utils;

import main.dao.file.CSVBasedBookDAOImpl;
import main.dao.file.JSONBasedBookDAOImpl;
import main.dao.file.TXTBasedBookDAOImpl;
import main.dao.file.XMLBasedBookDAOImpl;
import main.model.BookEntity;

import java.util.List;

public class RecordResetUtil {
    public static void resetTxtFile() {
        TXTBasedBookDAOImpl txtDAO = new TXTBasedBookDAOImpl();
        txtDAO.saveAllBooks(List.of());
        System.out.println("TXT file have been reset successfully");
    }

    public static void resetCsvFile() {
        CSVBasedBookDAOImpl csvDAO = new CSVBasedBookDAOImpl();
        csvDAO.saveAllBooks(List.of());
        System.out.println("CSV file have been reset successfully");
    }

    public static void resetJsonFile() {
        JSONBasedBookDAOImpl jsonDAO = new JSONBasedBookDAOImpl();
        jsonDAO.saveAllBooks(List.of());
        System.out.println("JSON file have been reset successfully");
    }

    public static void resetXMLFile() {
        XMLBasedBookDAOImpl xmlDAO = new XMLBasedBookDAOImpl();
        xmlDAO.saveAllBooks(List.of());
        System.out.println("XML file have been reset successfully");
    }

    public static void resetAllFiles() {
        resetTxtFile();
        resetCsvFile();
        resetJsonFile();
        resetXMLFile();
    }

    public static void resetRecordsInMemory(List<BookEntity> memoryBooks) {
        if (memoryBooks != null) {
            memoryBooks.clear();
            System.out.println("Records in memory have been reset successfully");
        }
    }

    public static void resetAllRecords(List<BookEntity> memoryBooks) {
        resetAllFiles();
        resetRecordsInMemory(memoryBooks);
    }
}
