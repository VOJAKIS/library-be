package sk.umb.example.library.book.persistence.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;


@Entity(name = "book")
public class BookEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "id_book", unique = true)
    private Long id;

    @Column(name = "author_first_name")
    private String authorFirstName;
    
    @Column(name = "author_last_name")
    private String authorLastName;
    
    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "book_count")
    private Integer bookCount;

    /* @Column(name = "categories")
    private List<Long> categoryIds; */

	@Column(name = "id_categories")
	private List<Long> categories;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return this.authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return this.authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getBookCount() {
        return this.bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public List<Long> getCategoryIds() {
        return this.categories;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categories = categoryIds;
    }    

}
