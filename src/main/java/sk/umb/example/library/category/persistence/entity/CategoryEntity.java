package sk.umb.example.library.category.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "category")
public class CategoryEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "id_category", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
