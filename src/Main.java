import main.dao.BookDAO;
import main.dao.db.MySqlBasedDaoImpl;
import main.dao.db.PostgresSqlBasedDaoImpl;
import main.model.BookEntity;

public class Main {
    public static void main(String[] args) {
        MySqlBasedDaoImpl myDao = new MySqlBasedDaoImpl();
        BookEntity book = new BookEntity("Text", "Text0", 2025);
        BookEntity book2 = new BookEntity("Text", "Text2", 2025);
        BookEntity book3 = new BookEntity("Text", "Text3", 2025);
        myDao.addBook(book);
        myDao.addBook(book2);
        myDao.addBook(book3);

    }
}