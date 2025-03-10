package main.model;

public class BookEntity {
    private String isbn;
    private String name;

    public BookEntity() {
    }

    private String author;
    private int publicationYear;

    public BookEntity(String name, String author, int publicationYear) {
        this(null, name, author, publicationYear);
    }

    public String getIsbn() {
        return isbn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "ISBN= " + isbn +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }

    public BookEntity(String isbn, String name, String author, int publicationYear) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
