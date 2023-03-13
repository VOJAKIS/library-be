package sk.umb.example.library.book.service;

import java.util.List;

import sk.umb.example.library.category.service.CategoryDetailDataTransferObject;

public class BookDetailDataTransferObject {
    private Long id;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private Integer bookCount;
    private List<CategoryDetailDataTransferObject> categories;


    public List<CategoryDetailDataTransferObject> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDetailDataTransferObject> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
