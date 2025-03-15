import main.dao.BookDAO;
import main.dao.file.PostgresSqlBasedDaoImpl;
import main.model.BookEntity;

public class Main {
    public static void main(String[] args) {
        BookDAO dao = new PostgresSqlBasedDaoImpl();

        // Kitap ekle
        BookEntity book = new BookEntity("Test Kitap", "Usta Yazar", 2023);
        dao.addBook(book);

    }
}