package sk.umb.example.library.book.service;

import java.util.List;

public class BookRequestDataTransferObject {
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private Integer bookCount;
    private List<Long> categories;

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categoryIds) {
        this.categories = categoryIds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
}
