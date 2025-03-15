package main.dao.file;

import main.dao.BookDAO;
import main.model.BookEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresSqlBasedDaoImpl implements BookDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
    private static final String USER = "javad";
    private static final String PASSWORD = "javad321!";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void addBook(BookEntity book) {
        String sql = "INSERT INTO books (isbn, name, author, publication_year) VALUES (?, ?, ?, ?)";
        String generatedIsbn = UUID.randomUUID().toString();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, generatedIsbn);
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getPublicationYear());
            stmt.executeUpdate();
            System.out.println("Book added to Postgres with ISBN: " + generatedIsbn);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to add book to Postgres: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<BookEntity> getBook(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BookEntity book = new BookEntity(
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("publication_year")
                );
                return Optional.of(book);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to get book from Postgres: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateBook(BookEntity book) {
        String sql = "UPDATE books SET name = ?, author = ?, publication_year = ? WHERE isbn = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getPublicationYear());
            stmt.setString(4, book.getIsbn());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated in Postgres: " + book.getName());
            } else {
                throw new IllegalArgumentException("No book found with ISBN: " + book.getIsbn());
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update book in Postgres: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted from Postgres with ISBN: " + isbn);
            } else {
                throw new IllegalArgumentException("No book found with ISBN: " + isbn);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to delete book from Postgres: " + e.getMessage(), e);
        }
    }
    public List<BookEntity> getAllBooks() {
        List<BookEntity> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BookEntity book = new BookEntity(
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("publication_year")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to get all books from Postgres: " + e.getMessage(), e);
        }
    }
}
