import main.dao.BookDAO;
import main.dao.file.PostgresSqlBasedDaoImpl;
import main.model.BookEntity;

public class Main {
    public static void main(String[] args) {
        PostgresSqlBasedDaoImpl postgresSqlBasedDao = new PostgresSqlBasedDaoImpl();
        postgresSqlBasedDao.getAllBooks().forEach(System.out::println);


    }
}