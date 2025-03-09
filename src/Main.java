import main.dao.BookDAO;
import main.dao.file.CSVBasedBookDAOImpl;
import main.dao.file.TXTBasedBookDAOImpl;
import main.dao.inMemory.InMemoryBookDAOImpl;
import main.model.BookEntity;
import main.utils.RecordMigrationUtils;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
       TXTBasedBookDAOImpl txtBasedBookDAO = new TXTBasedBookDAOImpl();
        txtBasedBookDAO.getAllBooks().forEach(System.out::println);


//        CSVBasedBookDAOImpl bookDAOImpl = new CSVBasedBookDAOImpl();

//        --------------------------------------------------------------------------------



//        bookDAO.addBook(new BookEntity(1, "1984", "George Orwell", 2023));
//        bookDAO.addBook(new BookEntity(2, "Crime and Punishment", "Dostoevsky", 2024));
//        bookDAO.addBook(new BookEntity(3, "Atomic Habits", "James Clear", 2022));
//        Optional<BookEntity> bookEntity = bookDAO.getBook(2);
//        System.out.println("Result : " + bookEntity.map(BookEntity::getName).orElse("Not found"));
//        bookDAO.updateBook(new BookEntity(2, "The Da Vinci Code", "Dan Brown", 2025));
//        Optional<BookEntity> bookEntity2 = bookDAO.getBook(2);
//        System.out.println("Result : " + bookEntity2.map(BookEntity::getName).orElse("Not found"));
//        bookDAO.deleteBook(2);
//        Optional<BookEntity> bookEntity3 = bookDAO.getBook(2);
//        System.out.println("Result : " + bookEntity3.map(BookEntity::getName).orElse("Not found"));
//


    }
}